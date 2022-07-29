package com.example.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
	
	@NotBlank(message = "名前を入力してください")
	@Size(max=60, message = "名前は60桁以内で入力してください")
	private String name;
	
	@NotBlank(message = "メールを入力してください")
	@Email(message = "電話番号の形式で入力してください")
	@Size(max=255, message = "メールアドレスは255桁以内入力してください")
	private String mail;
	

	@NotBlank(message = "パスワードを入力してください")
	@Size(max=255, message = "パスワードは255文字以内で入力してください")
	private String password;
}
