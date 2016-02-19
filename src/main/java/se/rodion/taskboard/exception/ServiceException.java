package se.rodion.taskboard.exception;

public final class ServiceException extends Exception
{
	private static final long serialVersionUID = -4244230929363809168L;
	
	public ServiceException (String message)
	{
		super(message);
	}
	
	public ServiceException (String message, Throwable cause)
	{
		super(message, cause);
	}
}
