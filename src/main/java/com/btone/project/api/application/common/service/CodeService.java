package com.btone.project.api.application.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.common.domain.condition.CodeSearchCondition;
import com.btone.project.api.application.common.domain.model.Code;
import com.btone.project.api.application.common.domain.model.CodeGrp;
import com.btone.project.api.application.common.domain.repository.CodeGrpRepository;
import com.btone.project.api.application.common.domain.repository.CodeRepository;
import com.btone.project.api.application.common.domain.repository.CodeSearchRepository;
import com.btone.project.api.application.common.dto.request.CodeRequestDTO;
import com.btone.project.api.application.common.dto.response.CodeResponseDTO;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeService {

	private final CodeRepository repository;
	private final CodeSearchRepository codeSearchRepository;
	private final CodeGrpRepository codeGrpRepository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage methods(String method, CodeRequestDTO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input, searchKeys);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return update(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"), null);
	}

	public ResponseMessage create(CodeRequestDTO input, Map<String, Object> searchKeys) {
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("grpCd", input.getGrpCd());
			Optional<CodeGrp> optionalCodeGrp = codeGrpRepository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalCodeGrp.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.board.notexists"), null);
			}

			CodeGrp codeGrp = CodeGrp.builder()
					.grpCd(input.getGrpCd())
					.build();

			Code code = Code.builder()
					.codeGrp(codeGrp)
					.cd(input.getCd())
					.cdNm(input.getCdNm())
					.desc1(input.getDesc1())
					.desc2(input.getDesc2())
					.build();

			repository.save(code);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseMessage.ok(null, messageSource.getMessage("post.create.success"), null);
	}

	public ResponseMessage update(String method, CodeRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		Code code = null;
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("grpCd", input.getGrpCd());
			searchKeys.put("code", input.getCd());
			Optional<Code> optionalCode = repository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalCode.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.notexists"), null);
			}

			code = optionalCode.get();

			if(CommonMethods.UPDATE.getKey().equals(method)) {
				code.setCdNm(input.getCdNm());
				code.setDesc1(input.getDesc1());
				code.setDesc2(input.getDesc2());
				message = messageSource.getMessage("post.update.success");
			}else {
				code.setDelYn("Y");
				message = messageSource.getMessage("post.delete.success");
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseMessage.ok(null, message, null);
	}

	public ResponseMessage search(CodeRequestDTO input, Map<String, Object> searchKeys) {
		List<CodeResponseDTO> list = new ArrayList<>();
		try {
			list = codeSearchRepository.search(CodeSearchCondition.build(input.getGrpCd(), input.getGrpCdNm(), input.getCd(), input.getCdNm(), input.getDesc1(), input.getDesc2()));

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.notexists"), null);
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseMessage.ok(list, messageSource.getMessage("post.search.success"), null);
	}
}
