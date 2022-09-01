package com.be.electroniccomponentstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@NotNull(message = "required")
	private String username;
	
	@NotNull(message = "required")
	@Length(min = 4,max = 50)
	private String password;
}
