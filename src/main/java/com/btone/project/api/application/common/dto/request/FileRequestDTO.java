package com.btone.project.api.application.common.dto.request;

import lombok.Data;

@Data
public class FileRequestDTO {
	private Integer fileSn;
	private Integer parentSn;
	private String parentId;
	private String uploadPath;
	private String originalFileName;
	private String uploadFileName;
	private long fileSize;
	private String fileExt;
}
