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
	 * M�todo que permite obtener el c�digo del lugar
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el c�digo del
	 * lugar
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * M�todo que permite obtener el nombre del lugar
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el nombre
	 * del lugar
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	/**
	 * M�todo que permite imprimir las propiedades del lugar
	 */
	@Override
	public String toString() {
		return "Lugar [codigo=" + codigo + ", nombre=" + nombre + "]";
	}



}
