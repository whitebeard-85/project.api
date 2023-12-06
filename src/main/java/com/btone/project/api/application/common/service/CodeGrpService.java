package com.btone.project.api.application.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.common.domain.condition.CodeGrpSearchCondition;
import com.btone.project.api.application.common.domain.model.CodeGrp;
import com.btone.project.api.application.common.domain.repository.CodeGrpRepository;
import com.btone.project.api.application.common.domain.repository.CodeGrpSearchRepository;
import com.btone.project.api.application.common.dto.request.CodeGrpRequestDTO;
import com.btone.project.api.application.common.dto.response.CodeGrpResponseDTO;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeGrpService {

	private final CodeGrpRepository repository;
	private final CodeGrpSearchRepository codeGrpSearchRepository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage methods(String method, CodeGrpRequestDTO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return update(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"), null);
	}

	@Transactional
	public ResponseMessage create(CodeGrpRequestDTO input) {
		CodeGrp codeGrp = CodeGrp.builder()
				.grpCd(input.getGrpCd())
				.grpCdNm(input.getGrpCdNm())
				.desc1(input.getDesc1())
				.desc2(input.getDesc2())
				.build();

		repository.save(codeGrp);

		return ResponseMessage.ok(null, messageSource.getMessage("board.create.success"), null);
	}

	@Transactional
	public ResponseMessage update(String method, CodeGrpRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		searchKeys.put("delYn", "N");
		searchKeys.put("grpCd", input.getGrpCd());
		Optional<CodeGrp> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalRole.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"), null);
		}

		CodeGrp codeGrp = optionalRole.get();

		if(CommonMethods.UPDATE.getKey().equals(method)) {
			codeGrp.setGrpCdNm(input.getGrpCdNm());
			codeGrp.setDesc1(input.getDesc1());
			codeGrp.setDesc2(input.getDesc2());
			message = messageSource.getMessage("board.update.success");
		}else {
			codeGrp.setDelYn("Y");
			message = messageSource.getMessage("board.delete.success");
		}

		return ResponseMessage.ok(null, message, null);
	}

	@Transactional
	public ResponseMessage search(CodeGrpRequestDTO input, Map<String, Object> searchKeys) {
		List<CodeGrpResponseDTO> list = codeGrpSearchRepository.search(CodeGrpSearchCondition.build(input.getGrpCd(), input.getGrpCdNm(), input.getDesc1(), input.getDesc2(), input.getUseYn()));

		if(list.size() == 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"), null);
		}

		return ResponseMessage.ok(list, messageSource.getMessage("board.search.success"), null);
	}
}
