package cloneproject.Instagram.domain.feed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cloneproject.Instagram.domain.feed.dto.PostImageDto;
import cloneproject.Instagram.domain.feed.entity.Post;
import cloneproject.Instagram.domain.feed.entity.PostImage;
import cloneproject.Instagram.domain.feed.repository.jdbc.PostImageRepositoryJdbc;

public interface PostImageRepository extends JpaRepository<PostImage, Long>, PostImageRepositoryJdbc {

	List<PostImage> findAllByPost(Post post);

	@Query("select new cloneproject.Instagram.domain.feed.dto.PostImageDto("
		+ "pi.post.id, pi.id, pi.image.imageUrl, pi.altText) "
		+ "from PostImage pi "
		+ "where pi.post.id in :postIds")
	List<PostImageDto> findAllPostImageDtoByPostIdIn(@Param(value = "postIds") List<Long> postIds);

	List<PostImage> findAllByPostIdIn(List<Long> postIds);

}
