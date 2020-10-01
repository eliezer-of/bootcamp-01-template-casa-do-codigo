package com.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casadocodigo.entity.Author;
import com.casadocodigo.requests.AuthorRequest;
import com.casadocodigo.validators.EmailUniqueValidator;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EmailUniqueValidator emailUniqueValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(emailUniqueValidator);
	}

	@PostMapping(value = "")
	@Transactional
	public String save(@Valid @RequestBody AuthorRequest request) {

		Author author = request.toModel();
		manager.persist(author);
		return author.toString();
	}

}
