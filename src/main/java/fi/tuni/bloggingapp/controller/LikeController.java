package fi.tuni.bloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.bloggingapp.repository.LikeRepository;

@RestController
public class LikeController {
	
	@Autowired
	private LikeRepository likeRepository;
	
	public long countAllLikesByBlogPostId() {
		return 0;
	}

}
