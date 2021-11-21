package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Excepcion que controla cuando un dato NO es almacenado
 * @author Juan Camilo Ramos R.
 *
 */
public class NoCreadoException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public NoCreadoException()
	{

	}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public NoCreadoException(String mensaje)
	{
		super(mensaje);
	}
}
