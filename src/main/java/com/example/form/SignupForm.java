package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class SignupForm {
	
	public SignupForm() {
		
	}
	
	public SignupForm( String name,
			String mail,
			String password) {
		super();
		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	
	
	
	@NotBlank
	@Length(max=255)
	private String name;

	@NotBlank
	@Email(message="emailの形式で入力してください")
	private String mail;
	
	@NotNull
	@Length(max=255)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message="パスワードは英数字の大文字と小文字、記号だけが使えます")
	private String password;
	
}
