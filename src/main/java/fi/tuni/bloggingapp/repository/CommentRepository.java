package fi.tuni.bloggingapp.repository;

import fi.tuni.bloggingapp.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findAll();

    public Comment findById(long id);

    public List<Comment> findAllByPostId(long postId);
}
