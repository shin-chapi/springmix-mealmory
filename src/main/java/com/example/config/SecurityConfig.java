//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserRepository userRepository;
//
//@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//
//		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests()
//		.antMatchers("/","/login","/signup").permitAll()
//			
//			.antMatchers("/h2-console/**").permitAll()
//			.anyRequest().authenticated();
//			
//
//	    http.formLogin()
//			.loginPage("/login")
//			
//			.usernameParameter("name")
//          .passwordParameter("password")
//			.defaultSuccessUrl("/calendar",true)
//			.failureUrl("/error")
//			.permitAll()
//			.and()
//
//			.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID");
//		
//		http.csrf().disable();	// H2DBデバッグ用
//		http.headers().frameOptions().disable(); // H2DBデバッグ用
//	}
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		    .withUser("user")
//		    .password("password")
//		    .roles("USER");	
//	}
	
//
//    
//        };
//    }
//}
