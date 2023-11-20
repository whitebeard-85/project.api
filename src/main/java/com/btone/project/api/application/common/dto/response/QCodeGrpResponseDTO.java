package com.btone.project.api.application.common.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.common.dto.response.QCodeGrpResponseDTO is a Querydsl Projection type for CodeGrpResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCodeGrpResponseDTO extends ConstructorExpression<CodeGrpResponseDTO> {

    private static final long serialVersionUID = -1908464593L;

    public QCodeGrpResponseDTO(com.querydsl.core.types.Expression<String> grpCd, com.querydsl.core.types.Expression<String> grpCdNm, com.querydsl.core.types.Expression<String> desc1, com.querydsl.core.types.Expression<String> desc2, com.querydsl.core.types.Expression<Integer> sort, com.querydsl.core.types.Expression<String> useYn, com.querydsl.core.types.Expression<String> delYn, com.querydsl.core.types.Expression<String> createDate, com.querydsl.core.types.Expression<String> modifiedDate) {
        super(CodeGrpResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, int.class, String.class, String.class, String.class, String.class}, grpCd, grpCdNm, desc1, desc2, sort, useYn, delYn, createDate, modifiedDate);
    }

}

