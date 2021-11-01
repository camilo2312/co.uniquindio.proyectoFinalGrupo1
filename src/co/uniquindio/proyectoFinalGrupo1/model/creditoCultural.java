package co.uniquindio.proyectoFinalGrupo1.model;

public class creditoCultural extends Credito
{

	double costo;

	/**
	 * Constructor de la clase
	 */
	public creditoCultural()
	{

	}

	/**
	 * Método que permite obtener el costo del credito cultural
	 * @return codigo
	 */
	public double getCosto()
	{
		return costo;
	}

	/**
	 * Método que permite asignar y/o actualizar el costo del credito cultural
	 * @param codigo
	 */
	public void setCosto(double costo)
	{
		this.costo = costo;
	}

	
	


	/**
	 * Método que permite imprimir las propiedades del credito Cultural
	 */
	@Override
	public String toString() {
		return "credito academico [costo=" + costo + "]";
	}
}