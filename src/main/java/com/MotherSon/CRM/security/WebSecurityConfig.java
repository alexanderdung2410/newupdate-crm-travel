 package com.MotherSon.CRM.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.MotherSon.CRM.security.jwt.AuthEntryPointJwt;
//import com.MotherSon.CRM.security.jwt.AuthTokenFilter;
//import com.MotherSon.CRM.security.jwt.AuthrEntryPointJwt;
//import com.MotherSon.CRM.security.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 


import com.MotherSon.CRM.security.jwt.AuthEntryPointJwt;
import com.MotherSon.CRM.security.jwt.AuthTokenFilter;
import com.MotherSon.CRM.security.services.UserDetailsServiceImpl;
import com.MotherSon.CRM.security.jwt.AuthrEntryPointJwt;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private AuthrEntryPointJwt unautharizedHandler;
    
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	System.out.println("Helll0");
    	 http.csrf(csrf -> csrf.disable())
         .exceptionHandling(exception -> exception
             .authenticationEntryPoint(unauthorizedHandler)
             .accessDeniedHandler(unautharizedHandler))
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(auth ->
         
//        auth.antMatchers("/Motherson/crm/v1/signup", "/Motherson/crm/v1/signin").permitAll() // Exclude /signin and /login from authentication
//         .antMatchers("/Motherson/api/v1/**").authenticated() // Require authentication for other /Motherson/api/v1/** endpoints
         auth.requestMatchers("/Motherson/crm/v1/signup", "/Motherson/crm/v1/signin","/Motherson/crm/v1/**").permitAll() 
        // .requestMatchers("/Motherson/crm/v1/**").authenticated() 
         );

    	 
    	 
    	 
        http.authenticationProvider(authenticationProvider());
    
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
       
//       http.addFilterBefore(new AuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
