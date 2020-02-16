package model.data_exception;

public class Cola_ColaVaciaException extends Exception
{
	public static final long serialVersionUID = 1L;
	
	public Cola_ColaVaciaException(String mensaje)
	{
		super(mensaje);
	}

	
}