package com.btone.project.api.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthMethods {
	CHECKID("check-id"), SIGNUP("sign-up"), EDIT("edit"), CANCEL("cancel"), RESETPASSWORD("reset-password");

	private final String key;
}
