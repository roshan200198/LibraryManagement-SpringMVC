package com.app.Security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.app.DAO.BookDao;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	BookDao bookdao;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 //HttpSession session = 
//		request.getSession().setMaxInactiveInterval(15);
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		String username = request.getParameter("username");
		int userId = bookdao.getUserIdByUserName(username);
		System.out.println("session userId is "+ userId);
		
		
		System.out.println("Username is :: "+ username);
		//request.setAttribute("username", username);
		byte[] imageByte = bookdao.getImageByUsername(username);
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("userId", userId);
		
		session.setAttribute("imgByte", imageByte);
		
		if(roles.contains("ADMIN")) { 
			response.sendRedirect("admin/signin");
			
		} else {
			response.sendRedirect("user/userSigninPage");
		}
	
	}

}
