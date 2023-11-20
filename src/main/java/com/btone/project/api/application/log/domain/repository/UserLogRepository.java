package com.btone.project.api.application.log.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.btone.project.api.application.log.domain.model.UserLog;

public interface UserLogRepository extends JpaRepository<UserLog, Long>, JpaSpecificationExecutor<UserLog> {

}
