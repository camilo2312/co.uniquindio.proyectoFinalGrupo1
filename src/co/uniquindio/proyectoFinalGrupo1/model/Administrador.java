package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase administrador de la aplicaci�n
 * @author Juan Camilo Ramos R.
 *
 */
public class Administrador extends Usuario
{
	private String nombre;
	private String documento;
	private String tipoDocumento;
	private int edad;

	/**
	 * Constructor de la clase
	 */
	public Administrador(){}

	/**
	 * M�todo que permite obtener el nombre del administrador
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el nombre
	 * del administrador
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * M�todo que permite obtener el documento del administrador
	 * @return documento
	 */
	public String getDocumento()
	{
		return documento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el documento del administrador
	 * @param documento
	 */
	public void setDocumento(String documento)
	{
		this.documento = documento;
	}

	/**
	 * M�todo que permite obtener el tipo de documento del administrador
	 * @return
	 */
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el tipo
	 * de documento del administrador
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * M�todo que permite obtener la edad del administrador
	 * @return
	 */
	public int getEdad()
	{
		return edad;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la edad
	 * del administrador
	 * @param edad
	 */
	public void setEdad(int edad)
	{
		this.edad = edad;
	}


}
