package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase horario
 * @author Juan Camilo Ramos R.
 *
 */
public class Horario
{
	private String code;
	private String horaInicio;
	private String horaFinal;
	private Dias dia;

	/**
	 * Constructor de la clase
	 */
	public Horario()
	{

	}

	/**
	 * Método que permite obtener el código del horario
	 * @return code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Método que permite asignar y/o actualizar el código del horario
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Método que permite obtener la hora de incio
	 * @return horaInicio
	 */
	public String getHoraInicio()
	{
		return horaInicio;
	}

	/**
	 * Método que permite asignar y/o actualizar la hora de inicio
	 * @param horaInicio
	 */
	public void setHoraInicio(String horaInicio)
	{
		this.horaInicio = horaInicio;
	}

	/**
	 * Método que permite obtener la hora final
	 * @return horaFinal
	 */
	public String getHoraFinal()
	{
		return horaFinal;
	}

	/**
	 * Método que permite asignar y/o actualizar la hora de final
	 * @param horaFinal
	 */
	public void setHoraFinal(String horaFinal)
	{
		this.horaFinal = horaFinal;
	}

	/**
	 * Método que permite obtener el día del horario
	 * @return dia
	 */
	public Dias getDia()
	{
		return dia;
	}

	/**
	 * Método que permite asignar y/o actualizar el día del horario
	 * @param dia
	 */
	public void setDia(Dias dia)
	{
		this.dia = dia;
	}

	/**
	 * Método que permite imprimir las propiedades de la clase horario
	 */
	@Override
	public String toString() {
		return "Horario [code=" + code + ", horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + ", dia=" + dia
				+ "]";
	}




}
