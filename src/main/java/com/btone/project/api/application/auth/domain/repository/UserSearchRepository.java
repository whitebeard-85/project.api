package com.btone.project.api.application.auth.domain.repository;

import static com.btone.project.api.application.auth.domain.model.QRole.role;
import static com.btone.project.api.application.auth.domain.model.QUser.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btone.project.api.application.auth.domain.condition.UserSearchCondition;
import com.btone.project.api.application.auth.dto.response.QUserResponseDTO;
import com.btone.project.api.application.auth.dto.response.UserResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class UserSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public UserSearchRepository(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<UserResponseDTO> search(UserSearchCondition userSearchCondition) {
		return jpaQueryFactory.select(new QUserResponseDTO(user.userSn, user.userId, user.pwd, user.actvNm, user.agreeYn, user.rsPwdYn, user.tmpPwd, user.roleCd, role.roleNm, user.socialYn, user.socialType, user.accessToken, user.createdDate, user.modifiedDate))
				.from(user).leftJoin(role)
				.on(user.roleCd.eq(role.roleCd))
				.where(
					allEq(userSearchCondition.getActvNm(), userSearchCondition.getRoleCd(), userSearchCondition.getRoleNm())
				)
				.fetch();
	}

	private Predicate actvNmContains(String actvNm) {
	    return actvNm != null ? user.actvNm.contains(actvNm) : null;
	}

	private Predicate roleCdEq(String roleCd) {
	    return roleCd != null ? role.roleCd.eq(roleCd) : null;
	}

	private Predicate roleNmContains(String roleNm) {
		return roleNm != null ? role.roleNm.contains(roleNm) : null;
	}

	private BooleanBuilder allEq(String actvNm, String roleCd, String roleNm) {
		BooleanBuilder builder = new BooleanBuilder();
	    return builder.and(actvNmContains(actvNm)).and(roleCdEq(roleCd)).and(roleNmContains(roleNm));
	}
}
