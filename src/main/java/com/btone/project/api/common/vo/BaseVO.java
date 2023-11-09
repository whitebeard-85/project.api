package com.btone.project.api.common.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseVO {
	private String regDate;		// 등록일시
	private String regr;		// 등록자
	private String modDate;		// 수정일시
	private String modr;		// 수정자
}
