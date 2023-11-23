package br.com.jhonerodrigues.core.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Long id) {
		super("Resource not found. Id: " + id);
	}
}
