package com.example.Cardetail.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.Cardetail.Service.UserUserDetailsService;
import com.example.Cardetail.filter.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionResolver;
	
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthFilter jwtAuthFilter()
	{
		return new JwtAuthFilter(exceptionResolver);
	}

    
    @Bean
    public UserDetailsService userDetailsService() {
//		UserDetails user=User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("user@123"))
//				.roles("User")
//				.build();
//		UserDetails admin=User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin@123"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
    	return new UserUserDetailsService();
//		
    	
    }
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
    	return http.csrf().disable()
    			.authorizeHttpRequests()
    			.requestMatchers("/cardetails/welcome","/userdetails/new","/userdetails/authenticate").permitAll()
    			.and()
    			.authorizeHttpRequests().requestMatchers("/cardetails/**")
    			.authenticated().and()
    			.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			.and()
    			.authenticationProvider(authenticationprovider())
    			.addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class)
    			.build();
    }
    @Bean
    public AuthenticationProvider authenticationprovider()
    {
    	DaoAuthenticationProvider authenticationprovider=new DaoAuthenticationProvider();
    	authenticationprovider.setUserDetailsService(userDetailsService());
    	authenticationprovider.setPasswordEncoder(passwordEncoder());
    	return authenticationprovider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	return config.getAuthenticationManager();
    }


}
