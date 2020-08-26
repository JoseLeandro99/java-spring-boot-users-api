package com.example.demo.controllers;

import com.example.demo.domain.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.models.User;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable(name = "id") int id) {
		var user = userRepository.findById(id);
		
		return user;
	}
	
	@PostMapping("/users")
	public String createUser(@RequestBody User body) {		
		var user = new User();
		user.setName(body.getName());
		user.setEmail(body.getEmail());
		userRepository.save(user);
		
		return "Usuário criado com sucesso!";
	}
	
	@PutMapping("/users/{id}")
	public String updateUser(@PathVariable(name = "id") int id, @RequestBody User body) {
		var user = userRepository.findById(id);
		user.setName(body.getName());
		user.setEmail(body.getEmail());
		
		userRepository.save(user);
		
		return String.format("Usuário com id %s atualizado com sucesso!", id);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		userRepository.deleteById(id);
		
		return String.format("Usuário com id %s excluído com sucesso!", id);
	}

}
