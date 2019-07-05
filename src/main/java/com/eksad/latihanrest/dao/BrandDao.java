package com.eksad.latihanrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eksad.latihanrest.model.Brand;

// crud repository itu terdapat method method yg biasa dipakai di DAO
// tetap butuh actual class, karena ini tu interface. nah class nya dibikin otomatis oleh spring
public interface BrandDao extends CrudRepository<Brand, Long>{ // entity yITU BRAND, DAN TIPE DARI ID jd diisi Long
	
	public List<Brand> findByName(String name);
	public List<Brand> findByProductType(String type);
	
//	@Query("select b from Brand b where name = ?1")  // pakai indeks
//	public List<Brand> findBySearch(String search);
	
	@Query("select b from Brand b where name = :search") //
	public List<Brand> findBySearch(@Param("search") String search);

}
