package co.uniquindio.proyectoFinalGrupo1.model;

public class CreditoDeportivo extends Credito
{
	private double asistenciaMinima;

	/**
	 * Constructor de la clase
	 */
	public CreditoDeportivo()
	{

	}

	/**
	 * M�todo que permite asignar y/o actualizar la asistencia mnima del
	 * credito Deportivo
	 * @param asistenciaMinima
	 */
	public void setAsistenciaMinima(double asistenciaMinima)
	{
		this.asistenciaMinima = asistenciaMinima;
	}
	public double getasistenciaMinima()
	{
		return asistenciaMinima;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la asistencia mnima del
	 * credito Deportivo
	 * @param asistenciaMinima
	 */
	public void setasistenciaMinima(double asistenciaMinima)
	{
		this.asistenciaMinima = asistenciaMinima;
	}


	/**
	 * M�todo que permite imprimir las propiedades del creditoDeportivo
	 */
	@Override
	public String toString() {
		return "credito deportivo [asistenciaMinima=" + asistenciaMinima + "]";
	}



}


