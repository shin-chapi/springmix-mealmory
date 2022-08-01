package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserRepository userRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/css/**", "js/**", "/img/**", "/webpack.mix.js");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "sinup", "index").permitAll()
				.antMatchers("/css/**", "js/**", "/img/**", "/webpack.mix.js").permitAll().anyRequest().authenticated()
				.and()

				.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login")
				.defaultSuccessUrl("/calender").and()

				.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password"))
				.authorities("ROLE_ADMIN").and().withUser("user").password(passwordEncoder().encode("password"))
				.authorities("ROLE_USRE");
	}

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            // ユーザ名を検索します（ユーザが存在しない場合は、例外をスローします）
//            var user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
//
//            // ユーザ情報を返します
//            return new User(user.getName(), user.getPassword(), user.getMail(), 
//                    AuthorityUtils.createAuthorityList("ADMIN"));
//        };
//    }
}
