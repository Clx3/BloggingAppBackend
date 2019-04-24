package fi.tuni.bloggingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.bloggingapp.entity.Like;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, Long> {
	
	public long countByblogpostId(long blogpostId);

	public List<Like> findAllByBlogpostId(long blogpostId);

}
