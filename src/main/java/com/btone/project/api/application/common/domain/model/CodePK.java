package com.btone.project.api.application.common.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CodePK implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String grpCd;
    private String cd;
}
