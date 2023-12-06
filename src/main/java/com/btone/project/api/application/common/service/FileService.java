package com.btone.project.api.application.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.btone.project.api.application.common.dto.request.FileRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

	@Value("${file.uploadPath}")
	private String uploadPath;

	public FileRequestDTO uploadFile(MultipartFile file) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String path = sdf.format(new Date());
		String root = uploadPath + "/" + path;

		File fileCheck = new File(root);

		if(!fileCheck.exists()) fileCheck.mkdirs();

		String originalFileName = file.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		String uploadFileName = UUID.randomUUID().toString() + ext;

		FileRequestDTO fileRequestDTO = new FileRequestDTO();
		fileRequestDTO.setUploadPath(uploadPath);
		fileRequestDTO.setOriginalFileName(originalFileName);
		fileRequestDTO.setUploadFileName(uploadFileName);
		fileRequestDTO.setFileSize(file.getSize());
		fileRequestDTO.setFileExt(ext);

		File uploadFile = new File(root + "\\" + uploadFileName);

		file.transferTo(uploadFile);

		return fileRequestDTO;
	}

	public List<FileRequestDTO> uploadFileList(List<MultipartFile> multipartFileList) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String path = sdf.format(new Date());
		String root = uploadPath + "/" + path;

		File fileCheck = new File(root);

		if(!fileCheck.exists()) fileCheck.mkdirs();

		List<FileRequestDTO> fileList = new ArrayList<>();

		for(MultipartFile file : multipartFileList) {
			String originalFileName = file.getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			String uploadFileName = UUID.randomUUID().toString() + ext;

			FileRequestDTO fileRequestDTO = new FileRequestDTO();
			fileRequestDTO.setUploadPath(uploadPath);
			fileRequestDTO.setOriginalFileName(originalFileName);
			fileRequestDTO.setUploadFileName(uploadFileName);
			fileRequestDTO.setFileSize(file.getSize());
			fileRequestDTO.setFileExt(ext);

			fileList.add(fileRequestDTO);
		}

		try {
			for(int i = 0; i < multipartFileList.size(); i++) {
				File uploadFile = new File(root + "\\" + fileList.get(i).getUploadFileName());
				multipartFileList.get(i).transferTo(uploadFile);
			}

		} catch (Exception e) {
			// 만약 업로드 실패하면 파일 삭제
			for(int i = 0; i < multipartFileList.size(); i++) {
				new File(root + "\\" + fileList.get(i).getUploadFileName()).delete();
			}

			throw e;
		}

		return fileList;
	}
}
