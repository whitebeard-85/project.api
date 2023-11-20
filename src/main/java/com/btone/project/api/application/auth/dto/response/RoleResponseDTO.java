package com.btone.project.api.application.auth.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class RoleResponseDTO {
	private String roleCd;
    private String roleNm;
    private String roleDesc;
    private String createdDate;
	private String modifiedDate;

    @QueryProjection
	public RoleResponseDTO(String roleCd, String roleNm, String roleDesc, String createdDate, String modifiedDate) {
		this.roleCd = roleCd;
		this.roleNm = roleNm;
		this.roleDesc = roleDesc;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}
