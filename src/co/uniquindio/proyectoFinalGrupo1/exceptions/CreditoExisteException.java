package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Excepcion para la clase credito
 * @author Camilo
 *
 */
public class CreditoExisteException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin mensaje
	 */
	public CreditoExisteException() {}

	/**
	 * Constructor con mensaje
	 * @param mensaje
	 */
	public CreditoExisteException(String mensaje)
	{
		super(mensaje);
	}
}
