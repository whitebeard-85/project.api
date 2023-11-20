package com.btone.project.api.application.auth.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.btone.project.api.application.auth.dto.response.QRoleResponseDTO is a Querydsl Projection type for RoleResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRoleResponseDTO extends ConstructorExpression<RoleResponseDTO> {

    private static final long serialVersionUID = -1091433246L;

    public QRoleResponseDTO(com.querydsl.core.types.Expression<String> roleCd, com.querydsl.core.types.Expression<String> roleNm, com.querydsl.core.types.Expression<String> roleDesc, com.querydsl.core.types.Expression<String> createdDate, com.querydsl.core.types.Expression<String> modifiedDate) {
        super(RoleResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class}, roleCd, roleNm, roleDesc, createdDate, modifiedDate);
    }

}

