package com.btone.project.api.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Method {
	// Common
	SEARCH("search"),

	// Account
	CHECKID("check-id"), SIGNUP("sign-up"), EDIT("edit"), CANCEL("cancel"), RESETPASSWORD("reset-password"),

	// Role
	CREATE("create"), UPDATE("update"), DELETE("delete");

	private final String key;
}
