package br.com.internetbanking.exception;

public class ServiceException extends Exception {

	private final static String defaultMessage = "[ServiceException] Handle Exception ";
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	
	public ServiceException(){
		super();
	}
	
	public ServiceException(String msg){
		super(defaultMessage + msg);
	}
	
	public ServiceException(String msg, Throwable t){
		super(defaultMessage + msg, t);
	}
}
