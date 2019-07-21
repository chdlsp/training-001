package com.rasol.training001.config;

import com.rasol.training001.service.RestUserDetailService;
import com.rasol.training001.service.RestUserDetailServiceImpl;
import com.rasol.training001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@Order
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestUserDetailService restUserDetailService;

    public SecurityConfig(RestUserDetailService restUserDetailService){
        super();
        this.restUserDetailService = restUserDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // for h2 console start
                .authorizeRequests().antMatchers("/h2-console/*").permitAll().and()
                .csrf().disable()
                .headers().frameOptions().disable().and()
                // for h2 console end
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // not working
//                .antMatchers(HttpMethod.POST, "/histories/users/{userId}").access("@Guard.checkUserId(authentication,#userId)")
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(restUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Component
//    public class Guard{
//        public boolean checkUserId(Authentication authentication, String userId){
//            return authentication.getName().equals(userId);
//        }
//    }

}