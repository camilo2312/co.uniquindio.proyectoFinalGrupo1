package co.uniquindio.proyectoFinalGrupo1.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;

/**
 * Clase bienestar estudiantil
 * @author Juan Camilo Ramos R.
 *
 */
public class BienestarEstudiantil
{
	private String codigo;
	private ArrayList<Estudiante> lstEstudiantes;
	private ArrayList<Instructor> lstInstructores;
	private ArrayList<Credito> lstCreditos;
	private ArrayList<Horario> lstHorarios;
	private ArrayList<Lugar> lstLugares;
	private Administrador administrador;

	/**
	 * Constructor de la clase
	 * @param codigo
	 */
	public BienestarEstudiantil(String codigo)
	{
		this.codigo = codigo;
		lstEstudiantes  = new ArrayList<Estudiante>();
		lstInstructores = new ArrayList<Instructor>();
		lstHorarios     = new ArrayList<Horario>();
		lstCreditos     = new ArrayList<Credito>();
		lstLugares      = new ArrayList<Lugar>();
		inicializarDatos();
	}

	/**
	 * Método que permite obtener el código
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * Método que permite asignar y/o actualizar el código de la clase
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Método que permite obtener los estudiantes de bienestar
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> getLstEstudiantes()
	{
		return lstEstudiantes;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de estudiantes
	 * @param lstEstudiantes
	 */
	public void setLstEstudiantes(ArrayList<Estudiante> lstEstudiantes)
	{
		this.lstEstudiantes = lstEstudiantes;
	}

	/**
	 * Método que permite obtener la lista de instructores
	 * @return lstInstructores
	 */
	public ArrayList<Instructor> getLstInstructores()
	{
		return lstInstructores;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de
	 * instuctires
	 * @param lstInstructores
	 */
	public void setLstInstructores(ArrayList<Instructor> lstInstructores)
	{
		this.lstInstructores = lstInstructores;
	}

	/**
	 * Método que permite obtener la lista de creditos
	 * @return lstCreditos
	 */
	public ArrayList<Credito> getLstCreditos()
	{
		return lstCreditos;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de
	 * creditos
	 * @param lstCreditos
	 */
	public void setLstCreditos(ArrayList<Credito> lstCreditos)
	{
		this.lstCreditos = lstCreditos;
	}

	/**
	 * Método que permite obtener la lista de horarios
	 * @return lstHorarios
	 */
	public ArrayList<Horario> getLstHorarios()
	{
		return lstHorarios;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de horarios
	 * @param lstHorarios
	 */
	public void setLstHorarios(ArrayList<Horario> lstHorarios)
	{
		this.lstHorarios = lstHorarios;
	}

	/**
	 * Método que permite obtener la lista de lugares
	 * @return lstLugares
	 */
	public ArrayList<Lugar> getLstLugares()
	{
		return lstLugares;
	}

	/**
	 * Método que permite asignar y/o actualizar la lista de lugares
	 * @param lstLugares
	 */
	public void setLstLugares(ArrayList<Lugar> lstLugares)
	{
		this.lstLugares = lstLugares;
	}

	/**
	 * Método que permite inicializar los datos
	 */
	private void inicializarDatos()
	{
		administrador = (Administrador) Persistencia.cargarDatosXML(Persistencia.RUTA_ARCHIVO_MODELO_ADMINISTRADOR_XML);
		
		lstEstudiantes.add((Estudiante) Persistencia.cargarDatosXML(Persistencia.RUTA_ARCHIVO_MODELO_ESTUDIANTE_XML));
		
		lstInstructores.add((Instructor) Persistencia.cargarDatosXML(Persistencia.RUTA_ARCHIVO_MODELO_INSTRUCTOR_XML));
		
		lstLugares.add((Lugar) Persistencia.cargarDatosXML(Persistencia.RUTA_ARCHIVO_MODELO_LUGARES_XML));
		
		lstHorarios.add((Horario) Persistencia.cargarDatosXML(Persistencia.RUTA_ARCHIVO_MODELO_HORARIOS_XML));
		
	}

	/**
	 * Método que permite ingresar a la aplicación
	 * @param usuario
	 * @param contrasena
	 * @param tipoUsuario
	 * @return ingreso
	 */
	public Usuario ingresar(String nombreUsuario, String contrasena, TipoUsuario tipoUsuario)
	{
		Usuario usuario = null;
		Estudiante estudiante = null;
		Instructor instructor = null;

		switch (tipoUsuario) {
		case ESTUDIANTE:
			estudiante = lstEstudiantes.stream().filter(x -> x.getUsuario().equals(nombreUsuario) && x.getContrasena().equals(contrasena)).findAny().orElse(null);

			if(estudiante != null)
				usuario = estudiante;

			break;
		case INSTRUCTOR:
			instructor = lstInstructores.stream().filter(x -> x.getUsuario().equals(nombreUsuario) && x.getContrasena().equals(contrasena)).findAny().orElse(null);

			if(instructor != null)
				usuario = instructor;

			break;
		case ADMINISTRADOR:
			if(administrador.getUsuario().equals(nombreUsuario) && administrador.getContrasena().equals(contrasena))
				usuario = administrador;
			break;
		default:
			break;
		}

		return usuario;
	}

	/**
	 * Método que permite obtener la lista de tipos de usuario
	 * @return lstTiposUsuario
	 */
	public ArrayList<TipoUsuario> obtenerListaTiposUsuario()
	{
		ArrayList<TipoUsuario> lstTipoUsuarios = new ArrayList<TipoUsuario>();
		lstTipoUsuarios.add(TipoUsuario.ESTUDIANTE);
		lstTipoUsuarios.add(TipoUsuario.ADMINISTRADOR);
		lstTipoUsuarios.add(TipoUsuario.INSTRUCTOR);
		return lstTipoUsuarios;
	}

	/**
	 * Método que permite agregar a un estudiante
	 * @param nombre
	 * @param documento
	 * @param tipoDocumento
	 * @param edad
	 * @param usuario
	 * @param contrasena
	 * @return estudiante
	 */
	public Estudiante agregarEstudiante(String nombre, String documento, String tipoDocumento, int edad, String usuario,
			String contrasena) throws UsuarioExisteException, IOException
	{
		Estudiante estudiante = obtenerEstudiante(documento);
		if(estudiante != null)
		{
			throw new UsuarioExisteException("Error, el usuario" + documento + " ya existe");
		}
		else
		{
				estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setDocumento(documento);
				estudiante.setTipoDocumento(tipoDocumento);
				estudiante.setEdad(edad);
				estudiante.setUsuario(usuario);
				estudiante.setContrasena(contrasena);

				lstEstudiantes.add(estudiante);
				Persistencia.guardarEstudiantes(lstEstudiantes);

				return estudiante;
		}
	}

	/**
	 * Método que permite actualizar la información de un estudiante
	 * @param documentoActual
	 * @param documento
	 * @param nombre
	 * @param tipoDocumento
	 * @param edad
	 * @param usuario
	 * @param contrasena
	 * @return actualizado
	 */
	public boolean actualizarEstudiante(String documentoActual, String documento, String nombre, String tipoDocumento,
			int edad, String usuario, String contrasena) throws NoActualizadoException
	{
		boolean actualizado = false;
		Estudiante  estudiante = obtenerEstudiante(documentoActual);

		if(estudiante == null)
		{
			throw new NoActualizadoException("error al actualizar los datos del estudiante");

		}
		else
		{
			estudiante.setNombre(nombre);
			estudiante.setDocumento(documento);
			estudiante.setTipoDocumento(tipoDocumento);
			estudiante.setEdad(edad);
			estudiante.setUsuario(usuario);
			estudiante.setContrasena(contrasena);

			actualizado = true;
		}

		return actualizado;
	}

	/**
	 * Método que permite eliminar un estudiante
	 * @param documento
	 * @return eliminado
	 */
	public boolean eliminarEstudiante(String documento) throws NoEliminadoException
	{
		boolean eliminado = false;
		Estudiante estudiante = obtenerEstudiante(documento);

		if(estudiante == null)
		{
			throw new NoEliminadoException("error al eliminar los datos del estudiante");

		}
		else
		{
			lstEstudiantes.remove(estudiante);
			eliminado = true;
		}

		return eliminado;
	}

	/**
	 * Método que permite obtener un estudiante por su documento
	 * @param documento
	 * @return estudiante
	 */
	private Estudiante obtenerEstudiante(String documento)
	{
		for (Estudiante estudiante : lstEstudiantes)
		{
			if(estudiante.getDocumento().equals(documento))
			{
				return estudiante;
			}
		}

		return null;
	}

	/**
	 * Método que permite obtener las cantidades actuales
	 * de los datos de la aplicación
	 * @return cantidades
	 */
	public int[] obtenerCantidadesActuales()
	{
		int[] cantidades = new int[4];

		cantidades[0] = lstEstudiantes.size();
		cantidades[1] = lstInstructores.size();
		cantidades[2] = lstCreditos.size();
		cantidades[3] = lstLugares.size();

		return cantidades;
	}

	/**
	 * Método que permite agregar un instructor a la lista
	 * @param nombre
	 * @param documento
	 * @param tipoDocumento
	 * @param asignatura
	 * @param usuario
	 * @param contrasena
	 * @return instructor
	 * @throws UsuarioExisteException
	 * @throws IOException
	 */
	public Instructor agregarInstructor(String nombre, String documento, String tipoDocumento, String asignatura,
			String usuario, String contrasena) throws UsuarioExisteException, IOException
	{
		Instructor instructor = obtenerInstructor(documento);
		if(instructor != null)
		{
			throw new UsuarioExisteException("Error, El usuario" + documento + " ya existe");
		}
		else
		{
			instructor = new Instructor();
			instructor.setNombre(nombre);
			instructor.setDocumento(documento);
			instructor.setTipoDocumento(tipoDocumento);
			instructor.setAsignatura(asignatura);
			instructor.setUsuario(usuario);
			instructor.setContrasena(contrasena);

			lstInstructores.add(instructor);
			Persistencia.guardarInstructor(lstInstructores);

			return instructor;
		}
	}

	/**
	 * Método que permite obtener un instructor por su
	 * documento
	 * @param documento
	 * @return instructor
	 */
	private Instructor obtenerInstructor(String documento)
	{
		for (Instructor instructor : lstInstructores)
		{
			if(instructor.getDocumento().equals(documento))
			{
				return instructor;
			}
		}

		return null;
	}

	/**
	 * Método que permite actualizar un instructor
	 * @param documentoActual
	 * @param documento
	 * @param nombre
	 * @param tipoDocumento
	 * @param asignatura
	 * @param usuario
	 * @param contrasena
	 * @return actualizado
	 * @throws NoActualizadoException
	 */
	public boolean actualizarInstructor(String documentoActual, String documento, String nombre, String tipoDocumento,
			String asignatura, String usuario, String contrasena)  throws NoActualizadoException
	{
		boolean actualizado = false;
		Instructor instructor = obtenerInstructor(documentoActual);

		if(instructor == null)
		{
			throw new NoActualizadoException("error al actualizar los datos del instructor");
		}
		else
		{
			instructor.setNombre(nombre);
			instructor.setDocumento(documento);
			instructor.setTipoDocumento(tipoDocumento);
			instructor.setAsignatura(asignatura);
			instructor.setUsuario(usuario);
			instructor.setContrasena(contrasena);

			actualizado = true;
		}

		return actualizado;
	}

	/**
	 * Método que permite eliminar un instructor
	 * @param documento
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarInstructor(String documento) throws NoEliminadoException
	{
		boolean eliminado = false;
		Instructor instructor = obtenerInstructor(documento);

		if(instructor == null)
		{
			throw new NoEliminadoException("error al eliminar los datos del Instructor");

		}
		else
		{
			lstInstructores.remove(instructor);
			eliminado = true;
		}

		return eliminado;
	}

	/**
	 * Método que permite agregar a un lugar
	 * @param nombre
	 * @param codigo
	 * @return lugar
	 */
	public Lugar agregarLugar(String nombre, String codigo) throws IOException, NoCreadoException
	{
		Lugar lugar = obtenerLugar(codigo);
		if(lugar != null)
		{
			throw new NoCreadoException("error al agregar el lugar");
		}
		else
		{
			lugar = new Lugar();
			lugar.setNombre(nombre);
			lugar.setCodigo(codigo);

			lstLugares.add(lugar);
			Persistencia.guardarLugares(lstLugares);

			return lugar;
		}
	}

	/**
	 * Método que permite actualizar un lugar
	 * @param documentoActual
	 * @param codigo
	 * @param codigoActual
	 * @return actualizado
	 * @throws NoActualizadoException
	 */
	public boolean actualizarLugar(String nombre, String codigo, String codigoActual) throws NoActualizadoException
	{
		boolean actualizado = false;
		Lugar lugar = obtenerLugar(codigoActual);

		if(lugar == null)
		{
			throw new NoActualizadoException("error al actualizar el lugar");
		}
		else
		{
			lugar.setNombre(nombre);
			lugar.setCodigo(codigo);

			actualizado = true;
		}

		return actualizado;
	}

	/**
	 * Método que permite eliminar un lugar
	 * @param codigo
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarLugar(String codigo) throws NoEliminadoException
	{
		boolean eliminado = false;
		Lugar lugar = obtenerLugar(codigo);

		if(lugar == null)
		{
			throw new NoEliminadoException("error al eliminar los datos del Instructor");

		}
		else
		{
			lstLugares.remove(lugar);
			eliminado = true;
		}

		return eliminado;
	}

	/**
	 * Método que permite obtener un lugar por su
	 * codigo
	 * @param codigo
	 * @return instructor
	 */
	private Lugar obtenerLugar(String codigo)
	{
		for (Lugar lugar : lstLugares)
		{
			if(lugar.getCodigo().equals(codigo))
			{
				return lugar;
			}
		}
		return null;
	}

	public static void guardarDatosRespaldo() 
	{
		File carpeta = new File(Persistencia.RUTA_RECURSOS);
		
		if(carpeta != null && carpeta.listFiles().length > 0)
		{
			for (File file : carpeta.listFiles()) 
			{
				if(file.getName().contains(".xml") || (file.getName().contains(".txt") && !file.getName().toLowerCase().contains("log")))
				{
					Persistencia.guardarArchivoRespaldo(file);
				}
			}
		}
		
	}


}
