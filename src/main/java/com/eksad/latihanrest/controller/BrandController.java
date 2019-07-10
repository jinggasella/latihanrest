package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.model.Brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1") 
@Api(tags = "Brand")
public class BrandController {

	//http://localhost:8080/brand/getAll
	@Autowired
	BrandDao brandDao;
	
	@GetMapping("getAllBrand") // slash didepan ga dipake gapapa, tp slash dibelakamg dipake kalo mau nambahin parameter
	@ApiOperation(
			value = "API to retrieve all brand's data",
			notes = "Return data with JSON format",
			tags = "Get Data API" 
			)
	public List<Brand> getAll() {
		List<Brand> result = new ArrayList<>() ;
		
		brandDao.findAll().forEach(result::add);
		
		return result;
	}
	
//------------------------------------------------------------------------------------------
	
	//http://localhost:8080/brand/getOne/1
	@GetMapping("getOneBrand/{id}")  // pake slash karena parameter yg diisi itu wajib
	public Brand getOne(@PathVariable Long id) {
		return brandDao.findById(id).orElse(null);
			
	}
//-----------------------------------------------------------------------------
	
//	@RequestMapping(value = "save", method = RequestMethod.POST)
//	public String save(@RequestBody Brand brand) { // membaca data body yg di postman tadi
//		try {
//			brandDao.save(brand);
//			
//			return "Berhasil Tersimpan";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "Gagal Tersimpan";
//		}
//	}
//------------------------------------------------------------------------------------------
// save ini hasil yg keluar di postman, data yg berhasil kita update or edit	
	@ApiOperation(
			value = "Add new brand's data", 
			notes = "Add new brand's data to database",
			tags = {"Data Manipulation API", "" }// ini untuk kalo mau masuk ke dua tempat
			)
	@PostMapping(value = "saveBrand")
	public Brand save(@RequestBody Brand brand) { // membaca data body yg di postman tadi
		try {

			return brandDao.save(brand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//----------------------------------------------------------------------------------------
	@ApiOperation(
			value = "Update new brand's data", 
			notes = "Update new brand's data to database",
			tags = "Data Manipulation API"
			)
	@PutMapping(value = "updateBrand/{id}")
	public String update(@RequestBody Brand brand, @PathVariable Long id) {
		Brand brandSelected = brandDao.findById(id).orElse(null);
		if (brandSelected != null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());
			
//			return brandDao.save(brandSelected);
			brandDao.save(brandSelected);
			return "Berhasil Memperbarui";
		} else {
//			return "Gagal Memperbarui";
			return null;
		}
	}
//-----------------------------------------------------------------------------------------
	@ApiOperation(
			value = "Delete brand's data",
			notes = "Delete data brand to database",
			tags = "Data Manipulation API" // getAll employees masuk ke kategori Get Data API
			)
	@RequestMapping(value = "deleteBrand/{id}", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
	}

	
}
