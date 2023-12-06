package com.btone.project.api.application.common.domain.repository;

import static com.btone.project.api.application.common.domain.model.QCodeGrp.codeGrp;
import static com.btone.project.api.application.common.domain.model.QCode.code;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.common.domain.condition.CodeSearchCondition;
import com.btone.project.api.application.common.dto.response.CodeResponseDTO;
import com.btone.project.api.application.common.dto.response.QCodeResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class CodeSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CodeSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public CodeResponseDTO search(CodeSearchCondition codeGrpSearchCondition){
		return jpaQueryFactory.select(new QCodeResponseDTO(code.grpCd, code.cd, code.cdNm, code.desc1, code.desc2, code.sort, code.useYn, code.delYn, code.createdDate, code.modifiedDate))
				.from(code).leftJoin(codeGrp)
				.on(code.grpCd.eq(codeGrp.grpCd))
				.fetchOne();
	}

	public List<CodeResponseDTO> searchList(CodeSearchCondition codeGrpSearchCondition){
		return jpaQueryFactory.select(new QCodeResponseDTO(code.grpCd, code.cd, code.cdNm, code.desc1, code.desc2, code.sort, code.useYn, code.delYn, code.createdDate, code.modifiedDate))
				.from(code).leftJoin(codeGrp)
				.on(code.grpCd.eq(codeGrp.grpCd))
				.fetch();
	}
}
