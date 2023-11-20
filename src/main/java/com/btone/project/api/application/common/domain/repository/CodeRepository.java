package com.btone.project.api.application.common.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.btone.project.api.application.common.domain.model.Code;

public interface CodeRepository extends JpaRepository<Code, Long>, JpaSpecificationExecutor<Code> {

}
