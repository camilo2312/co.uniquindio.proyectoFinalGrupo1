package co.uniquindio.proyectoFinalGrupo1.model;

public class creditoDeportivo extends Credito
{
	double asistenciaMinima;
	
	public creditoDeportivo()
	{
		
	}

	/**
	 * Método que permite asignar y/o actualizar la asistencia mnima del
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
	 * Método que permite asignar y/o actualizar la asistencia mnima del
	 * credito Deportivo
	 * @param asistenciaMinima
	 */
	public void setasistenciaMinima(double asistenciaMinima)
	{
		this.asistenciaMinima = asistenciaMinima;
	}

	
	


	/**
	 * Método que permite imprimir las propiedades del creditoDeportivo
	 */
	@Override
	public String toString() {
		return "credito deportivo [asistenciaMinima=" + asistenciaMinima + "]";
	}



}


