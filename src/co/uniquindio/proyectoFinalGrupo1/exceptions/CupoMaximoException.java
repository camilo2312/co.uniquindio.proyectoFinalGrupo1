package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Excepcion que controla la cantidad maxima de estudiantes
 * de un credito
 * @author Camilo
 *
 */
public class CupoMaximoException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public CupoMaximoException()
	{

	}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public CupoMaximoException(String mensaje)
	{
		super(mensaje);
	}

}
