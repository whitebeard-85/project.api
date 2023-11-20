package com.btone.project.api.application.common.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.common.domain.condition.CodeSearchCondition;
import com.btone.project.api.application.common.dto.response.CodeResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class CodeSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CodeSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<CodeResponseDTO> search(CodeSearchCondition codeGrpSearchCondition){

	}
}
