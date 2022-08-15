package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImp;

	@Override
	public void configure(WebSecurity web) throws Exception {
		/**
		 * 静的リソースへのアクセスには、セキュリティを適用しない
		 */
		web.ignoring().antMatchers("/h2-console/**").antMatchers("/images/**", "/css/**", "/js/**","/webjar/**");

	}
	/**
	 * ユーザーの認証方式
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
        //アクセスポリシーの設定
        http.authorizeRequests()
            .antMatchers("/login","/signup").permitAll()
            .anyRequest().authenticated();
        	
        //ログイン設定
        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/calendar")
            .usernameParameter("username") 
            .passwordParameter("password")
            .defaultSuccessUrl("/calendar")
            .failureUrl("/login?error")
        .and()
            .logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutUrl("/logout")
        	.logoutSuccessUrl("/login")
        	.deleteCookies("JSESSIONID")
        	.invalidateHttpSession(true);
        	
        // @formatter:on
	}
	/**
	 * パスワードをBCryptでハッシュ化するクラス ハッシュ化するクラスも幾つか種類がある
	 * 
	 * @return パスワードをBCryptで暗号化するクラスオブジェクト
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}