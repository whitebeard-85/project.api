package com.btone.project.api.application.common.domain.repository;

import static com.btone.project.api.application.common.domain.model.QCodeGrp.codeGrp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.common.domain.condition.CodeGrpSearchCondition;
import com.btone.project.api.application.common.dto.response.CodeGrpResponseDTO;
import com.btone.project.api.application.common.dto.response.QCodeGrpResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class CodeGrpSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CodeGrpSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<CodeGrpResponseDTO> search(CodeGrpSearchCondition codeGrpSearchCondition){
		return jpaQueryFactory.select(new QCodeGrpResponseDTO(codeGrp.grpCd, codeGrp.grpCdNm, codeGrp.desc1, codeGrp.desc2, codeGrp.sort, codeGrp.useYn, codeGrp.delYn, codeGrp.createdDate, codeGrp.modifiedDate))
				.from(codeGrp)
				.fetch();
	}
}
