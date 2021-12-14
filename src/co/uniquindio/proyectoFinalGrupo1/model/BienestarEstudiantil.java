package co.uniquindio.proyectoFinalGrupo1.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.exceptions.CreditoExisteException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.CupoMaximoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;
import javafx.collections.ObservableList;

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
	private Usuario usuarioActual;

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
	 * Método que permite obtener el usuario conectado
	 * @return
	 */
	public Usuario getUsuarioActual()
	{
		return usuarioActual;
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
		usuarioActual = null;
		Estudiante estudiante = null;
		Instructor instructor = null;

		switch (tipoUsuario) {
		case ESTUDIANTE:
			estudiante = lstEstudiantes.stream().filter(x -> x.getUsuario().equals(nombreUsuario) && x.getContrasena().equals(contrasena)).findAny().orElse(null);

			if(estudiante != null)
				usuarioActual = estudiante;

			break;
		case INSTRUCTOR:
			instructor = lstInstructores.stream().filter(x -> x.getUsuario().equals(nombreUsuario) && x.getContrasena().equals(contrasena)).findAny().orElse(null);

			if(instructor != null)
				usuarioActual = instructor;

			break;
		case ADMINISTRADOR:
			if(administrador.getUsuario().equals(nombreUsuario) && administrador.getContrasena().equals(contrasena))
				usuarioActual = administrador;
			break;
		default:
			break;
		}

		return usuarioActual;
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
	 * Método que permite agregar a un Horario
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
	 * Método que permite actualizar un Horario
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
			lstHorarios.remove(horario);
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

	/**
	 * Método que permite obtener un horario por su
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
	 * Método que permite guardar
	 * los datos de respaldo de la aplicación
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
	 * Método que permite obtener la lista de días de
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

	/**
	 * Método que permite crear un credito
	 * @param codigo2
	 * @param nombre
	 * @param duracion
	 * @param cupoMaximo
	 * @param lugar
	 * @param instructor
	 * @param tipo
	 * @param homologable
	 * @param costo
	 * @param asistenciaMinima
	 * @return credito
	 */
	public Credito agregarCredito(String codigo, String nombre, int duracion, int cupoMaximo, Lugar lugar,
			Instructor instructor, TipoCredito tipo, boolean homologable, double costo, int asistenciaMinima, ArrayList<Horario> lstHorariosSeleccionados) throws CreditoExisteException
	{

		switch (tipo)
		{
			case ACADEMICO:
				CreditoAcademico credito = (CreditoAcademico)obtenerCredito(codigo);
				if(credito == null)
				{
					credito = new CreditoAcademico();
					credito.setCodigo(codigo);
					credito.setNombre(nombre);
					credito.setDuracion(duracion);
					credito.setCupoMaximo(cupoMaximo);
					credito.setLugar(lugar);
					credito.setInstructor(instructor);
					credito.setTipoCredito(tipo);
					credito.setHorarios(lstHorariosSeleccionados);
					credito.setHomologable(homologable);
					credito.setLstEstudiantes(new ArrayList<>());

					lstCreditos.add(credito);

					return credito;
				}
				else
				{
					throw new CreditoExisteException("Error el crédito" + codigo + " ya existe");
				}
			case CULTURAL:
				CreditoCultural creditoCultural = (CreditoCultural)obtenerCredito(codigo);
				if(creditoCultural == null)
				{
					creditoCultural = new CreditoCultural();
					creditoCultural.setCodigo(codigo);
					creditoCultural.setNombre(nombre);
					creditoCultural.setDuracion(duracion);
					creditoCultural.setCupoMaximo(cupoMaximo);
					creditoCultural.setLugar(lugar);
					creditoCultural.setInstructor(instructor);
					creditoCultural.setTipoCredito(tipo);
					creditoCultural.setHorarios(lstHorariosSeleccionados);
					creditoCultural.setCosto(costo);
					creditoCultural.setLstEstudiantes(new ArrayList<>());

					lstCreditos.add(creditoCultural);

					return creditoCultural;
				}
				else
				{
					throw new CreditoExisteException("Error el crédito" + codigo + " ya existe");
				}
			case DEPORTIVO:
				CreditoDeportivo creditoDeportivo = (CreditoDeportivo)obtenerCredito(codigo);
				if(creditoDeportivo == null)
				{
					creditoDeportivo = new CreditoDeportivo();
					creditoDeportivo.setCodigo(codigo);
					creditoDeportivo.setNombre(nombre);
					creditoDeportivo.setDuracion(duracion);
					creditoDeportivo.setCupoMaximo(cupoMaximo);
					creditoDeportivo.setLugar(lugar);
					creditoDeportivo.setInstructor(instructor);
					creditoDeportivo.setTipoCredito(tipo);
					creditoDeportivo.setHorarios(lstHorariosSeleccionados);
					creditoDeportivo.setAsistenciaMinima(asistenciaMinima);
					creditoDeportivo.setLstEstudiantes(new ArrayList<>());

					lstCreditos.add(creditoDeportivo);

					return creditoDeportivo;
				}
				else
				{
					throw new CreditoExisteException("Error el crédito" + codigo + " ya existe");
				}
			default:
				break;
		}

		return null;
	}

	/**
	 * Método que permite actualizar el credito
	 * @param codigoActual
	 * @param codigo
	 * @param nombre
	 * @param duracion
	 * @param cupoMaximo
	 * @param lugar
	 * @param instructor
	 * @param tipo
	 * @param homologable
	 * @param costo
	 * @param asistenciaMinima
	 * @return actualizado
	 */
	public boolean actualizarCredito(String codigoActual, String codigo, String nombre, int duracion, int cupoMaximo,
			Lugar lugar, Instructor instructor, TipoCredito tipo, boolean homologable, double costo,
			int asistenciaMinima) throws NoActualizadoException
	{
		boolean actualizado = true;
		int index = 0;
		Credito credito = obtenerCredito(codigoActual);
		if(credito != null)
		{
			switch (tipo) {
			case ACADEMICO:
				CreditoAcademico academico = new CreditoAcademico();
				academico.setCodigo(codigo);
				academico.setNombre(nombre);
				academico.setDuracion(duracion);
				academico.setCupoMaximo(cupoMaximo);
				academico.setLugar(lugar);
				academico.setInstructor(instructor);
				academico.setTipoCredito(tipo);
				academico.setHomologable(homologable);

				index = lstCreditos.indexOf(credito);
				lstCreditos.set(index, academico);

				actualizado = true;

				break;
			case CULTURAL:
				CreditoCultural cultural = new CreditoCultural();
				cultural.setCodigo(codigo);
				cultural.setNombre(nombre);
				cultural.setDuracion(duracion);
				cultural.setCupoMaximo(cupoMaximo);
				cultural.setLugar(lugar);
				cultural.setInstructor(instructor);
				cultural.setTipoCredito(tipo);
				cultural.setCosto(costo);

				index = lstCreditos.lastIndexOf(credito);
				lstCreditos.set(index, cultural);

				actualizado = true;
				break;
			case DEPORTIVO:
				CreditoDeportivo deportivo = new CreditoDeportivo();
				deportivo.setCodigo(codigo);
				deportivo.setNombre(nombre);
				deportivo.setDuracion(duracion);
				deportivo.setCupoMaximo(cupoMaximo);
				deportivo.setLugar(lugar);
				deportivo.setInstructor(instructor);
				deportivo.setTipoCredito(tipo);
				deportivo.setAsistenciaMinima(asistenciaMinima);

				index = lstCreditos.lastIndexOf(credito);
				lstCreditos.set(index, deportivo);

				actualizado = true;
				break;

			default:
				break;
			}

			actualizado = true;
		}
		else
		{
			throw new NoActualizadoException("Error al intentar actualizar el crédito");
		}


		return actualizado;
	}

	/**
	 * Método que permite obtener el un credito mediante
	 * su código
	 * @param codigo
	 * @return credito
	 */
	private Credito obtenerCredito(String codigo)
	{
		for (Credito credito : lstCreditos)
		{
			if(credito.getCodigo().equals(codigo))
			{
				return credito;
			}
		}
		return null;
	}

	/**
	 * Método que permite eliminar un crédito
	 * @param codigo2
	 * @return eliminado
	 */
	public boolean eliminarCredito(String codigo) throws NoEliminadoException
	{
		boolean eliminado = false;
		Credito credito = obtenerCredito(codigo);
		if(credito != null)
		{
			lstCreditos.remove(credito);
			eliminado = true;
		}
		else
		{
			throw new NoEliminadoException("Error al intentar eliminar el crédito con código " + codigo);
		}

		return eliminado;
	}

	/**
	 * Método que permite obtener la lista de creditos por
	 * estudiante
	 * @param documento
	 * @return lstCreditos
	 */
	public ArrayList<Credito> obtenerListaCreditosInscritosEstudiante(Estudiante estudiante)
	{
		ArrayList<Credito> lstCreditosEstudiante = new ArrayList<>();

		if(lstCreditos != null && lstCreditos.size() > 0)
		{
			for (Credito credito : lstCreditos)
			{
				if(credito.getLstEstudiantes() != null && credito.getLstEstudiantes().contains(estudiante))
				{
					lstCreditosEstudiante.add(credito);
				}
			}
		}

		return lstCreditosEstudiante;
	}

	/**
	 * Método que permite actualizar los creditos inscritos por estudiante
	 * @param lstCreditosData
	 * @param lstCreditosInscritosData
	 * @param estudiante
	 * @return actualizado
	 */
	public boolean actualizarInscripcionCreditos(ObservableList<Credito> lstCreditosData,
			ObservableList<Credito> lstCreditosInscritosData, Estudiante estudiante) throws CupoMaximoException
	{
		Credito creditoAux = null;
		boolean actualizado = true;

		for (Credito credito : lstCreditosData)
		{
			creditoAux = obtenerCredito(credito.getCodigo());
			if(creditoAux != null && creditoAux.getLstEstudiantes().contains(estudiante))
			{
				creditoAux.getLstEstudiantes().remove(estudiante);
			}
		}

		for (Credito credito : lstCreditosInscritosData)
		{
			creditoAux = obtenerCredito(credito.getCodigo());
			if(creditoAux != null && !creditoAux.getLstEstudiantes().contains(estudiante))
			{
				if(creditoAux.getLstEstudiantes().size() != creditoAux.getCupoMaximo())
				{
					creditoAux.getLstEstudiantes().add(estudiante);
				}
				else
				{
					actualizado = false;
					throw new CupoMaximoException("No se pueden inscribir más estudiantes");
				}
			}
		}

		return actualizado;
	}

	/**
	 * Método que permite obtener la lista de creditos por
	 * instructor
	 * @param instructor
	 * @return lstCreditos
	 */
	public ArrayList<Credito> obtenerListaCreditosInstructor(Instructor instructor)
	{
		ArrayList<Credito> lstCreditosInstructor = new ArrayList<>();
		for (Credito credito : lstCreditos)
		{
			if(credito.getInstructor().getDocumento().equals(instructor.getDocumento()))
			{
				lstCreditosInstructor.add(credito);
			}
		}

		return lstCreditosInstructor;
	}

}
