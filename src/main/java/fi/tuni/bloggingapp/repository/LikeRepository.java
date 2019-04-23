package fi.tuni.bloggingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.bloggingapp.entity.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {
	
	public long countByblogpostId(long blogpostId);

}
