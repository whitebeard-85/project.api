package com.btone.project.api.application.board.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.board.dto.response.QPostResponseDTO is a Querydsl Projection type for PostResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostResponseDTO extends ConstructorExpression<PostResponseDTO> {

    private static final long serialVersionUID = 1405958486L;

    public QPostResponseDTO(com.querydsl.core.types.Expression<Integer> boardSn, com.querydsl.core.types.Expression<String> boardType, com.querydsl.core.types.Expression<String> boardNm, com.querydsl.core.types.Expression<String> boardDesc, com.querydsl.core.types.Expression<Integer> postSn, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<String> delYn, com.querydsl.core.types.Expression<String> actvNm) {
        super(PostResponseDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, int.class, String.class, String.class, String.class, String.class, String.class}, boardSn, boardType, boardNm, boardDesc, postSn, title, contents, writer, delYn, actvNm);
    }

}

