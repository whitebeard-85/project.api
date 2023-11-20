package com.btone.project.api.application.common.domain.repository;

import static com.btone.project.api.application.common.domain.model.QCodeGrp.codeGrp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.common.domain.condition.CodeGrpSearchCondition;
import com.btone.project.api.application.common.dto.response.CodeGrpResponseDTO;
import com.btone.project.api.application.common.dto.response.QCodeGrpResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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
				.where(
					allEq(codeGrpSearchCondition.getGrpCd(), codeGrpSearchCondition.getGrpCdNm(), codeGrpSearchCondition.getDesc1(), codeGrpSearchCondition.getDesc2(), codeGrpSearchCondition.getUseYn())
				)
				.fetch();
	}

	private Predicate grpCdEq(String grpCd) {
	    return grpCd != null ? codeGrp.grpCd.eq(grpCd) : null;
	}

	private Predicate grpCdNmContains(String grpCdNm) {
		return grpCdNm != null ? codeGrp.grpCdNm.contains(grpCdNm) : null;
	}

	private Predicate desc1Contains(String desc1) {
		return desc1 != null ? codeGrp.grpCdNm.contains(desc1) : null;
	}

	private Predicate desc2Contains(String desc2) {
		return desc2 != null ? codeGrp.grpCdNm.contains(desc2) : null;
	}

	private Predicate useYnEq(String useYn) {
		return useYn != null ? codeGrp.useYn.eq(useYn) : null;
	}

	private BooleanBuilder allEq(String grpCd, String grpCdNm, String desc1, String desc2, String useYn) {
		BooleanBuilder builder = new BooleanBuilder();
	    return builder.and(grpCdEq(grpCd)).and(grpCdNmContains(grpCdNm)).and(desc1Contains(desc1)).and(desc2Contains(desc2)).and(useYnEq(useYn));
	}
}
