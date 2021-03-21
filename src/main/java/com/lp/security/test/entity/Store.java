package com.lp.security.test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "store")
@Data
public class Store implements Serializable {
	
	@Id
	private String storeId;
	
	private String storeName;
	
	private String location;
	
	private String phoneNumber;
	
	@DBRef
	private List<Product> product = new ArrayList<>();

}
