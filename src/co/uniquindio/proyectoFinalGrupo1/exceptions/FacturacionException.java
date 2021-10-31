package co.uniquindio.proyectoFinalGrupo1.exceptions;

/**
 * Exception que se genera si el usuario no paga la factura
 * @author Orlando Narvaez Baracaldo
 *
 */
public class FacturacionException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio para instancias sin mensaje
	 */
	public FacturacionException(){}

	/**
	 * Constructor con mensaje para especificar el error
	 * @param mensaje
	 */
	public FacturacionException(String mensaje)
	{
		super(mensaje);
	}

}
