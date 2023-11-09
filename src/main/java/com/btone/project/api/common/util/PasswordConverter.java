package com.btone.project.api.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.persistence.AttributeConverter;

public class PasswordConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String raw) {
		return encode(raw);
	}

	@Override
	public String convertToEntityAttribute(String encoded) {
		return encoded;
	}

	public static String encode(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			return String.format("%0128x", new BigInteger(1, md.digest()));
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
