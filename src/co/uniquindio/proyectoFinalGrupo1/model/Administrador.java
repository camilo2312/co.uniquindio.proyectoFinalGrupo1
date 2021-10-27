package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase administrador de la aplicación
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

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento)
	{
		this.documento = documento;
	}

	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	public int getEdad()
	{
		return edad;
	}

	public void setEdad(int edad)
	{
		this.edad = edad;
	}


}
