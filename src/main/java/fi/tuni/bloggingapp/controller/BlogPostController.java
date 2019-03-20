package fi.tuni.bloggingapp.controller;

import fi.tuni.bloggingapp.entity.BlogPost;
import fi.tuni.bloggingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.bloggingapp.repository.BlogPostRepository;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogRepo;

    @RequestMapping(value = "blog/posts", method = RequestMethod.GET)
    public List<BlogPost> getAllUsers() {
        return blogRepo.findAll();
    }

    @RequestMapping(value = "blog/add", method = RequestMethod.PUT)
    public BlogPost addBlogPost(@RequestBody BlogPost post) throws Exception {
        BlogPost newBlogPost;

        try {
            newBlogPost = blogRepo.save(post);
            return newBlogPost;
        } catch (DataIntegrityViolationException e) {
            throw new Exception("vituiks meni");
        }
    }
}
