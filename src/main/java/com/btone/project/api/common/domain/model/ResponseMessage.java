package com.btone.project.api.common.domain.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {

	private int statusCode;
	private String timestamp;
	private String resultMessage;
	private String detailMessage;
	private Object data;

	public static ResponseMessage of(Object data, int statusCode, String resultMessage, String detailMessage) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ResponseMessage.builder().data(data).statusCode(statusCode).resultMessage(resultMessage).detailMessage(detailMessage).timestamp(sdf.format(new Date())).build();
	}

	public static ResponseMessage of(Object data, HttpStatus status, String resultMessage, String detailMessage) {
		return of(data, status.value(), resultMessage, detailMessage);
	}

	public static ResponseMessage of(Object data, HttpStatus status) {
		return of(data, status, null, null);
	}

	public static ResponseMessage ok(Object data, String resultMessage, String detailMessage) {
		return of(data, HttpStatus.OK, resultMessage, detailMessage);
	}

	public static ResponseMessage of(HttpStatus status) {
		return of(null, status);
	}

	public static ResponseMessage ok() {
		return of(HttpStatus.OK);
	}

	public static ResponseMessage ok(Object data) {
		return of(data, HttpStatus.OK);
	}

	public <T> T getConvertFromMapToData(Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		return clazz.cast(mapper.convertValue(this.data, clazz));
	}
}