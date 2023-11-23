package br.com.jhonerodrigues.core.domain.exceptions.job;

public class JobNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public JobNotFoundException(Long id) {
		super("Resource not found. Id: " + id);
	}
}
