package com.example.TodoApplication.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Name")
	private String username;

	@Size(min = 10, message = "enter at least 10 characters")
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Target_Date")
	private LocalDate targetDate;


	public Todo(String username, String description, LocalDate targetDate) {
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
	}

	// No-args constructor for JPA
	public Todo() {
	}

	// Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}



	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate +"]";
	}
}
