package co.uniquindio.proyectoFinalGrupo1.model;

public class creditoAcademico extends Credito
{

boolean homologable;

public creditoAcademico()
{
	
}

/**
 * M�todo que permite obtener si es homologable o no del credito academico
 * @return homologable
 */
public boolean gethomologable()
{
	return homologable;
}

/**
 * M�todo que permite asignar y/o actualizar si es homologable o no del credito academico
 * @param costo
 */
public void sethomologable(boolean homologable)
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

