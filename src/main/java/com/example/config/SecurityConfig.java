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

import com.example.service.UserDetailsServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl  userDetailsServiceImp;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
			.antMatchers("/h2-console/**");
		
	}
	
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
	        
	    }

  

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //アクセスポリシーの設定
        http.authorizeRequests()
            //指定したパターンごとに制限をかける
            .anyRequest().authenticated()
        	.and()
            .formLogin();
            
           
            //上記以外は制限あり
       
    }
    /**
     * パスワードをBCryptでハッシュ化するクラス
     * ハッシュ化するクラスも幾つか種類がある
     * @return パスワードをBCryptで暗号化するクラスオブジェクト
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}