package br.com.jhonerodrigues.core.requests;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	
	private String name;
	private LocalDate birthday;
	private String phone;

}
