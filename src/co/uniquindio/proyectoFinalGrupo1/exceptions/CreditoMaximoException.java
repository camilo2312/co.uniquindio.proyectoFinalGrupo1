package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si se sobre pasa de los creditos maximos permitidos                                               
 * @author Orlando Narvaez Baracaldo
 *
 */
public class CreditoMaximoException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public CreditoMaximoException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public CreditoMaximoException(String mensaje)
	{
		super(mensaje);
	}

}
