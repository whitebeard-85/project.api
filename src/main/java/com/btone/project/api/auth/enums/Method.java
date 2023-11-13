package com.btone.project.api.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Method {
	// Account
	CHECKID("check-id"), SIGNUP("sign-up"), EDIT("edit"), CANCEL("cancel"), SEARCH("search"), RESETPASSWORD("reset-password");

	private final String key;
}
