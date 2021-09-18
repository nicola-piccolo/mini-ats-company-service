package org.miniats.companyservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String uid;

	@NotNull
	@Size(max=256, message="Name must be max 256 characters long")
	private String name;
	
	@NotNull
	@Size(max=256, message="Email must be max 256 characters long")
	private String email;
	
	@Size(max=32, message="Phone number must be max 32 characters long")
	private String phone;
	
	@Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean deleted = false;
}
