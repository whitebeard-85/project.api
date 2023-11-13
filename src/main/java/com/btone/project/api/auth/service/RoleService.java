package com.btone.project.api.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.auth.entity.Role;
import com.btone.project.api.auth.enums.Method;
import com.btone.project.api.auth.repository.RoleRepository;
import com.btone.project.api.auth.vo.AuthVO;
import com.btone.project.api.common.model.ResponseMessage;
import com.btone.project.api.common.specification.CommonSpecification;

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
@Transactional
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
	public ResponseMessage methods(String method, AuthVO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(Method.CREATE.getKey().equals(method)) {
			return create(input, searchKeys);
		} else if(Method.UPDATE.getKey().equals(method) || Method.DELETE.getKey().equals(method)) {
			return ud(method, input, searchKeys);
		} else if(Method.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"));
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
	public ResponseMessage create(AuthVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("role.create.success");
		try {
			searchKeys.put("roleCd", input.getRoleCd());
			List<Role> list = repository.findAll(CommonSpecification.searchCondition(searchKeys));

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.checkcd.deleted"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.checkcd.duplicated"));
				}
			}

			Role role = Role.builder()
					.roleCd(input.getRoleCd())
					.roleNm(input.getRoleNm())
					.roleDesc(input.getRoleDesc())
					.build();

			repository.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, message);
	}

	/**
	* @methodName  : ud
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한수정, 삭제
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage ud(String method, AuthVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("role.update.success");
		Role role = null;
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("roleCd", input.getRoleCd());
			Optional<Role> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalRole.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.notexists"));
			}

			role = optionalRole.get();

			if(Method.UPDATE.getKey().equals(method)) {
				role.setRoleNm(input.getRoleNm());
				role.setRoleDesc(input.getRoleDesc());
			}else {
				role.setDelYn("Y");
				message = messageSource.getMessage("role.delete.success");
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, message);
	}

	/**
	* @methodName  : search
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한조회
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage search(AuthVO input, Map<String, Object> searchKeys) {
		List<Role> list = new ArrayList<>();
		try {
			list = repository.findAll();

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("role.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("role.search.success"));
	}
}
