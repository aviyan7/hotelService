package com.hotel.project.config;


import com.hotel.project.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configurable
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig  {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

   
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	authProvider.setUserDetailsService(userDetailsService);
    	authProvider.setPasswordEncoder(passwordEncoder());
    	return authProvider;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf()
//                .disable()
//                .cors()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/api/v1/generate-token", "/api/v1/users").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable()
    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    	.authorizeRequests().requestMatchers("api/v1/generate-token", "api/v1/users").permitAll()
    	.anyRequest().authenticated();
    http.authenticationProvider(authenticationProvider());
    	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
}
