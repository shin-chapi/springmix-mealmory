package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //アクセスポリシーの設定
        http.authorizeRequests()
            //指定したパターンごとに制限をかける
            .antMatchers("/js/**", "/css/**","/img/**","/h2-console/**").permitAll()//制限なし
            .antMatchers("").permitAll()
            .anyRequest().authenticated();//上記以外は制限あり
        //フォーム認証の設定
        http.formLogin()
            /*ログインページの指定（指定なしの場合デフォルトのものが用意される）
              コントローラークラスでこのURLをマッピングしておく*/
            .loginPage("/login")  
            /*ログイン処理のパス（このURLへリクエストが送られると認証処理が実行される）
              ログインページのformタグのth:action属性にこのURLを指定しておく*/
            .loginProcessingUrl("/login")
            /*ログインページのログイン情報入力欄のname属性に以下の名称を指定する*/
            .usernameParameter("user")
            .passwordParameter("password")
            /*ログイン成功時に遷移するページ(trueで成功時は常にここに飛ぶ）
             コントローラークラスでこのURLをマッピングしておく*/
            .defaultSuccessUrl("/login", true)
            /*失敗時の遷移先、アクセス制限は解除する
              コントローラークラスでこのURLをマッピングしておく*/
            .failureUrl("/index").permitAll();
    }

    /**
     * 認証するユーザー情報をデータベースからロードする処理
     * @param auth　認証マネージャー生成ツール
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ///認証するユーザー情報をデータベースからロードする
        //その際、パスワードはBCryptでハッシュ化した値を利用する
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * パスワードをBCryptでハッシュ化するクラス
     * ハッシュ化するクラスも幾つか種類があるみたいです
     * @return パスワードをBCryptで暗号化するクラスオブジェクト
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}