package com.btone.project.api.application.log.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.log.dto.response.QUserLogResponseDTO is a Querydsl Projection type for UserLogResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserLogResponseDTO extends ConstructorExpression<UserLogResponseDTO> {

    private static final long serialVersionUID = 1547183691L;

    public QUserLogResponseDTO(com.querydsl.core.types.Expression<Integer> userSn, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> pwd, com.querydsl.core.types.Expression<String> actvNm, com.querydsl.core.types.Expression<String> agreeYn, com.querydsl.core.types.Expression<String> rsPwdYn, com.querydsl.core.types.Expression<String> tmpPwd, com.querydsl.core.types.Expression<String> roleCd, com.querydsl.core.types.Expression<String> roleNm, com.querydsl.core.types.Expression<String> createdDate) {
        super(UserLogResponseDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, userSn, userId, pwd, actvNm, agreeYn, rsPwdYn, tmpPwd, roleCd, roleNm, createdDate);
    }

}

