package com.lp.security.test.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "roles")
@Data
@Getter
@Setter
public class UserRole implements GrantedAuthority ,Serializable {
	
	@Id
	private String roleId;
	
	private Roles name;

	@Override
	public String getAuthority() {
		return name.name();
	}

}
