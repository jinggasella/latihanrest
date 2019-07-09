package com.eksad.latihanrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eksad.latihanrest.model.Product;

public interface ProductDao extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.brand.id = :brandId")
	public Iterable<Product> findByBrandId(@Param("brandId") Long brandId);
	
	@Query("select b from Product b where b.name = :search") //
	public List<Product> findBySearch(@Param("search") String search);
}
