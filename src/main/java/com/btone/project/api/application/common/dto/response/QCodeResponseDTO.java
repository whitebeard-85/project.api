package com.btone.project.api.application.common.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.common.dto.response.QCodeResponseDTO is a Querydsl Projection type for CodeResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCodeResponseDTO extends ConstructorExpression<CodeResponseDTO> {

    private static final long serialVersionUID = -1968035544L;

    public QCodeResponseDTO(com.querydsl.core.types.Expression<String> grpCd, com.querydsl.core.types.Expression<String> cd, com.querydsl.core.types.Expression<String> cdNm, com.querydsl.core.types.Expression<String> desc1, com.querydsl.core.types.Expression<String> desc2, com.querydsl.core.types.Expression<Integer> sort, com.querydsl.core.types.Expression<String> useYn, com.querydsl.core.types.Expression<String> delYn, com.querydsl.core.types.Expression<String> createDate, com.querydsl.core.types.Expression<String> modifiedDate) {
        super(CodeResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, int.class, String.class, String.class, String.class, String.class}, grpCd, cd, cdNm, desc1, desc2, sort, useYn, delYn, createDate, modifiedDate);
    }

}

