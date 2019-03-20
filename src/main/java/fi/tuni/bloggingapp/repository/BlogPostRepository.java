package fi.tuni.bloggingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.bloggingapp.entity.BlogPost;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

    public List<BlogPost> findAll();

}
