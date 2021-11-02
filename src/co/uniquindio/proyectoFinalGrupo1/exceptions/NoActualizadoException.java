package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Excepcion que controla cuando un dato NO es actualizado
 * @author Juan Camilo Ramos R.
 *
 */
public class NoActualizadoException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public NoActualizadoException()
	{

	}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public NoActualizadoException(String mensaje)
	{
		super(mensaje);
	}
}
