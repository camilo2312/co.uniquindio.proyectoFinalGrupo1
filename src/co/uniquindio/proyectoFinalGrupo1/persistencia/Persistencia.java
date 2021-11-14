package co.uniquindio.proyectoFinalGrupo1.persistencia;

public class Persistencia
{
	public static final String RUTA_ARCHIVO_LOG_ESTUDIANTE = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogEstudiantes.txt";

	public static void guardaRegistroLogEstudiante(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_ESTUDIANTE);
	}

}
