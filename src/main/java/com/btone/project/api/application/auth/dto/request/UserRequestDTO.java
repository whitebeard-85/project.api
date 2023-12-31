package com.btone.project.api.application.auth.dto.request;

import lombok.Data;

@Data
public class UserRequestDTO {
	private Integer userSn;		// 사용자일련번호
	private String userId;		// 사용자아이디
	private String pwd;			// 비밀번호
	private String actvNm;		// 활동명
	private String agreeYn;		// 약관동의여부
	private String rsPwdYn;		// 비밀번호초기화여부
	private String tmpPwd;		// 임시비밀번호(비밀번호초기화시 발급)
	private String delYn;		// 삭제여부
    private String roleCd;
    private String roleNm;
}
