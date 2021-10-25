package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase estudiante
 * @author Juan Camilo Ramos R.
 *
 */
public class Estudiante extends Usuario
{
	private String nombre;
	private String documento;
	private String tipoDocumento;
	private int edad;

	/**
	 * Constructor de la clase estudiante
	 */
	public Estudiante()
	{

	}

	/**
	 * M�todo que permite obtener el nombre
	 * del estudiante
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el nombre del estudiante
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * M�todo que permite obtener el documento
	 * del estudiante
	 * @return documento
	 */
	public String getDocumento()
	{
		return documento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el documento del estudiante
	 * @param documento
	 */
	public void setDocumento(String documento)
	{
		this.documento = documento;
	}

	/**
	 * M�todo que permite obtener el tipo de documento del estudiante
	 * @return tipoDocumento
	 */
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el tipo de documento del estudiante
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * M�todo que permite obtener la edad del estudiante
	 * @return estudiante
	 */
	public int getEdad()
	{
		return edad;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * la edad del estudiante
	 * @param edad
	 */
	public void setEdad(int edad)
	{
		this.edad = edad;
	}

	/**
	 * M�todo que permite imprimir las propiedades del estudiante
	 */
	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", documento=" + documento + ", tipoDocumento=" + tipoDocumento
				+ ", edad=" + edad + "]";
	}




}
