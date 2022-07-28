package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Entity
	@Table(name = "Users")	
	public class Users {
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(nullable = false)
	    private Integer id;
	    
	    @Column(nullable = false)
	    private String name;
	    
	    @Column(nullable = false)
	    private String password;
	    
	    @Column(nullable = false)
	    private String mail;
	    
	    @Column(name = "create_datetime", nullable = false)
	    private LocalDateTime createDatetime;
	}

