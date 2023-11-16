package com.btone.project.api.application.board.domain.repository;

import static com.btone.project.api.application.board.entity.QBoard.board;
import static com.btone.project.api.application.board.entity.QPost.post;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.board.domain.condition.PostSearchCondition;
import com.btone.project.api.application.board.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class PostSearchAndFilterRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public PostSearchAndFilterRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<Post> search(PostSearchCondition postSearchCondition){
		return jpaQueryFactory.select(post)
				.from(post).leftJoin(board)
				.on(post.board.boardSn.eq(board.boardSn))
				.where(
					board.boardSn.eq(postSearchCondition.getBoardSn()),
					post.postSn.eq(postSearchCondition.getPostSn())
				)
				.orderBy(board.boardSn.asc(), post.postSn.asc())
				.fetch();
	}
}
