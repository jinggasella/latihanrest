package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter  //
//@EqualsAndHashCode
//@ToString

@Data // bisa dipersingkat gettersetter, equals dan tostring 
@EqualsAndHashCode(callSuper = true) // artinya ikut sm parents. kalo false enggak
@ToString(callSuper = true)
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // karena auto increment
//	private Long id;
//	
////	@EqualsAndHashCode.Include // kalo pake @data, ini ga perlu ada
//	@Column(nullable = false)
//	private String name;
//	
//	@Column(name = "product_type")  // nama kolom di pgadmin/database
//	private String productType;

//2-------------------------------------------------------------------------------
	@Column(nullable = false)
	private String name;

	@Column(name = "product_type")
	private String productType;
}
