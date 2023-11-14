package com.btone.project.api.application.board.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {
	GENERAL("general"), COMMENTS("comments");

	private final String key;

	public static BoardType find(String key) {
        return Arrays.stream(values())
            .filter(boardType -> boardType.key.equals(key))
            .findAny()
            .orElse(GENERAL);
    }
}
