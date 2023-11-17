package com.btone.project.api.application.auth.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer userSn;		// 사용자일련번호
	private String userId;		// 사용자아이디
	private String pwd;			// 비밀번호
	private String actvNm;		// 활동명
	private String agreeYn;		// 약관동의여부
	private String rsPwdYn;		// 비밀번호초기화여부
	private String tmpPwd;		// 임시비밀번호(비밀번호초기화시 발급)
	private String roleCd;
    private String roleNm;

    @QueryProjection
	public UserResponseDTO(Integer userSn, String userId, String pwd, String actvNm, String agreeYn, String rsPwdYn,
			String tmpPwd, String roleCd, String roleNm) {
		this.userSn = userSn;
		this.userId = userId;
		this.pwd = pwd;
		this.actvNm = actvNm;
		this.agreeYn = agreeYn;
		this.rsPwdYn = rsPwdYn;
		this.tmpPwd = tmpPwd;
		this.roleCd = roleCd;
		this.roleNm = roleNm;
	}


}
