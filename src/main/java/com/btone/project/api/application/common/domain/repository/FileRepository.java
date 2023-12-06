package com.btone.project.api.application.common.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.btone.project.api.application.common.domain.model.File;

public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File> {

}
