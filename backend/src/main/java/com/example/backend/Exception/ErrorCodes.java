package com.example.backend.Exception;

public enum ErrorCodes {
	ADMININSTRATEUR_NOT_FOUND(1000),
	ADMINISTRATEUR_INVALID(1001),
	ADMINISTRATEUR_ALREADY_EXISTE(1002),
	ROLE_NOT_FOUND(2000),
	ROLE_INVALID(2001),
	ROLE_ALREADY_EXISTE(2002),
	LOGACTIVITE_NOT_FOUND(3000),
	LOGACTIVITE_INVALID(3000),
	LOGACTIVITE_ALREADY_EXISTE(3000);
	private int code;
	ErrorCodes(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
}
