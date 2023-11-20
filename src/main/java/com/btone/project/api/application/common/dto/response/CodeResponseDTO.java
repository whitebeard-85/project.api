package com.btone.project.api.application.common.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CodeResponseDTO {

	private String grpCd;
	private String cd;
	private String cdNm;
	private String desc1;
	private String desc2;
	private int sort;
	private String useYn;
	private String delYn;
	private String createDate;
	private String modifiedDate;

	@QueryProjection
	public CodeResponseDTO(String grpCd, String cd, String cdNm, String desc1, String desc2, int sort, String useYn,
			String delYn, String createDate, String modifiedDate) {
		this.grpCd = grpCd;
		this.cd = cd;
		this.cdNm = cdNm;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.sort = sort;
		this.useYn = useYn;
		this.delYn = delYn;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
}
