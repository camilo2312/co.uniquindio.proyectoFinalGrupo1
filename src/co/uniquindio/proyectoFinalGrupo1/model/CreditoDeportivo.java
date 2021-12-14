package co.uniquindio.proyectoFinalGrupo1.model;

public class CreditoDeportivo extends Credito
{
	private int asistenciaMinima;

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
	public void setAsistenciaMinima(int asistenciaMinima)
	{
		this.asistenciaMinima = asistenciaMinima;
	}

	/**
	 * M�todo que permite obtener la asistencia minima
	 * @return asistenciaMinima
	 */
	public int getAsistenciaMinima()
	{
		return asistenciaMinima;
	}

	/**
	 * M�todo que permite imprimir las propiedades del creditoDeportivo
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}



}


