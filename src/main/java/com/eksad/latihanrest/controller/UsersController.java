package com.eksad.latihanrest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.UsersDao;
import com.eksad.latihanrest.model.Users;


@RestController
@RequestMapping("admin")
public class UsersController {
	
	@Autowired
	UsersDao usersDao;
	
	@RequestMapping("")
	public HashMap<String, Object> admin() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "masuk sebagai admin");
		return map;
	}

	
	@RequestMapping("user")
	public HashMap<String, Object> user() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "masuk sebagai user");
		return map;
	}
// jadi saat ingin insert data. sistem mengecek data dari id yang pertama. jd ouput yg keluar akan eror
// dia akan membuat id baru ketika sistem selesai cek semua id
	
	@GetMapping("getAll")
	public List<Users> getAll() {
		return usersDao.findAll();
	}
	
	@PostMapping("save")
	public Users save(@RequestBody Users user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//BCryptPAsswordEncoder =  saat kita add new data di postman, dan set password nya dan role diuabah ke admin
// username itu bisa login di url admin dengan password yg sudah di set (cek gamabr di word)
		return usersDao.save(user);
	}
	
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		usersDao.deleteById(id);;
		return "Berhasil Dihapus";
	}


}
