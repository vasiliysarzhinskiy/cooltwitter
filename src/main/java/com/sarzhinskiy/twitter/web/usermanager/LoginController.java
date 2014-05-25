package com.sarzhinskiy.twitter.web.usermanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.encryption.Encrypt;
import com.sarzhinskiy.twitter.service.UserService;


@Controller
public class LoginController {
	private static final String LOGIN_PAGE = "LoginPage";
	private static final String REGISTER_PAGE = "RegisterPage";
	
	@Autowired
	private UserManager userManager;		

	@Autowired
	private UserService userService;
	
	public LoginController() {
	}
			
	
	@RequestMapping("/login")
	public String tryLogin(HttpSession session,
            HttpServletRequest request,  RedirectAttributes redirectAttributes, @RequestParam String email, @RequestParam String password) {
		if ((email == null || email.isEmpty()) 
				|| (password == null || password.isEmpty())) {
			redirectAttributes.addFlashAttribute("err_register_empty","true");
			return "redirect:/";
		}
		if (userManager.login(email, password)) {  
			session = request.getSession();  //?
			session.setAttribute("user", userManager.getUser());
			return "redirect:userhome";
		}
		redirectAttributes.addFlashAttribute("err_login","true");
		return "redirect:/";
	}
	
	@RequestMapping("/registration")
	public String registration() {
		return REGISTER_PAGE;
	}
	

	
	@RequestMapping("/register")
	public String register(HttpServletRequest request,  RedirectAttributes redirectAttributes, @RequestParam String email, @RequestParam String password, 
			@RequestParam String  confirmpassword, @RequestParam String firstName, @RequestParam String lastName) {
		if ((email == null || email.isEmpty()) 
				|| (password == null || password.isEmpty())
				|| (confirmpassword == null || confirmpassword.isEmpty()) ) {
			redirectAttributes.addFlashAttribute("err_register_empty","true");
			return "redirect:/registration";
		}
		if (userService.checkIfExist(email)) {
			redirectAttributes.addFlashAttribute("err_register_exist_email","true");
			return "redirect:/registration";
		}
				
		if (!password.equals(confirmpassword)) {
			redirectAttributes.addFlashAttribute("err_register_dif_password", "true");
			return "redirect:/registration";
		}
		User user = new User(email, Encrypt.encryptByMDFive(password));
		user.setName(firstName);
		user.setSurname(lastName);
		boolean isSuccess = userService.create(user);
		userManager.login(email, password);
		HttpSession session = request.getSession();  //?
		session.setAttribute("user", userManager.getUser());
		return "redirect:userhome";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		userManager.logout();
		return "redirect:/";
	}
	
}
