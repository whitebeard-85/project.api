package com.btone.project.api.application.common.domain.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CodeGrpSearchCondition {

	String grpCd;
	String grpCdNm;
	String desc1;
	String desc2;

	public static CodeGrpSearchCondition build(String grpCd, String grpCdNm, String desc1, String desc2) {
		CodeGrpSearchCondition searchCondition = new CodeGrpSearchCondition();
		searchCondition.grpCd = grpCd;
		searchCondition.grpCdNm = grpCdNm;
		searchCondition.desc1 = desc1;
		searchCondition.desc2 = desc2;
		return searchCondition;
	}
}
