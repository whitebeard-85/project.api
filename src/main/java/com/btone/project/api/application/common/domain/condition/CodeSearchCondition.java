package com.btone.project.api.application.common.domain.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CodeSearchCondition {

	private String grpCd;
	private String grpCdNm;
	private String code;
	private String codeNm;
	private String desc1;
	private String desc2;

	public static CodeSearchCondition build(String grpCd, String grpCdNm, String code, String codeNm, String desc1, String desc2) {
		CodeSearchCondition searchCondition = new CodeSearchCondition();
		searchCondition.grpCd = grpCd;
		searchCondition.grpCdNm = grpCdNm;
		searchCondition.code = code;
		searchCondition.codeNm = codeNm;
		searchCondition.desc1 = desc1;
		searchCondition.desc2 = desc2;
		return searchCondition;
	}
}
