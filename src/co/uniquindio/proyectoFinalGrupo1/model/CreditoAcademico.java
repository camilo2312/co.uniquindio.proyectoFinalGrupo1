package co.uniquindio.proyectoFinalGrupo1.model;

public class CreditoAcademico extends Credito
{

	private boolean homologable;

	/**
	 * Constructor de la clase
	 */
	public CreditoAcademico()
	{

	}

	/**
	 * M�todo que permite obtener si es homologable o no del credito academico
	 * @return homologable
	 */
	public boolean getHomologable()
	{
		return homologable;
	}

	/**
	 * M�todo que permite asignar y/o actualizar si es homologable o no del credito academico
	 * @param homologable
	 */
	public void setHomologable(boolean homologable)
	{
		this.homologable = homologable;
	}

	/**
	 * M�todo que permite imprimir las propiedades del credito Academico
	 */
	@Override
	public String toString() {
		return "credito academico [homologable=" + homologable + "]";
	}
}

