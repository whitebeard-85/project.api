package com.btone.project.api.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String key;
}
