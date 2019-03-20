package fi.tuni.bloggingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.bloggingapp.entity.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

}
