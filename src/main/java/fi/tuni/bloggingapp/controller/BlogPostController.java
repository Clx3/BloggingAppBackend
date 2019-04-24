package fi.tuni.bloggingapp.controller;

import fi.tuni.bloggingapp.entity.*;
import fi.tuni.bloggingapp.entity.Comment;
import fi.tuni.bloggingapp.repository.CommentRepository;
import fi.tuni.bloggingapp.repository.LikeRepository;
import fi.tuni.bloggingapp.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import fi.tuni.bloggingapp.repository.BlogPostRepository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Handles requests regarding blog posts. Handles comment requests also.
 *
 * @author Joonas Salojarvi & Teemu Salminen
 * @version 2019.04.22
 * @since 0.1
 */
@RestController
public class BlogPostController {

    /**
     * Autowired repo for blog posts
     */
    @Autowired
    private BlogPostRepository blogRepo;

    /**
     * Autowired repo for comments
     */
    @Autowired
    private CommentRepository commentRepo;

    /**
     * Autowired repo for tokens. Used to check auth.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Autowired repo for likes.
     */
    @Autowired
    private LikeRepository likeRepo;

    /**
     * Method for getting all blog posts.
     * @return all blog posts
     */
    @RequestMapping(value = "blog/posts", method = RequestMethod.GET)
    public List<BlogPost> getAllUsers() {
        return blogRepo.findAll();
    }

    /**
     * Method for adding blog posts.
     *
     * @param post Blog post to add to db
     * @param token User token, to check if user is authorized
     * @return BlogPost added
     * @throws Exception unauthorized user or data integrity issue
     */
    @RequestMapping(value = "blog/add", method = RequestMethod.PUT)
    public BlogPost addBlogPost(@RequestBody BlogPost post, @RequestHeader(value="Token") String token) throws Exception {
        if(!(tokenService.containsAdmin(token))){
            throw new UnauthorizedTokenException();
        }

        BlogPost newBlogPost;

        try {
            newBlogPost = blogRepo.save(new BlogPost(post.getAuthor(), post.getTitle(), post.getContent()));
            return newBlogPost;
        } catch (DataIntegrityViolationException e) {
            throw new Exception(e.getStackTrace().toString());
        }
    }

    /**
     * Get a single blog post.
     *
     * @param blogId ID of blog post
     * @return blog post or null, if not found
     */
    @RequestMapping(value = "blog/{blogId}", method = RequestMethod.GET)
    public BlogPost getBeersByIds(@PathVariable long blogId){
        Optional<BlogPost> optPost = blogRepo.findById(blogId);
        if(optPost.isPresent()){
            return optPost.get();
        } else {
            return null;
        }
    }
    
    /**
     * Request mapping method to delete a blogpost and all comments added to it.
     * 
     * @param blogId Id of the blogpost to be deleted.
     */
    @RequestMapping(value = "blog/{blogId}", method = RequestMethod.DELETE)
    public void deleteBlogPost(@PathVariable long blogId) {
    	/* Find all comments and delete them first */
    	List<Comment> comments = commentRepo.findAllByPostId(blogId);
    	List<Like> likes = likeRepo.findAllByBlogpostId(blogId);
    	
    	if(comments.size() > 0)
    		commentRepo.deleteAll(comments);

    	if(likes.size() > 0)
    	    likeRepo.deleteAll(likes);
    	
    	/* Deleting the blogpost */
    	blogRepo.deleteById(blogId);
    }
    
    /**
     * Method to add comments. Any user is able to comments, so doesn't check for auth.
     * @param post Comment to be added
     * @return added comment
     */
    @RequestMapping(value = "blog/comments/add", method = RequestMethod.PUT)
    public Comment addComment(@RequestBody Comment post) {
        Comment newComment;

        try {
            newComment = commentRepo.save(new Comment(post.getAuthor(), post.getContent(), post.getPostId()));
            return newComment;
        } catch(DataIntegrityViolationException e) {
            throw e;
        }
    }

    /**
     * Request all comments for a single blog post.
     * @param postId Blog posts ID to search comments for
     * @return All comments for given blog post
     */
    @RequestMapping(value = "blog/comments/{postId}", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable long postId){
        return commentRepo.findAllByPostId(postId);
    }

    /**
     * Get all comments and return them
     * @return all comments
     */
    @RequestMapping(value = "blog/comments/", method = RequestMethod.GET)
    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

    /**
     * Exception to throw when user isn't authorized for a request.
     *
     * @author Joonas Salojarvi
     * @version 2019.04.22
     * @since 0.1
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    class UnauthorizedTokenException extends Exception{
        public UnauthorizedTokenException(){
            super("Invalid token");
        }
    }

}
