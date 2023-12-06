package com.btone.project.api.application.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.auth.domain.model.Role;
import com.btone.project.api.application.auth.domain.repository.RoleRepository;
import com.btone.project.api.application.auth.dto.request.RoleRequestDTO;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;

import lombok.RequiredArgsConstructor;

/**
* @packageName   : com.btone.project.api.auth.service
* @fileName      : RoleService.java
* @author        : 오수병
* @date          : 2023.11.13
* @description   : 권한관리 서비스
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.13        오수병                최초 생성
*/
/**
* @packageName   : com.btone.project.api.auth.service
* @fileName      : RoleService.java
* @author        : 오수병
* @date          : 2023.11.13
* @description   :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.13        오수병                최초 생성
*/
@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository repository;
	private final MessageSourceAccessor messageSource;

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한관리
	* @param method
	* @param input
	* @return
	*/
	public ResponseMessage methods(String method, RoleRequestDTO input) {
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

	/**
	* @methodName  : create
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한등록
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage create(RoleRequestDTO input, Map<String, Object> searchKeys) {
		searchKeys.put("roleCd", input.getRoleCd());
		List<Role> list = repository.findAll(CommonSpecification.searchCondition(searchKeys));

		if(list.size() > 0) {
			if("Y".equals(list.get(0).getDelYn())) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.checkcd.deleted"), null);
			}else {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.checkcd.duplicated"), null);
			}
		}

		Role role = Role.builder()
				.roleCd(input.getRoleCd())
				.roleNm(input.getRoleNm())
				.roleDesc(input.getRoleDesc())
				.build();

		repository.save(role);

		return ResponseMessage.ok(null, messageSource.getMessage("role.create.success"), null);
	}

	/**
	* @methodName  : ud
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한수정/삭제
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage update(String method, RoleRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		searchKeys.put("delYn", "N");
		searchKeys.put("roleCd", input.getRoleCd());
		Optional<Role> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalRole.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.notexists"), null);
		}

		Role role = optionalRole.get();

		if(CommonMethods.UPDATE.getKey().equals(method)) {
			role.setRoleNm(input.getRoleNm());
			role.setRoleDesc(input.getRoleDesc());
			message = messageSource.getMessage("role.update.success");
		}else {
			role.setDelYn("Y");
			message = messageSource.getMessage("role.delete.success");
		}

		return ResponseMessage.ok(null, message, null);
	}

	/**
	* @methodName  : search
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한목록조회
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage search(RoleRequestDTO input, Map<String, Object> searchKeys) {
		List<Role> list = repository.findAll();

		if(list.size() == 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.notexists"), null);
		}

		return ResponseMessage.ok(list, messageSource.getMessage("role.search.success"), null);
	}
}
