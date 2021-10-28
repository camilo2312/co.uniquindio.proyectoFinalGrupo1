package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si un usuario ya existe
 * @author Juan Camilo Ramos R
 *
 */
public class UsuarioExisteException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public UsuarioExisteException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public UsuarioExisteException(String mensaje)
	{
		super(mensaje);
	}
}
