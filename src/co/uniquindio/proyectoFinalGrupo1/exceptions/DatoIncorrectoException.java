package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si se ingresa algun dato incorrecto
 * @author Orlando Narvaez Baracaldo
 *
 */
public class DatoIncorrectoException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public DatoIncorrectoException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public DatoIncorrectoException(String mensaje)
	{
		super(mensaje);
	}

}
