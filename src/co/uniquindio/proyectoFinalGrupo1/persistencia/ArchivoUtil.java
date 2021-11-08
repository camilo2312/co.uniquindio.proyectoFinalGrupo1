package co.uniquindio.proyectoFinalGrupo1.persistencia;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil 
{
	static String fechaSistema = "";
	
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
	{
		String log = "";
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
	
	private static void cargarFechaSistema() 
	{
		String diaN = "";
		String mesN = "";
		String añoN = "";

		Calendar cal1 = Calendar.getInstance();

		int  dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int año = cal1.get(Calendar.YEAR);
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

		fechaSistema = año + "-" + mesN + "-" + diaN + "-" + hora + "-" + minuto;
	}
}
