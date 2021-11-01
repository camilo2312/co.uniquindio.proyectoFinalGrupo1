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
	 * M�todo que permite obtener el costo del credito cultural
	 * @return codigo
	 */
	public double getCosto()
	{
		return costo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el costo del credito cultural
	 * @param codigo
	 */
	public void setCosto(double costo)
	{
		this.costo = costo;
	}

	
	


	/**
	 * M�todo que permite imprimir las propiedades del credito Cultural
	 */
	@Override
	public String toString() {
		return "credito academico [costo=" + costo + "]";
	}
}