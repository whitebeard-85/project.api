package com.btone.project.api.application.auth.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.auth.dto.response.QUserResponseDTO is a Querydsl Projection type for UserResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserResponseDTO extends ConstructorExpression<UserResponseDTO> {

    private static final long serialVersionUID = 845916781L;

    public QUserResponseDTO(com.querydsl.core.types.Expression<Integer> userSn, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> pwd, com.querydsl.core.types.Expression<String> actvNm, com.querydsl.core.types.Expression<String> agreeYn, com.querydsl.core.types.Expression<String> rsPwdYn, com.querydsl.core.types.Expression<String> tmpPwd, com.querydsl.core.types.Expression<String> roleCd, com.querydsl.core.types.Expression<String> roleNm) {
        super(UserResponseDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, userSn, userId, pwd, actvNm, agreeYn, rsPwdYn, tmpPwd, roleCd, roleNm);
    }

}

