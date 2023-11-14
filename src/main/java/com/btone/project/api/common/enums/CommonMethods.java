package com.btone.project.api.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonMethods {
	SEARCH("search"), CREATE("create"), UPDATE("update"), DELETE("delete");

	private final String key;
}
