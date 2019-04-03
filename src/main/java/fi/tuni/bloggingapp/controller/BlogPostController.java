package fi.tuni.bloggingapp.controller;

import fi.tuni.bloggingapp.entity.BlogPost;
import fi.tuni.bloggingapp.entity.Comment;
import fi.tuni.bloggingapp.entity.User;
import fi.tuni.bloggingapp.entity.Comment;
import fi.tuni.bloggingapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import fi.tuni.bloggingapp.repository.BlogPostRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogRepo;

    @Autowired
    private CommentRepository commentRepo;

    @RequestMapping(value = "blog/posts", method = RequestMethod.GET)
    public List<BlogPost> getAllUsers() {
        return blogRepo.findAll();
    }

    @RequestMapping(value = "blog/add", method = RequestMethod.PUT)
    public BlogPost addBlogPost(@RequestBody BlogPost post) throws Exception {
        BlogPost newBlogPost;

        try {
            newBlogPost = blogRepo.save(new BlogPost(post.getAuthor(), post.getTitle(), post.getContent()));
            return newBlogPost;
        } catch (DataIntegrityViolationException e) {
            throw new Exception(e.getStackTrace().toString());
        }
    }

    @RequestMapping(value = "blog/{blogId}", method = RequestMethod.GET)
    public BlogPost getBeersByIds(@PathVariable long blogId) throws Exception{
        Optional<BlogPost> optPost = blogRepo.findById(blogId);
        if(optPost.isPresent()){
            return optPost.get();
        } else {
            return null;
        }
    }

    @RequestMapping(value = "blog/comments/add", method = RequestMethod.PUT)
    public Comment addComment(@RequestBody Comment post) throws Exception {
        Comment newComment;

        try {
            newComment = commentRepo.save(new Comment(post.getAuthor(), post.getContent(), post.getPostId()));
            return newComment;
        } catch(DataIntegrityViolationException e) {
            throw e;
        }
    }

    @RequestMapping(value = "blog/comments/{postId}", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable long postId){
        return commentRepo.findAllByPostId(postId);
    }

    @RequestMapping(value = "blog/comments/", method = RequestMethod.GET)
    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

}
