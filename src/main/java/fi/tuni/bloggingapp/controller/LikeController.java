package fi.tuni.bloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.bloggingapp.entity.Like;
import fi.tuni.bloggingapp.repository.LikeRepository;

@RestController
public class LikeController {
	
	@Autowired
	private LikeRepository likeRepository;
	
	@RequestMapping(value = "like/", method = RequestMethod.POST)
	public Like postLike(@RequestBody Like like) {
		return likeRepository.save(like);
	}
	
	@RequestMapping(value = "like/count/{blogpostId}", method = RequestMethod.GET)
	public long countAllLikesByBlogPostId(@PathVariable("blogpostId") long blogpostId) {
		return likeRepository.countByblogpostId(blogpostId);
	}

}
