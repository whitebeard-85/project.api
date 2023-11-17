package com.btone.project.api.application.auth.dto.request;

import lombok.Data;

@Data
public class RoleRequestDTO {
	private String roleCd;
    private String roleNm;
    private String roleDesc;
}
