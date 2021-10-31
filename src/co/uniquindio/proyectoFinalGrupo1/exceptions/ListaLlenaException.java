package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si la lista sobrepaso del limite
 * @author Orlando Narvaez Baracaldo
 *
 */
public class ListaLlenaException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public ListaLlenaException() {}
	
	
	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public ListaLlenaException(String mensaje)
	{
		super(mensaje);
	}
}
