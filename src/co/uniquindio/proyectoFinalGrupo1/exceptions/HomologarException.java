package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si el usuario no puede homologar la materia
 * @author Orlando Narvaez Baracaldo
 *
 */
public class HomologarException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public HomologarException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public HomologarException(String mensaje)
	{
		super(mensaje);
	}

}
