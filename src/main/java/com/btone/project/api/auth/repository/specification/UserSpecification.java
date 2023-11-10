package com.btone.project.api.auth.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.btone.project.api.auth.entity.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserSpecification {
	public static Specification<User> searchUser(Map<String, Object> searchKey) {
        return new Specification<User>() {
            /**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				for(String key : searchKey.keySet()){
					predicates.add(criteriaBuilder.equal(root.get(key), searchKey.get(key)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
