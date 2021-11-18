package co.uniquindio.proyectoFinalGrupo1.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil
{
	static String fechaSistema = "";

	/**
	 * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
	 * @param ruta
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> leerArchivo(String ruta) throws IOException {

		ArrayList<String>  contenido = new ArrayList<String>();
		FileReader fr=new FileReader(ruta);
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";
		while((linea = bfr.readLine())!=null)
		{
			contenido.add(linea);
		}
		bfr.close();
		fr.close();
		return contenido;
	}

	/**
	 * Este metodo encargado de registrar los archivos logs
	 * @param mensajeLog
	 * @param nivel
	 * @param accion
	 * @param rutaArchivo
	 */
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
	{
		Logger LOGGER = Logger.getLogger(accion);
		FileHandler archivo ;

		cargarFechaSistema();

		try {
			archivo = new FileHandler(rutaArchivo,true);
			archivo.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(archivo);

			switch (nivel)
			{
			case 1:
				LOGGER.log(Level.INFO,accion+","+mensajeLog+","+fechaSistema);
				break;

			case 2:
				LOGGER.log(Level.WARNING,accion+","+mensajeLog+","+fechaSistema);
				break;

			case 3:
				LOGGER.log(Level.SEVERE,accion+","+mensajeLog+","+fechaSistema);
				break;

			default:
				break;
			}

		} catch (SecurityException e)
		{
			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Este metodo encargado cargar la fecha y hora
	 */
	private static void cargarFechaSistema()
	{
		String diaN = "";
		String mesN = "";

		Calendar cal1 = Calendar.getInstance();

		int  dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int anio = cal1.get(Calendar.YEAR);
		int hora = cal1.get(Calendar.HOUR);
		int minuto = cal1.get(Calendar.MINUTE);

		if(dia < 10)
		{
			diaN+="0"+dia;
		}
		else
		{
			diaN+=""+dia;
		}

		if(mes < 10)
		{
			mesN+="0"+mes;
		}
		else
		{
			mesN+=""+mes;
		}

		fechaSistema = anio + "-" + mesN + "-" + diaN + "-" + hora + "-" + minuto;
	}

	/**
	 * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
	 * @param ruta es la ruta o path donde esta ubicado el archivo
	 * @throws IOException
	 */
	public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException
	{
		FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
		BufferedWriter bfw = new BufferedWriter(fw);
		bfw.write(contenido);
		bfw.close();
		fw.close();
	}

	/**
	 * M�todo que permite cargar un recorso serializado XML
	 * @param rutaArchivo
	 * @return
	 * @throws IOException
	 */
	public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException
	{

		XMLDecoder decodificadorXML;
		Object objetoXML;

		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
		objetoXML = decodificadorXML.readObject();
		decodificadorXML.close();
		return objetoXML;

	}

	/**
	 * M�todp que permite salvar el recurso de XML
	 * @param rutaArchivo
	 * @param objeto
	 * @throws IOException
	 */
	public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException
	{

		XMLEncoder codificadorXML;

		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.writeObject(objeto);
		codificadorXML.close();

	}

	/**
	 * M�todo que permite guardar los datos de respaldo en la aplicaci�n
	 * en la carpeta de respaldo
	 * @param file
	 * @param rutaRecursos
	 * @param rutaRespaldo
	 * @throws IOException
	 */
	public static void guardarDatosRespaldo(File file, String rutaRecursos, String rutaRespaldo) throws IOException
	{
		cargarFechaSistema();
		String nuevoContenido = " ";
		String extension = obtenerExtension(file.getName());
		String nuevoNombre = rutaRespaldo + file.getName().substring(0, file.getName().lastIndexOf('.'));
		nuevoNombre += "_" + fechaSistema + '.' + extension;

		FileWriter fw = new FileWriter(nuevoNombre, true);
		FileReader fr = new FileReader(nuevoNombre);
		ArrayList<String> contenido = leerArchivo(rutaRecursos + file.getName());

		for (String cadena : contenido)
		{
			nuevoContenido += cadena + "\n";

		}

		fw.write(nuevoContenido);
		fw.flush();

	}

	/**
	 * M�todo que permite obtener la extensi�n de un nombre de archivo
	 * @param fileName
	 * @return extension;
	 */
	public static String obtenerExtension(String fileName)
	{
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0)
		    extension = fileName.substring(i+1);

		return extension;
	}
}
