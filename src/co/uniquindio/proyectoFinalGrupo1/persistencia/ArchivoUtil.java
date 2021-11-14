package co.uniquindio.proyectoFinalGrupo1.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil
{
	static String fechaSistema = "";

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

	public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

		XMLDecoder decodificadorXML;
		Object objetoXML;

		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
		objetoXML = decodificadorXML.readObject();
		decodificadorXML.close();
		return objetoXML;

	}

	public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {

		XMLEncoder codificadorXML;

		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.writeObject(objeto);
		codificadorXML.close();

	}
}
