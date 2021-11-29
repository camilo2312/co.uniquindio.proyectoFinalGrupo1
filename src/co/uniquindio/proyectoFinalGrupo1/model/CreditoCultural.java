package co.uniquindio.proyectoFinalGrupo1.model;

public class CreditoCultural extends Credito
{

	private double costo;

	/**
	 * Constructor de la clase
	 */
	public CreditoCultural()
	{

	}

	/**
	 * M�todo que permite obtener el costo del credito cultural
	 * @return costo
	 */
	public double getCosto()
	{
		return costo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el costo del credito cultural
	 * @param costo
	 */
	public void setCosto(double costo)
	{
		this.costo = costo;
	}



	/**
	 * M�todo que permite imprimir las propiedades del credito Cultural
	 */
	@Override
	public String toString()
	{
		return "Credito Cultural [costo=" + costo + "]";
	}
}