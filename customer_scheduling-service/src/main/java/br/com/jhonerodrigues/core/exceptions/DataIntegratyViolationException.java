package br.com.jhonerodrigues.core.exceptions;

public class DataIntegratyViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegratyViolationException (String phone) {
		super("The phone " + phone + " is already registered in the system");
	}
}
