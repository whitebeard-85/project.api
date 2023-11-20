package com.btone.project.api.application.common.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.common.domain.condition.CodeGrpSearchCondition;
import com.btone.project.api.application.common.dto.response.CodeGrpResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class CodeGrpSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CodeGrpSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<CodeGrpResponseDTO> search(CodeGrpSearchCondition codeGrpSearchCondition){

	}
}
