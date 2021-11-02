package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Excepcion que controla cuando un dato NO es eliminado
 * @author Juan Camilo Ramos R.
 *
 */
public class NoEliminadoException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public NoEliminadoException()
	{

	}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public NoEliminadoException(String mensaje)
	{
		super(mensaje);
	}
}
