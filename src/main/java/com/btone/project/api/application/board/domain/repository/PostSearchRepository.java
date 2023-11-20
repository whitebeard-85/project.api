package com.btone.project.api.application.board.domain.repository;

import static com.btone.project.api.application.auth.domain.model.QUser.user;
import static com.btone.project.api.application.board.domain.model.QBoard.board;
import static com.btone.project.api.application.board.domain.model.QPost.post;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.board.domain.condition.PostSearchCondition;
import com.btone.project.api.application.board.dto.response.PostResponseDTO;
import com.btone.project.api.application.board.dto.response.QPostResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class PostSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public PostSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<PostResponseDTO> search(PostSearchCondition postSearchCondition){
//		return jpaQueryFactory.select(new QPostResponseDTO(board.boardSn, post.postSn, post.title, post.contents, user.actvNm, post.delYn, post.createdDate, post.modifiedDate))
//				.from(post).leftJoin(board)
//				.on(post.board.boardSn.eq(board.boardSn))
//				.leftJoin(user)
//				.on(post.writer.eq(user.userId))
//				.where(
//					allEq(postSearchCondition.getBoardSn(), postSearchCondition.getPostSn(), postSearchCondition.getTitle(), postSearchCondition.getContents(), postSearchCondition.getWriter())
//				)
//				.orderBy(board.boardSn.asc(), post.postSn.asc())
//				.fetch();
		return null;
	}

	private Predicate boardSnEq(Integer boardSn) {
	    return boardSn != null ? board.boardSn.eq(boardSn) : null;
	}

	private Predicate postSnEq(Integer postSn) {
	    return postSn != null ? post.postSn.eq(postSn) : null;
	}

	private Predicate titleContains(String title) {
		return title != null ? post.title.contains(title) : null;
	}

	private Predicate contentsContains(String contents) {
		return contents != null ? post.contents.contains(contents) : null;
	}

	private Predicate writerContains(String writer) {
		return writer != null ? user.actvNm.contains(writer) : null;
	}

	private BooleanBuilder allEq(Integer boardSn, Integer postSn, String title, String contents, String writer) {
		BooleanBuilder builder = new BooleanBuilder();
	    return builder.and(boardSnEq(boardSn)).and(postSnEq(postSn)).and(titleContains(title)).and(contentsContains(contents)).and(writerContains(writer));
	}
}
