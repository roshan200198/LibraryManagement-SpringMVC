package com.app.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.app.DAO.BookDao;
import com.app.Pojo.AuthRequest;
import com.app.Pojo.ImportBook;
import com.app.Service.SendMail;
import com.app.Util.JwtUtil;

@Controller
public class SecurityController {
	
	/*
	 * @Autowired JwtUtil jwtUtil;
	 * 
	 * @Autowired AuthenticationManager authenticationManager;
	 */
	
	@Autowired
	BookDao bookdao;

	Random random = new Random();
	 
	@Autowired
	SendMail sendmail;
	
	@GetMapping("/custom_login")
	public String custom_login() {
		
		return "login_css";
	}

	
	/*
	 * @PostMapping("/login_error") public String login_error() {
	 * System.out.println("User has failed to login!"); return
	 * "redirect:/custom_login?error"; }
	 */
	
	@PostMapping("/failed_login")
	public String failed_login(Model model) {
		model.addAttribute("error" , "Bad credential!!");
		
		return "login_css";
	}

	/*
	 * @PostMapping("/authenticate") public String generateToken(@ModelAttribute
	 * AuthRequest authRequest) throws Exception {
	 * 
	 * try { authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(authRequest.getUsername(),
	 * authRequest.getPassword()) );
	 * 
	 * }catch(Exception ex) { throw new Exception("Invalid username/password"); }
	 * return jwtUtil.generateToken(authRequest.getUsername()); }
	 */


@GetMapping("/forgot_password")
public String forgot_password() {
	
	return "forgot_password";
}

@PostMapping("/generate_otp")
public String generate_otp(@RequestParam("mailId") String mailId, Model model,HttpSession session) {
	System.out.println("MailId is: "+ mailId);
	
	session.setAttribute("mailId", mailId);
	System.out.println("mailId is: "+mailId);
//	request.getSession().setAttribute("mailId", mailId);
	
//	String otp = getRandom();
//	System.out.println("mail_otp is: "+otp);
//	session.setAttribute("mail_otp", otp);
	int otp = Integer.parseInt(sendmail.getRandom());
	

	if (sendmail.sendEmail(mailId,otp)) {
		System.out.println("sendMail true..");
		session.setAttribute("mail_otp", otp);
		session.setAttribute("mailId", mailId);

		return "verify_otp";
	} else {
		model.addAttribute("status", "check mailID or resend OTP.");
		System.out.println("sendMail false..");
		return "forgot_password";
	}

}

@RequestMapping("/setpassword")
public String set_password(@RequestParam("txt_otp") int txt_otp,
					        Model model, HttpSession session) {
	
	int mail_otp = (Integer) session.getAttribute("mail_otp");
	//model.addAttribute("mailId", mailId);
	String mailId = (String) session.getAttribute("mailId");
	session.setAttribute("mailId", mailId);
	System.out.println("session.getAttribute(\"mail_otp\") " + session.getAttribute("mail_otp"));

	if( bookdao.getUserByMailId(mailId)) {
		System.out.println("txt_otp: "+txt_otp+ "mail_otp "+ session.getAttribute("mail_otp"));
		if(txt_otp == mail_otp){
			model.addAttribute("status" , "OTP verification successfull. You can set new password..");
			System.out.println("OTP verification successfull. You can set new password..");
			
			return "set_password";
		} else {
			model.addAttribute("status", "Incorrect OTP. Please resend OTP" );
			System.out.println("Incorrect OTP. Please resend OTP");
			
			return "verify_otp";
		}
		
	}else  {
		model.addAttribute("status", "MailId is not exist in database! please sign up first");
		System.out.println("mail not exist in database..");
		return "forgot_password";
	}

}


@PostMapping("/new_password")
public String new_password( @RequestParam("password") String password,@RequestParam("cpassword") String cpassword,
							Model model,HttpSession session) {
	
	String mailId = (String) session.getAttribute("mailId");
	
	if(password.equals(cpassword)) {
		if(bookdao.update_newpassword(mailId, password)) {
			
			System.out.println("Password update successfully");
			model.addAttribute("status", "Password update successfully. Use new password..");
			return "redirect:custom_login";
		} else {
			model.addAttribute("status", "Failed to update password!!");
			return "set_password";
		}
		
	} else {
		
		model.addAttribute("status","Password doesn't match!..");
		
		return "set_password";
	}
	
	
	
}

}



