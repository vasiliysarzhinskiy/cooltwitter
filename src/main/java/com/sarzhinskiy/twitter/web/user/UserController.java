package com.sarzhinskiy.twitter.web.user;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.Session;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sarzhinskiy.twitter.domain.Language;
import com.sarzhinskiy.twitter.domain.user.Gender;
import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.domain.user.UserAdditionalInfo;
import com.sarzhinskiy.twitter.encryption.Encrypt;
import com.sarzhinskiy.twitter.service.UserService;
import com.sarzhinskiy.twitter.web.usermanager.UserManager;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	Language languageInstanse;
	
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	

	@RequestMapping(value = "/")  
	public String indexHome(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("language", languageInstanse.getLanguageMap());
//		model.addAttribute("language", languageInstanse.getLanguageMap());
		return "index";
	}
	
	@RequestMapping(value = "/user/viewall") 
	public String showAllUsers(Locale locale, Model model) {
		model.addAttribute("users", userService.findAll());
		return "ViewAllUsers";
	}
	
	@RequestMapping(value = "/userhome")
	public String home() {
		User user = userManager.getUser();
		System.out.println(user);
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
			model.addAllAttributes(map);
		}
		
		return "UserEditInfo";		
	}
	
	@RequestMapping(value="/language")
	public String changeLanguage(Model model, @RequestParam String language, HttpServletRequest request, HttpServletResponse response) {
		response.addCookie(new Cookie("localeCookie", language));
		model.addAttribute("language", languageInstanse.getLanguageMap());
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="//twitspage")
	public String twitsPage(){
		return "TwitsPage";
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
					System.out.println("not correct pass!");
				}
			}
			else {
				redirectAttributes.addFlashAttribute("err_not_correct_password", "true");
				System.out.println("not correct pass");
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

}
