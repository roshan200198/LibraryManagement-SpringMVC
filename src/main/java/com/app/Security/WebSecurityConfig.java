
package com.app.Security;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;


//@Configuration --> @EnableWebSecurity ==> contains @Configuration

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
//	@Autowired
//    private CustomLogoutSuccessHandler logoutHandler;
	
	/*
	 * @Autowired private GenericFilterBean jwtFilter;
	 */
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	SimpleUrlAuthenticationFailureHandler simpleAuthenticationFailureHandler;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("Select username, password, enabled from users where username =?")
				.authoritiesByUsernameQuery("Select username, role from users where username = ? ");
	}

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http
	  .authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
	  .antMatchers("/user/**").hasAuthority("USER")
	  .and()
	  .formLogin().loginPage("/custom_login") .successHandler(
	  authenticationSuccessHandler).loginProcessingUrl("/process_login")
	  .failureHandler(simpleAuthenticationFailureHandler)
	  
	  .and()
	  
	  .csrf().disable().logout().logoutSuccessUrl("/").permitAll(
	  ) .and().exceptionHandling().accessDeniedPage("/Access_Denied");
	  
	  }
	
	  
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers("/authenticate")
	 * .permitAll().anyRequest().authenticated()
	 * .and().exceptionHandling().and().sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
	 * }
	 * 
	 * @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 */

}
