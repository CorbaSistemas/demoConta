package com.example.demoConta.infra.exceptions;

/**
 * @author Dionízio Inácio on 31/05/2024
 */
public class BusinessException extends RuntimeException{

	public BusinessException(String message){
		super(message);
	}

	public BusinessException(String message, Throwable cause){
		super(message, cause);
	}

}
