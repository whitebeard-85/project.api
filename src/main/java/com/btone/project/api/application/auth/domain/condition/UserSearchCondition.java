package com.btone.project.api.application.auth.domain.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSearchCondition {

	private String actvNm;
	private String roleCd;
	private String roleNm;

	public static UserSearchCondition build(String actvNm, String roleCd, String roleNm) {
		UserSearchCondition searchCondition = new UserSearchCondition();
		searchCondition.actvNm = actvNm;
		searchCondition.roleCd = roleCd;
		searchCondition.roleNm = roleNm;
		return searchCondition;
	}
}
