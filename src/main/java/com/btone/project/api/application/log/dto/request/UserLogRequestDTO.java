package com.btone.project.api.application.log.dto.request;

import lombok.Data;

@Data
public class UserLogRequestDTO {
	private Integer userSn;		// 사용자일련번호
	private String userId;		// 사용자아이디
}
