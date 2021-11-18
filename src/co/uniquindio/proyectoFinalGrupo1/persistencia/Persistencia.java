package co.uniquindio.proyectoFinalGrupo1.persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.model.Credito;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.Lugar;

public class Persistencia
{
	// RUTAS DE ARCHIVOS

	public static final String RUTA_ARCHIVO_LOG_ESTUDIANTE = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogEstudiantes.txt";
	public static final String RUTA_ARCHIVO_LOG_INSTRUCTOR = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogInstructor.txt";
	public static final String RUTA_ARCHIVO_LOG_LUGAR      = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogLugar.txt";
	public static final String RUTA_ARCHIVO_LOG_HORARIO    = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogHorario.txt";
	public static final String RUTA_ARCHIVO_LOG_CREDITO    = "src/co/uniquindio/proyectoFinalGrupo1/resources/LogCredito.txt";

	public static final String RUTA_ARCHIVO_ESTUDIANTE = "src/co/uniquindio/proyectoFinalGrupo1/resources/ArchivoEstudiantes.txt";
	public static final String RUTA_ARCHIVO_INSTRUCTOR = "src/co/uniquindio/proyectoFinalGrupo1/resources/ArchivoInstructor.txt";
	public static final String RUTA_ARCHIVO_LUGAR      = "src/co/uniquindio/proyectoFinalGrupo1/resources/ArchivoLugar.txt";
	public static final String RUTA_ARCHIVO_HORARIO    = "src/co/uniquindio/proyectoFinalGrupo1/resources/ArchivoHorario.txt";
	public static final String RUTA_ARCHIVO_CREDITO    = "src/co/uniquindio/proyectoFinalGrupo1/resources/ArchivoCredito.txt";

	public static final String RUTA_ARCHIVO_MODELO_ESTUDIANTE_XML    = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelEstudiante.xml";
	public static final String RUTA_ARCHIVO_MODELO_INSTRUCTOR_XML    = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelInstructor.xml";
	public static final String RUTA_ARCHIVO_MODELO_HORARIOS_XML      = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelHorarios.xml";
	public static final String RUTA_ARCHIVO_MODELO_LUGARES_XML       = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelLugares.xml";
	public static final String RUTA_ARCHIVO_MODELO_CREDITOS_XML      = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelCreditos.xml";
	public static final String RUTA_ARCHIVO_MODELO_ADMINISTRADOR_XML = "src/co/uniquindio/proyectoFinalGrupo1/resources/ModelAdministrador.xml";

	public static final String RUTA_RECURSOS = "src/co/uniquindio/proyectoFinalGrupo1/resources/";
	public static final String RUTA_RESPALDO = "src/co/uniquindio/proyectoFinalGrupo1/respaldo/";

	//METODOS PARA GUARDAR LOGS

	/**
     * Método que permite guardar los registros logs de los estudiante
     * por medio de un .txt
     */
	public static void guardaRegistroLogEstudiante(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_ESTUDIANTE);
	}

	/**
     * Método que permite guardar los registros logs de los instructores
     * por medio de un .txt
     */
	public static void guardaRegistroLogInstructor(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_INSTRUCTOR);
	}

	/**
     * Método que permite guardar los registros logs de los lugares
     * por medio de un .txt
     */
	public static void guardaRegistroLogLugar(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_LUGAR);
	}

	/**
     * Métodos que permite guardar los registros logs de los horarios
     * por medio de un .txt
     */
	public static void guardaRegistroLogHorario(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_HORARIO);
	}

	/**
     * Métodos que permite guardar los registros logs de los creditos
     * por medio de un .txt
     */
	public static void guardaRegistroLogCredito(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_CREDITO);
	}

	//METODOS PARA GUARDAR LOS DATOS DE CADA CRUD

	/**
	 * Guarda en un archivo de texto todos la información de los estudiantes almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarEstudiantes(ArrayList<Estudiante> lstEstudiantes) throws IOException
	{
		String contenido = "";

		for(Estudiante estudiante:lstEstudiantes)
		{
			contenido+= estudiante.getNombre()+","+estudiante.getDocumento()+","+estudiante.getTipoDocumento()+","+estudiante.getEdad()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTE, contenido, false);
	}

	/**
	 * Guarda en un archivo de texto todos la información de los instructores almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarInstructor(ArrayList<Instructor> lstInstructores) throws IOException
	{
		String contenido = "";

		for(Instructor instructor:lstInstructores)
		{
			contenido+= instructor.getNombre()+","+instructor.getDocumento()+","+instructor.getTipoDocumento()+","+instructor.getAsignatura()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_INSTRUCTOR, contenido, false);
	}

	/**
	 * Guarda en un archivo de texto todos la información de los lugares almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarLugares(ArrayList<Lugar> lstLugares) throws IOException
	{
		String contenido = "";

		for(Lugar lugar:lstLugares)
		{
			contenido+= lugar.getNombre()+","+lugar.getCodigo()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_LUGAR, contenido, false);
	}

	/**
	 * Guarda en un archivo de texto todos la información de los horarios almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarHorario(ArrayList<Horario> lstHorarios) throws IOException
	{
		String contenido = "";

		for(Horario horario:lstHorarios)
		{
			contenido+= horario.getDia()+","+horario.getCode()+","+horario.getHoraInicio()+","+horario.getHoraFinal()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_HORARIO, contenido, false);
	}

	/**
	 * Guarda en un archivo de texto todos la información de los creditos almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarCredito(ArrayList<Credito> lstCreditos) throws IOException
	{
		String contenido = "";

		for(Credito credito:lstCreditos)
		{
			contenido+= credito.getNombre()+","+credito.getCodigo()+","+credito.getDuracion()+","+credito.getCupoMaximo()+","+
					credito.getLstEstudiantes()+","+credito.getHorario()+","+credito.getLugar()+","+credito.getInstructor()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_LOG_CREDITO, contenido, false);
	}

	//SERIALIZACIÓN  y XML

	/**
	 * Método que permetie cargar datos desde un XML
	 * @param rutaArchivo
	 * @return
	 */
	public static Object cargarDatosXML(String rutaArchivo)
	{

		Object obj = null;

		try {
			obj = ArchivoUtil.cargarRecursoSerializadoXML(rutaArchivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

	/**
	 * Método que permite guardar los datos a un XML
	 * @param object
	 * @param rutaArchivo
	 */
	public static void guardarDatosXML(Object object, String rutaArchivo)
	{

		try
		{
			ArchivoUtil.salvarRecursoSerializadoXML(rutaArchivo, object);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite guardar los datos de respaldo de
	 * la apliación
	 * @param file
	 */
	public static void guardarArchivoRespaldo(File file)
	{
		try
		{
			ArchivoUtil.guardarDatosRespaldo(file, RUTA_RECURSOS,RUTA_RESPALDO);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
