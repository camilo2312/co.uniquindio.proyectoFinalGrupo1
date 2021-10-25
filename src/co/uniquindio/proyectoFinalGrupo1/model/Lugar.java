package co.uniquindio.proyectoFinalGrupo1.model;
/**
 * Clase lugar
 * @author Juan Camilo Ramos R.
 *
 */
public class Lugar
{
	private String codigo;
	private String nombre;

	/**
	 * Constructor de la clase
	 */
	public Lugar()
	{

	}

	/**
	 * Método que permite obtener el código del lugar
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * Método que permite asignar y/o actualizar el código del
	 * lugar
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Método que permite obtener el nombre del lugar
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Método que permite asignar y/o actualizar el nombre
	 * del lugar
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	/**
	 * Método que permite imprimir las propiedades del lugar
	 */
	@Override
	public String toString() {
		return "Lugar [codigo=" + codigo + ", nombre=" + nombre + "]";
	}



}
