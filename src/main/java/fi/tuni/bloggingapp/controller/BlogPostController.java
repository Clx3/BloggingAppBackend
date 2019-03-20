package fi.tuni.bloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.bloggingapp.repository.BlogPostRepository;

@RestController
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository userRepo;

}
