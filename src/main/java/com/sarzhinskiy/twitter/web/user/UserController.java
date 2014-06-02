package com.sarzhinskiy.twitter.web.user;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.hsqldb.Session;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sarzhinskiy.twitter.domain.Language;
import com.sarzhinskiy.twitter.domain.twit.Twit;
import com.sarzhinskiy.twitter.domain.user.Gender;
import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.domain.user.UserAdditionalInfo;
import com.sarzhinskiy.twitter.encryption.Encrypt;
import com.sarzhinskiy.twitter.service.TwitService;
import com.sarzhinskiy.twitter.service.UserService;
import com.sarzhinskiy.twitter.web.usermanager.UserManager;

@Controller
public class UserController {
	
	private UserService userService;
	private TwitService twitService;
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	Language languageInstanse;
	
	
	@Autowired
	public UserController(UserService userService, TwitService twitService) {
		this.userService = userService;
		this.twitService = twitService;
	}
	
	@RequestMapping(value = "/")  
	public String indexHome(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("language", languageInstanse.getLanguageMap());
//		model.addAttribute("language", languageInstanse.getLanguageMap());
		return "index";
	}
		
	@RequestMapping(value = "/userhome")
	public String home(Model model) {
		User user = userManager.getUser();
		List<Twit> twits = twitService.findAllByOwnerId(user.getId());
		model.addAttribute("myTwits", twits);
		return "UserHome";
	}
	
	@RequestMapping(value = "/usereditinfo")
	public String editInfo(Model model) {
		UserAdditionalInfo userInfo = userService.findUserAdditionalInfo(userManager.getUser().getId());
		if (userInfo != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gender", userInfo.getGender().toString().toLowerCase());
			map.put("birthday", userInfo.getBirthday());
			map.put("country", userInfo.getCountry());
			map.put("city", userInfo.getCity());
			map.put("address", userInfo.getAddress());
			map.put("phone", userInfo.getPhone());
			map.put("status", userInfo.getStatus());
			map.put("aboutYourself", userInfo.getAboutYourself());
			byte [] photo = userManager.getUser().getPhoto(); 
			if (photo != null) {
				map.put("photo", "true");
				System.out.println("photo has been loaded...");
			}
			model.addAllAttributes(map);
		}
		return "UserEditInfo";		
	}
	
	@RequestMapping(value="/loadUserPhoto")
	public String loadUserPhoto(@RequestParam(value="file") Part file) {
		if (file != null) {
			byte [] fileContent = null;
			try {
				InputStream inputStream = file.getInputStream();
				fileContent = IOUtils.toByteArray(inputStream);
				userManager.getUser().setPhoto(fileContent);
				System.out.println(fileContent);		
				 BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(fileContent));
				 ImageIO.write(bufImage, "jpg", new File("savingImageA.jpg"));
			     OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("savingImageAAA.jpg")));
			     out.write(fileContent);
			     if (out != null) out.close();
//				 System.out.println(fileContent);
			}
			catch (IOException exception) {
				System.err.println(exception);
			}
		}
		return "redirect:/usereditinfo";
	}
	
	@RequestMapping(value="/photo/{id}")
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
//		System.out.println("downloadPhoto");
		User user = userService.findById(id);
		//must be checked if not null!
		return user.getPhoto();
	}
	
	@RequestMapping(value="/language")
	public String changeLanguage(Model model, @RequestParam String language, HttpServletRequest request, HttpServletResponse response) {
		response.addCookie(new Cookie("localeCookie", language));
		model.addAttribute("language", languageInstanse.getLanguageMap());
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	
	@RequestMapping(value="/friends")
	public String friendsPage(Model model) {
		model.addAttribute("friendOption", "my_friends");
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "FriendsPage";
	}
	
	@RequestMapping(value="/search_users")
	public String searchFriends(Model model) {
		model.addAttribute("searchOption", "search_users");
		model.addAttribute("users", userService.findAll());
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/search_user")
	public String searchReqieredUsers(Model model, @RequestParam String searchUserInfo) {
		model.addAttribute("searchOption", "search_users");
		if (searchUserInfo.trim().isEmpty()) {
			model.addAttribute("users", userService.findAll());
		}
		else {
			List<User> users = userService.findByFullName(searchUserInfo); 
			model.addAttribute("users", users);
			model.addAttribute("searchUserInfo", searchUserInfo);
		}
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/full_search_users")
	public String fullSearchFriends(Model model) {
		model.addAttribute("searchOption", "full_search_users");
		model.addAttribute("users", userService.findAll());
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/full_search_user")
	public String fullSearchReqieredUsers(Model model, @RequestParam String searchUserInfo) {
		model.addAttribute("searchOption", "full_search_user");
		if (searchUserInfo.trim().isEmpty()) {
			model.addAttribute("users", userService.findAll());
		}
		else {
			List<User> users = userService.findByFullName(searchUserInfo); 
			model.addAttribute("users", users);
			model.addAttribute("searchUserInfo", searchUserInfo);
		}
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/addToFriend")
	public String addToFriend(Model model, @RequestParam String userId) {
		long addedFriendId = Long.parseLong(userId);
		userService.addObservedUser(userManager.getUser(), userService.findById(addedFriendId));
		model.addAttribute("searchOption", "search_users");
		model.addAttribute("users", userService.findAll());
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/addToFriendFullSearch")
	public String addToFriendFullSearch(Model model, @RequestParam String userId) {
		long addedFriendId = Long.parseLong(userId);
		userService.addObservedUser(userManager.getUser(), userService.findById(addedFriendId));
		model.addAttribute("searchOption", "full_search_users");
		model.addAttribute("users", userService.findAll());
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "SearchPage";
	}
	
	@RequestMapping(value="/my_friends")
	public String showMyFriends(Model model) {
		model.addAttribute("friendOption", "my_friends");
		long id = userManager.getUser().getId();
		model.addAttribute("myFriends", userService.findAllObservedUsers(id));
		return "FriendsPage";
	}
	
	@RequestMapping(value="/show_user")
	public String showUserById(Model model, @RequestParam String userId) {
		Long showUserId = Long.parseLong(userId);
		User user = userService.findById(showUserId);
		List<Twit> twits = twitService.findAllByOwnerId(user.getId());
		model.addAttribute("myTwits", twits);
		model.addAttribute("showUserHome", user);
		return "UserHome";
	}
	
	@RequestMapping(value="/my_observed_users")
	public String showMyObservedFriends(Model model) {
		model.addAttribute("friendOption", "my_observed_users");
		return "FriendsPage";
	}
	
	@RequestMapping(value="/saveuserinfo")
	public String saveUserInfo(RedirectAttributes redirectAttributes, 
			@RequestParam String firstName, @RequestParam String lastName, 
			@RequestParam String oldPassword, @RequestParam String  newPassword, @RequestParam String confirmNewPassword,
			@RequestParam String gender, @RequestParam String birthday,
			@RequestParam String country, @RequestParam String city, 
			@RequestParam String address, @RequestParam String phone, 
			@RequestParam String status, @RequestParam String aboutYourself) {
		
		User user = userManager.getUser();
		user.setName(firstName);
		user.setSurname(lastName);
		if (oldPassword != null && !oldPassword.isEmpty()
				&& newPassword != null && !newPassword.isEmpty()
				&& confirmNewPassword != null && !confirmNewPassword.isEmpty()) {
			if (user.getPasswordCipher().equals(Encrypt.encryptByMDFive(oldPassword))) {
				if (newPassword.equals(confirmNewPassword)) {
					String newPasswordEncrypt = Encrypt.encryptByMDFive(newPassword);
					user.setPasswordCipher(newPasswordEncrypt);
				}
				else {
					redirectAttributes.addFlashAttribute("err_register_dif_password", "true");
				}
			}
			else {
				redirectAttributes.addFlashAttribute("err_not_correct_password", "true");
			}
		}
		userService.update(user);
		
		UserAdditionalInfo userInfo = userService.findUserAdditionalInfo(user.getId());
		if (userInfo == null) {
			userInfo = new UserAdditionalInfo(user.getId());
		}
		if (isFilledField(gender)) {
			if (gender.equals("male")) {
				userInfo.setGender(Gender.MALE);
			}
			else {
				userInfo.setGender(Gender.FEMALE);
			}
		}
		if (isFilledField(address)) {
			userInfo.setAddress(address);
		}
		if (isFilledField(status)) {
			userInfo.setStatus(Integer.parseInt(status));
		}
		if (isFilledField(phone)) {
			userInfo.setPhone(phone);
		}
		if (isFilledField(aboutYourself)) {
			userInfo.setAboutYourself(aboutYourself);
		}
		if (isFilledField(birthday)) {
			userInfo.setBirthday(new LocalDate(birthday));
		}
		if (isFilledField(country)) {
			userInfo.setCountry(country);
		}
		if (isFilledField(city)) {
			userInfo.setCity(city);
		}
		userService.updateAdditionalInfo(userInfo);
		return "redirect:usereditinfo";
	}
	
	private boolean isFilledField(String field) {
		return (field != null && !field.isEmpty());
	}

	@RequestMapping(value="/news")
	public String showNews() {
		return "NewsPage";
	}
	
}
