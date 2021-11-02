package co.uniquindio.proyectoFinalGrupo1.model;
import java.util.ArrayList;

/**
 * Clase credito
 * @author Juan Camilo Ramos R.
 *
 */
public class Credito
{
	private String codigo;
	private String nombre;
	private int duracion;
	private int cupoMaximo;
	private ArrayList<Estudiante> lstEstudiantes;
	private ArrayList<Horario> horarios;
	private Lugar lugar;
	private Instructor instructor;

	/**
	 * Constructor de la clase
	 */
	public Credito()
	{

	}

	/**
	 * M�todo que permite obtener el c�digo del credito
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el
	 * codigo del credito
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * M�todo que permite obtener la duraci�n del credito en horas
	 * @return duracion
	 */
	public int getDuracion()
	{
		return duracion;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la duraci�n
	 * del credito
	 * @param duracion
	 */
	public void setDuracion(int duracion)
	{
		this.duracion = duracion;
	}

	/**
	 * M�todo que permite obtener el cupo maximo
	 * @return cupoMaximo
	 */
	public int getCupoMaximo()
	{
		return cupoMaximo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * el cupo maximo del credito
	 * @param cupoMaximo
	 */
	public void setCupoMaximo(int cupoMaximo)
	{
		this.cupoMaximo = cupoMaximo;
	}

	/**
	 * M�todo que permite obtener los horarios
	 * del credito
	 * @return horarios
	 */
	public ArrayList<Horario> getHorarios()
	{
		return horarios;
	}

	/**
	 * M�todo que permite asignar y/o actualizar
	 * los horarios del credito
	 * @param horarios
	 */
	public void setHorarios(ArrayList<Horario> horarios)
	{
		this.horarios = horarios;
	}

	/**
	 * M�todo que permite obtener el nombre del credito
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el
	 * nombre del credito
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * M�todo que permite obtener la lista de estudiantes
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> getLstEstudiantes()
	{
		return lstEstudiantes;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de estudiantes
	 * @param lstEstudiantes
	 */
	public void setLstEstudiantes(ArrayList<Estudiante> lstEstudiantes)
	{
		this.lstEstudiantes = lstEstudiantes;
	}

	/**
	 * M�todo que permite obtener los horario del credito
	 * @return horario
	 */
	public ArrayList<Horario> getHorario()
	{
		return horarios;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el horario del credito
	 * @param horario
	 */
	public void setHorario(ArrayList<Horario> horario)
	{
		this.horarios = horario;
	}

	/**
	 * M�todo que permite obtener el lugar del credito
	 * @return lugar
	 */
	public Lugar getLugar() {
		return lugar;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el lugar del credito
	 * @param lugar
	 */
	public void setLugar(Lugar lugar)
	{
		this.lugar = lugar;
	}

	/**
	 * M�todo que permite obtener el instructor del credito
	 * @return instructor
	 */
	public Instructor getInstructor()
	{
		return instructor;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el instructor del credito
	 * @param instructor
	 */
	public void setInstructor(Instructor instructor)
	{
		this.instructor = instructor;
	}

	/**
	 * M�todo que permite imprimir las propiedades del credito
	 */
	@Override
	public String toString()
	{
		return "Credito [nombre=" + nombre + ", lstEstudiantes=" + lstEstudiantes + ", horarios=" + horarios
				+ ", lugar=" + lugar + ", instructor=" + instructor + "]";
	}



}
