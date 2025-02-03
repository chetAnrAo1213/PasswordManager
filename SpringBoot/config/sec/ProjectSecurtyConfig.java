package com.boot.config.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class ProjectSecurtyConfig 
{
	
	
	 UserDetailsService adminCredits;
	
	
	
	public ProjectSecurtyConfig(UserDetailsService adminCredits) {
		this.adminCredits = adminCredits;
	}



	@Bean
	public SecurityFilterChain securityFilters(HttpSecurity https) throws Exception
	{
		return https
			    .authorizeHttpRequests(req -> 
			        req.requestMatchers(
			        	
			        	//User login/SignIn
			            "/PasswordManager/",
			            "/PasswordManager/SignUp",
			            "/PasswordManager/signIn",
			            "/Authentication/authenticateUserByPassword",
			            "/Authentication/authenticateUserByPin",
			            "/PasswordManager/home",
			             
			            // User SignUp/Registarion/ForgotPassword
			            "/Authentication/verifyEmail",
			            "/Authentication/saveUserDetails",
			            "/PasswordManager/ForgotPassword",
			            "/AuthenticationQuestions/QuestionsValidation",
			            "/AuthenticationQuestions/validateUserFromQuestions",
			            "/AuthenticationQuestions/ForgotPinValidation",
			            "/AuthenticationQuestions/validateUserFromPin",
			            "/Authentication/updatePasswordAndPinForUser",
			            "/Authentication/postUserCompleteData",
			            "/Authentication/ResetPassword",
			            "/Authentication/resetPasswordForUser",
			            "/Authentication/emailDBCheck",
			            // Crud Operation pages
			            "/UserPasswords/checkPinForAddPasswords",
			            "/UserPasswords/AddPasswordsFromUser",
			            "/UserPasswords/checkPinForUpdatePasswords",
			            "/UserPasswords/UpdatePasswordById",
			            "/UserPasswords/checkPinForGetPasswords",
			            "/UserPasswords/sortingPassword",
			            "/UserPasswords/checkPinFordeletePasswords", 
			            "/UserPasswords/deleteRecord",
			            "/UserPasswords/DecryptionPassword",
			            "/UserPasswords/decryptingPassword",
			            // Feature Pages
			            "/PasswordManager/ChatBot",
			            "/AiModel/Chat/UserMessage",
			            "/PasswordManager/Note",
			            "/QuickNote/addNote",
			            "/QuickNote/getNote",
			            "/PasswordManager/Todo",
			            "/PasswordManager/News",
			            "/api/news",
			            "/PasswordManager/NASAAPOD",
			            "/api/apod",
			            "/PasswordManager/PexelVideo",
			            "/api/videos",
			            
			            //user profile
			            "/PasswordManager/Profile",
			            "/PasswordManager/ApiUsage",
			            
			            //user logout
			            "/PasswordManager/logout",
			            
			          //Admin
			            "/PasswordManager/Admin/",
			            "/PasswordManager/Admin/home",
			            "/PasswordManager/Admin/AdminHome",
			            //"/PasswordManager/Admin/AdminRegister",
			            "/PasswordManager/Admin/AdminSignIn",
			            "/PasswordManager/Admin/Authentication/saveAdminInfo",
			            "/PasswordManager/Admin/Authentication/AdminLogin",
			            "/PasswordManager/Admin/Charts/basicInfo",
			            "/PasswordManager/Admin/UserSpecifics/getLists",
			            "/PasswordManager/Admin/UserSpecifics/getUserData",
			            "/PasswordManager/Admin/DeleteUser",
			            "/PasswordManager/Admin/DeleteUser/deleteByUserMailId",
			            "/PasswordManager/Admin/UserReportAI",
			            "/PasswordManager/Admin/UserReport/fetchAiReportForUser",
			            "/PasswordManager/Admin/logout",
			            
			            "/PasswordManager/Module"
			        ).permitAll()
			        .anyRequest().authenticated()
			    )
			    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
			    .httpBasic(Customizer.withDefaults()) 
			    .exceptionHandling(ex -> ex
			    	    .authenticationEntryPoint((request, response, authException) -> {
			    	        response.setContentType("text/html");
			    	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    	        response.getWriter().write("<html><body><h2>401 - Unauthorized Access</h2><p>You are not authorized to view this page.</p></body></html>");
			    	    })
		        )
		        .build();
			}
	
	@Bean 
	public BCryptPasswordEncoder pass()
	{
		return new BCryptPasswordEncoder(14);
	}
	
	@Bean
	public AuthenticationProvider provider()
	{
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		 dao.setUserDetailsService(adminCredits);
		 dao.setPasswordEncoder(pass());
		 return dao;
	}

}
