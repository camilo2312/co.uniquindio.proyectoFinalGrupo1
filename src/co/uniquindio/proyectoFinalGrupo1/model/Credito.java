package co.uniquindio.proyectoFinalGrupo1.model;
import java.util.ArrayList;

/**
 * Clase credito
 * @author Juan Camilo Ramos R.
 *
 */
public class Credito
{
	/**
	 * Atributos de la clase
	 */
	private String codigo;
	private String nombre;
	private int duracion;
	private int cupoMaximo;
	private ArrayList<Estudiante> lstEstudiantes;
	private ArrayList<Horario> horarios;
	private Lugar lugar;
	private Instructor instructor;
	private TipoCredito tipoCredito;

	/**
	 * Constructor de la clase
	 */
	public Credito()
	{

	}

	/**
	 * Método que permite obtener el código del credito
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * Método que permite asignar y/o actualizar el
	 * codigo del credito
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Método que permite obtener la duración del credito en horas
	 * @return duracion
	 */
	public int getDuracion()
	{
		return duracion;
	}

	/**
	 * Método que permite asignar y/o actualizar la duración
	 * del credito
	 * @param duracion
	 */
	public void setDuracion(int duracion)
	{
		this.duracion = duracion;
	}

	/**
	 * Método que permite obtener el cupo maximo
	 * @return cupoMaximo
	 */
	public int getCupoMaximo()
	{
		return cupoMaximo;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el cupo maximo del credito
	 * @param cupoMaximo
	 */
	public void setCupoMaximo(int cupoMaximo)
	{
		this.cupoMaximo = cupoMaximo;
	}

	/**
	 * Método que permite obtener los horarios
	 * del credito
	 * @return horarios
	 */
	public ArrayList<Horario> getHorarios()
	{
		return horarios;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * los horarios del credito
	 * @param horarios
	 */
	public void setHorarios(ArrayList<Horario> horarios)
	{
		this.horarios = horarios;
	}

	/**
	 * Método que permite obtener el nombre del credito
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Método que permite asignar y/o actualizar el
	 * nombre del credito
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Método que permite obtener la lista de estudiantes
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> getLstEstudiantes()
	{
		return lstEstudiantes;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de estudiantes
	 * @param lstEstudiantes
	 */
	public void setLstEstudiantes(ArrayList<Estudiante> lstEstudiantes)
	{
		this.lstEstudiantes = lstEstudiantes;
	}

	/**
	 * Método que permite asignar y/o actualizar el horario del credito
	 * @param horario
	 */
	public void setHorario(ArrayList<Horario> horario)
	{
		this.horarios = horario;
	}

	/**
	 * Método que permite obtener el lugar del credito
	 * @return lugar
	 */
	public Lugar getLugar() {
		return lugar;
	}

	/**
	 * Método que permite asignar y/o actualizar el lugar del credito
	 * @param lugar
	 */
	public void setLugar(Lugar lugar)
	{
		this.lugar = lugar;
	}

	/**
	 * Método que permite obtener el instructor del credito
	 * @return instructor
	 */
	public Instructor getInstructor()
	{
		return instructor;
	}

	/**
	 * Método que permite asignar y/o actualizar el instructor del credito
	 * @param instructor
	 */
	public void setInstructor(Instructor instructor)
	{
		this.instructor = instructor;
	}

	/**
	 * Método que permite obtener el tipo de crédito
	 * @return tipoCredito
	 */
	public TipoCredito getTipoCredito()
	{
		return tipoCredito;
	}

	/**
	 * Método que permite asignar y/o actualizar el tipo de credito
	 * @param tipoCredito
	 */
	public void setTipoCredito(TipoCredito tipoCredito)
	{
		this.tipoCredito = tipoCredito;
	}


	/**
	 * Método que permite imprimir las propiedades del credito
	 */
	@Override
	public String toString()
	{
		return "Nombre: " + nombre + "\n" + "Instructor: " + instructor.getNombre() + "\n";
	}


}
