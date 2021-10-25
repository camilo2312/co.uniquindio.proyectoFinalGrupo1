package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase horario
 * @author Juan Camilo Ramos R.
 *
 */
public class Horario
{
	private int code;
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
	 * M�todo que permite obtener el c�digo del horario
	 * @return code
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el c�digo del horario
	 * @param code
	 */
	public void setCode(int code)
	{
		this.code = code;
	}

	/**
	 * M�todo que permite obtener la hora de incio
	 * @return horaInicio
	 */
	public String getHoraInicio()
	{
		return horaInicio;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la hora de inicio
	 * @param horaInicio
	 */
	public void setHoraInicio(String horaInicio)
	{
		this.horaInicio = horaInicio;
	}

	/**
	 * M�todo que permite obtener la hora final
	 * @return horaFinal
	 */
	public String getHoraFinal()
	{
		return horaFinal;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la hora de final
	 * @param horaFinal
	 */
	public void setHoraFinal(String horaFinal)
	{
		this.horaFinal = horaFinal;
	}

	/**
	 * M�todo que permite obtener el d�a del horario
	 * @return dia
	 */
	public Dias getDia()
	{
		return dia;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el d�a del horario
	 * @param dia
	 */
	public void setDia(Dias dia)
	{
		this.dia = dia;
	}

	/**
	 * M�todo que permite imprimir las propiedades de la clase horario
	 */
	@Override
	public String toString() {
		return "Horario [code=" + code + ", horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + ", dia=" + dia
				+ "]";
	}




}
