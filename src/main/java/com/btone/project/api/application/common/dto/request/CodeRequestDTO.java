package com.btone.project.api.application.common.dto.request;

import lombok.Data;

@Data
public class CodeRequestDTO {
	private String grpCd;
	private String grpCdNm;
	private String cd;
	private String cdNm;
	private String desc1;
	private String desc2;
	private int sort;
	private String useYn;
	private String delYn;
}
