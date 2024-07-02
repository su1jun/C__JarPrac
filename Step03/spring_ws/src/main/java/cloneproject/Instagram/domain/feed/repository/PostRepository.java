package cloneproject.Instagram.domain.feed.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cloneproject.Instagram.domain.feed.entity.Post;
import cloneproject.Instagram.domain.feed.repository.querydsl.PostRepositoryQuerydsl;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryQuerydsl {

	@Query("select p from Post p join fetch p.member where p.id = :postId")
	Optional<Post> findWithMemberById(@Param("postId") Long postId);

	List<Post> findTop3ByMemberIdOrderByIdDesc(Long memberId);

}
