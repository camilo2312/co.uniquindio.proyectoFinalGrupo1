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
	 * M�todo que permite obtener el c�digo
	 * @return codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * M�todo que permite asignar y/o actualizar el c�digo de la clase
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * M�todo que permite obtener los estudiantes de bienestar
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> getLstEstudiantes()
	{
		return lstEstudiantes;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de estudiantes
	 * @param lstEstudiantes
	 */
	public void setLstEstudiantes(ArrayList<Estudiante> lstEstudiantes)
	{
		this.lstEstudiantes = lstEstudiantes;
	}

	/**
	 * M�todo que permite obtener la lista de instructores
	 * @return lstInstructores
	 */
	public ArrayList<Instructor> getLstInstructores()
	{
		return lstInstructores;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de
	 * instuctires
	 * @param lstInstructores
	 */
	public void setLstInstructores(ArrayList<Instructor> lstInstructores)
	{
		this.lstInstructores = lstInstructores;
	}

	/**
	 * M�todo que permite obtener la lista de creditos
	 * @return lstCreditos
	 */
	public ArrayList<Credito> getLstCreditos()
	{
		return lstCreditos;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de
	 * creditos
	 * @param lstCreditos
	 */
	public void setLstCreditos(ArrayList<Credito> lstCreditos)
	{
		this.lstCreditos = lstCreditos;
	}

	/**
	 * M�todo que permite obtener la lista de horarios
	 * @return lstHorarios
	 */
	public ArrayList<Horario> getLstHorarios()
	{
		return lstHorarios;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de horarios
	 * @param lstHorarios
	 */
	public void setLstHorarios(ArrayList<Horario> lstHorarios)
	{
		this.lstHorarios = lstHorarios;
	}

	/**
	 * M�todo que permite obtener la lista de lugares
	 * @return lstLugares
	 */
	public ArrayList<Lugar> getLstLugares()
	{
		return lstLugares;
	}

	/**
	 * M�todo que permite asignar y/o actualizar la lista de lugares
	 * @param lstLugares
	 */
	public void setLstLugares(ArrayList<Lugar> lstLugares)
	{
		this.lstLugares = lstLugares;
	}

	/**
	 * M�todo que permite inicializar los datos
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
	 * M�todo que permite ingresar a la aplicaci�n
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
	 * M�todo que permite obtener la lista de tipos de usuario
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
	 * M�todo que permite agregar a un estudiante
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
	 * M�todo que permite actualizar la informaci�n de un estudiante
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
	 * M�todo que permite eliminar un estudiante
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
	 * M�todo que permite obtener un estudiante por su documento
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
	 * M�todo que permite obtener las cantidades actuales
	 * de los datos de la aplicaci�n
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
	 * M�todo que permite agregar un instructor a la lista
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
	 * M�todo que permite obtener un instructor por su
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
	 * M�todo que permite actualizar un instructor
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
	 * M�todo que permite eliminar un instructor
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
	 * M�todo que permite agregar a un Horario
	 * @param horaIni,horaFin,dia
	 * @param code
	 * @return Horario
	 */
	public Horario agregarHorario( String code,String horaIni,String horaFin, Dias dia) throws IOException, NoCreadoException
	{
		Horario horario = obtenerHorario(code);
		if(horario != null)
		{
			throw new NoCreadoException("error al agregar el horario");
		}
		else
		{
			horario = new Horario();
			horario.setCode(code);
			horario.setHoraInicio(horaIni);
			horario.setHoraFinal(horaFin);
			horario.setDia(dia);

			lstHorarios.add(horario);
			Persistencia.guardarHorario(lstHorarios);

			return horario;
		}
	}

	/**
	 * M�todo que permite actualizar un Horario
	 * @param documentoActual
	 * @param code,horaIni,horaFin,dia
	 * @param codigoActual
	 * @return actualizado
	 * @throws NoActualizadoException
	 */

	public boolean actualizarHorario(String code,String CodigoActual,String horaIni,String horaFin, Dias dia) throws NoActualizadoException
	{
		boolean actualizado = false;
		Horario horario = obtenerHorario(CodigoActual);

		if(horario == null)
		{
			throw new NoActualizadoException("error al actualizar el horario");
		}
		else
		{
			horario.setCode(code);
			horario.setHoraInicio(horaIni);
			horario.setHoraFinal(horaFin);
			horario.setDia(dia);

			actualizado = true;
		}

		return actualizado;
	}

	public boolean eliminarHorario(String code) throws NoEliminadoException
	{

		boolean eliminado = false;
		Horario horario = obtenerHorario(code);

		if(horario == null)
		{
			throw new NoEliminadoException("error al eliminar los datos del horario");

		}
		else
		{
			lstLugares.remove(horario);
			eliminado = true;
		}

		return eliminado;
	}

	/**
	 * M�todo que permite agregar a un lugar
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
	 * M�todo que permite actualizar un lugar
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
	 * M�todo que permite eliminar un lugar
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
	 * M�todo que permite obtener un lugar por su
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

	/**
	 * M�todo que permite obtener un horario por su
	 * codigo
	 * @param codigo
	 * @return instructor
	 */

	private Horario obtenerHorario(String codigo)
	{
		for (Horario horario : lstHorarios)
		{
			if(horario.getCode().equals(codigo))
			{
				return horario;
			}
		}
		return null;
	}

	/**
	 * M�todo que permite guardar
	 * los datos de respaldo de la aplicaci�n
	 */
	public void guardarDatosRespaldo()
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

	/**
	 * M�todo que permite obtener la lista de d�as de
	 * los horarios
	 * @return
	 */
	public ArrayList<Dias> obtenerListaDiasData()
	{
		ArrayList<Dias> lstDias = new ArrayList<>();

		lstDias.add(Dias.LUNES);
		lstDias.add(Dias.MARTES);
		lstDias.add(Dias.MIERCOLES);
		lstDias.add(Dias.JUEVES);
		lstDias.add(Dias.VIERNES);

		return lstDias;
	}

}
