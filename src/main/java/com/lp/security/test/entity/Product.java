package com.lp.security.test.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "product")
@Data
public class Product  implements Serializable {
	
	@Id
	private String productId;
	
	private String productName;
	
	private String availableQuantity;
	
	private String description;

}
