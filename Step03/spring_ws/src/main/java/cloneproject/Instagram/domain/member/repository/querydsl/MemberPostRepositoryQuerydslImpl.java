package cloneproject.Instagram.domain.member.repository.querydsl;

import static cloneproject.Instagram.domain.feed.entity.QBookmark.*;
import static cloneproject.Instagram.domain.feed.entity.QPost.*;
import static cloneproject.Instagram.domain.feed.entity.QPostImage.*;
import static cloneproject.Instagram.domain.feed.entity.QPostLike.*;
import static cloneproject.Instagram.domain.feed.entity.QPostTag.*;
import static cloneproject.Instagram.domain.member.entity.QMember.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import cloneproject.Instagram.domain.feed.dto.MemberPostDto;
import cloneproject.Instagram.domain.feed.dto.QMemberPostDto;

@RequiredArgsConstructor
public class MemberPostRepositoryQuerydslImpl implements MemberPostRepositoryQuerydsl {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<MemberPostDto> findMemberPostDtoPageByLoginMemberIdAndTargetUsername(Long loginMemberId, String username, Pageable pageable) {
		final List<MemberPostDto> posts = queryFactory
			.select(new QMemberPostDto(
				post.id,
				post.member,
				post.postImages.size().gt(1),
				post.likeFlag,
				existPostLikeWherePostEqAndMemberIdEq(loginMemberId),
				post.comments.size(),
				post.postLikes.size()))
			.from(post)
			.innerJoin(post.member, member)
			.where(post.member.username.eq(username))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(post.id.desc())
			.distinct()
			.fetch();

		final long total = queryFactory
			.selectFrom(post)
			.where(post.member.username.eq(username))
			.fetch().size();
		return new PageImpl<>(posts, pageable, total);
	}

	@Override
	public Page<MemberPostDto> findMemberSavedPostDtoPageByLoginMemberId(Long loginMemberId, Pageable pageable) {
		final List<MemberPostDto> posts = queryFactory
			.select(new QMemberPostDto(
				bookmark.post.id,
				bookmark.post.member,
				bookmark.post.postImages.size().gt(1),
				post.likeFlag,
				existPostLikeWherePostEqAndMemberIdEq(loginMemberId),
				bookmark.post.comments.size(),
				bookmark.post.postLikes.size()))
			.from(bookmark)
			.innerJoin(bookmark.post, post)
			.innerJoin(bookmark.post.member, member)
			.where(bookmark.member.id.eq(loginMemberId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(bookmark.post.id.desc())
			.distinct()
			.fetch();

		final long total = queryFactory
			.selectFrom(bookmark)
			.where(bookmark.member.id.eq(loginMemberId))
			.fetch().size();
		return new PageImpl<>(posts, pageable, total);
	}

	@Override
	public Page<MemberPostDto> findMemberTaggedPostDtoPageByLoginMemberIdAndTargetUsername(Long loginMemberId, String username, Pageable pageable) {
		final List<MemberPostDto> posts = queryFactory
			.select(new QMemberPostDto(
				postTag.postImage.post.id,
				postTag.postImage.post.member,
				postTag.postImage.post.postImages.size().gt(1),
				postTag.postImage.post.likeFlag,
				existPostLikeWherePostEqAndMemberIdEq(loginMemberId),
				postTag.postImage.post.comments.size(),
				postTag.postImage.post.postLikes.size()))
			.from(postTag)
			.innerJoin(postTag.postImage, postImage)
			.innerJoin(postTag.postImage.post, post)
			.innerJoin(postTag.postImage.post.member, member)
			.where(postTag.tag.username.eq(username))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(postTag.postImage.post.id.desc())
			.distinct()
			.fetch();

		final long total = queryFactory
			.selectFrom(postTag)
			.where(postTag.tag.username.eq(username))
			.fetch().size();
		return new PageImpl<>(posts, pageable, total);
	}

	private BooleanExpression existPostLikeWherePostEqAndMemberIdEq(Long id) {
		return JPAExpressions
			.selectFrom(postLike)
			.where(postLike.post.eq(post).and(postLike.member.id.eq(id)))
			.exists();
	}

}
