package com.btone.project.api.application.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserMethods {
	CHECKID("check-id"), SIGNUP("sign-up"), EDIT("edit"), CANCEL("cancel"), RESETPASSWORD("reset-password");

	private final String key;
}
