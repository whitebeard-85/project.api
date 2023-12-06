package com.btone.project.api.common.exception;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.btone.project.api.common.domain.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final MessageSourceAccessor messageSource;

	@ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MaxUploadSizeExceededException ex) {
		return ResponseEntity.ok(ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.fileupload.maximum-size-exceeded"), null));
    }
}
