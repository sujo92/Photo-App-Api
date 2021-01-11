package com.example.photoapp_api_users.security;

import com.example.photoapp_api_users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment env;
    private UsersService usersService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public WebSecurity(UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder,Environment environment){
        this.env=environment;
        this.usersService=usersService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
        .and().addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter= new AuthenticationFilter(usersService,env,authenticationManager());
        authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
//        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }
}
