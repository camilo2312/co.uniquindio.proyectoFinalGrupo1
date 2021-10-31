package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera dos creditos coinciden en el mismo horario
 * @author Orlando Narvaez Baracaldo
 *
 */
public class CoincidirException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public CoincidirException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public CoincidirException(String mensaje)
	{
		super(mensaje);
	}

}
