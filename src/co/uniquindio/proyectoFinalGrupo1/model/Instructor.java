package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase instructor
 * @author Juan Camilo Ramos R.
 *
 */
public class Instructor extends Usuario
{
	private String nombre;
	private String documento;
	private String tipoDocumento;
	private String asignatura;

	/**
	 * Constructor de la clase
	 */
	public Instructor()
	{

	}

	/**
	 * M�todo que permite obtener el nombre
	 * del instructor
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el nombre del instructor
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * M�todo que permite obtener el documento
	 * del instructor
	 * @return documento
	 */
	public String getDocumento()
	{
		return documento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el documento del instructor
	 * @param documento
	 */
	public void setDocumento(String documento)
	{
		this.documento = documento;
	}

	/**
	 * M�todo que permite obtener el tipo de documento del instructor
	 * @return tipoDocumento
	 */
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el tipo de documento del instructor
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * M�todo que permite obtener la asignatura del instructor
	 * @return asignatura
	 */
	public String getAsignatura()
	{
		return asignatura;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * la asignatura del instructor
	 * @param asignatura
	 */
	public void setAsignatura(String asignatura)
	{
		this.asignatura = asignatura;
	}

	/**
	 * M�todo que permite imprimir las propiedades del instructor
	 */
	@Override
	public String toString() {
		return nombre;
	}


}
