package com.app.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		String error = exception.getMessage();
		
		request.setAttribute("error", "Invalid username or password.");
		
		System.out.println("A failed login attempt with email: "
                + username + ". Reason: " + error);
		
		/*
		 * super.setDefaultFailureUrl("/custom_login?error");
		 * super.onAuthenticationFailure(request, response, exception);
		 */
		
		/*
		 * UrlPathHelper helper = new UrlPathHelper(); String contextPath =
		 * helper.getContextPath(request);
		 */
		 
		//String redirectUrl = request.getContextPath() + "/login_error?error";
		//response.sendRedirect("/SpringMvc3/custom_login?error");
		
		getRedirectStrategy().sendRedirect(request, response, "/custom_login?error=true");
		
	}

}
