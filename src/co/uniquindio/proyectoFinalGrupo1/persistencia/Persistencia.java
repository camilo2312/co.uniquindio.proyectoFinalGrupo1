package co.uniquindio.proyectoFinalGrupo1.persistencia;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;

public class Persistencia 
{
	public static final String RUTA_ARCHIVO_LOG_ESTUDIANTE = "src/co/uniquindio/proyectoFinalGrupo1/resouces/LogEstudiantes.txt";
	public static final String RUTA_ARCHIVO_LOG_INSTRUCTOR = "src/co/uniquindio/proyectoFinalGrupo1/resouces/LogInstructor.txt";
	
	public static final String RUTA_ARCHIVO_ESTUDIANTE = "src/co/uniquindio/proyectoFinalGrupo1/resouces/Estudiantes.txt";
	public static final String RUTA_ARCHIVO_INSTRUCTOR = "src/co/uniquindio/proyectoFinalGrupo1/resouces/Instructor.txt";
	
	public static final String RUTA_ARCHIVO_MODELO_ESTUDIANTE_XML = "src/resources/ModelEstudiante.xml";
	
	/**
     * M�todos que permite guardar los registros logs de los estudiante
     * por medio de un .txt
     */
	public static void guardaRegistroLogEstudiante(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_ESTUDIANTE);
	}
	
	/**
     * M�todos que permite guardar los registros logs de los estudiante
     * por medio de un .txt
     */
	public static void guardaRegistroLogInstructor(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_INSTRUCTOR);
	}
	
	/**
	 * Guarda en un archivo de texto todos la informaci�n de los estudiantes almacenadas en el ArrayList
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
	 * Guarda en un archivo de texto todos la informaci�n de los estudiantes almacenadas en el ArrayList
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
	
//	public static ArrayList<Estudiante> cargarDatosEstudianteXML() 
//	{
//		
//		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
//		
//		try {
//			estudiantes = (ArrayList<Estudiante>)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_ESTUDIANTE_XML);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return estudiantes;
//
//	}
	
	public static void guardarDatosEstudiantesXML(Estudiante estudiante) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_ESTUDIANTE_XML, estudiante);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
