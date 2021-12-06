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
	 * Método que permite obtener el nombre
	 * del instructor
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el nombre del instructor
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Método que permite obtener el documento
	 * del instructor
	 * @return documento
	 */
	public String getDocumento()
	{
		return documento;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el documento del instructor
	 * @param documento
	 */
	public void setDocumento(String documento)
	{
		this.documento = documento;
	}

	/**
	 * Método que permite obtener el tipo de documento del instructor
	 * @return tipoDocumento
	 */
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el tipo de documento del instructor
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Método que permite obtener la asignatura del instructor
	 * @return asignatura
	 */
	public String getAsignatura()
	{
		return asignatura;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * la asignatura del instructor
	 * @param asignatura
	 */
	public void setAsignatura(String asignatura)
	{
		this.asignatura = asignatura;
	}

	/**
	 * Método que permite imprimir las propiedades del instructor
	 */
	@Override
	public String toString() {
		return nombre;
	}


}
