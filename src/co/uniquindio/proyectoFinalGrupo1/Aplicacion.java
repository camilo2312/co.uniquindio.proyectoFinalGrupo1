package co.uniquindio.proyectoFinalGrupo1;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.controller.DashBoardController;
import co.uniquindio.proyectoFinalGrupo1.controller.LoginController;
import co.uniquindio.proyectoFinalGrupo1.exceptions.CreditoExisteException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.CupoMaximoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.BienestarEstudiantil;
import co.uniquindio.proyectoFinalGrupo1.model.Credito;
import co.uniquindio.proyectoFinalGrupo1.model.Dias;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.Lugar;
import co.uniquindio.proyectoFinalGrupo1.model.TipoCredito;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import co.uniquindio.proyectoFinalGrupo1.model.Usuario;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private Stage primaryStage;
	private BienestarEstudiantil bienestarEstudiantil = new BienestarEstudiantil("001");

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bienestar universitario");
//		mostrarVentanaLogin();
		mostrarVentanaPrincipal(TipoUsuario.ADMINISTRADOR, "Juan Camilo Ramos R.");
	}

	public static void main(String[] args)
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}

	/**
	 * Método que permite mostrar la ventana login del sistema
	 */
	public void mostrarVentanaLogin()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/LoginView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();

			LoginController loginController = loader.getController();
			loginController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite mostrar la ventana principal de la aplicación
	 * @param tipoUsuario
	 */
	public void mostrarVentanaPrincipal(TipoUsuario tipoUsuario, String nombreUsuario)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/DashBoardView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();

			DashBoardController ventanaPrincipalController = loader.getController();
			ventanaPrincipalController.setAplicacion(this, tipoUsuario, nombreUsuario);

			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(Aplicacion.class.getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite ingresar a la aplicación
	 * @param usuario
	 * @param contrasena
	 * @param tipoUsuario
	 * @return ingreso
	 */
	public Usuario ingresar(String usuario, String contrasena, TipoUsuario tipoUsuario)
	{
		return bienestarEstudiantil.ingresar(usuario, contrasena, tipoUsuario);
	}

	/**
	 * Método que permite obtener la lista de tipos de usuario
	 * @return lstTiposUsuario
	 */
	public ArrayList<TipoUsuario> obtenerListaTiposUsuarios()
	{
		return bienestarEstudiantil.obtenerListaTiposUsuario();
	}

	/**
	 * Método que permite obtener la lista de estudiantes predeterminados
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> obtenerListaEstudiantesData()
	{
		return bienestarEstudiantil.getLstEstudiantes();
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
	 * @throws UsuarioExisteException
	 * @throws IOException
	 */
	public Estudiante agregarEstudiante(String nombre, String documento, String tipoDocumento, int edad, String usuario,
			String contrasena) throws UsuarioExisteException, IOException
	{
		return bienestarEstudiantil.agregarEstudiante(nombre, documento, tipoDocumento, edad, usuario, contrasena);
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
	 * @throws NoActualizadoException
	 */
	public boolean actualizarEstudiante(String documentoActual, String documento, String nombre, String tipoDocumento,
			int edad, String usuario, String contrasena) throws NoActualizadoException
	{
		return bienestarEstudiantil.actualizarEstudiante(documentoActual, documento, nombre, tipoDocumento, edad, usuario, contrasena);
	}

	/**
	 * Método que permite eliminar un estudiante
	 * @param documento
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarEstudiante(String documento) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarEstudiante(documento);
	}

	/**
	 * Método que permite obtener las cantidades actuales
	 * de los datos de la aplicación
	 * @return cantidades
	 */
	public int[] obtenerCantidadesActuales()
	{
		return bienestarEstudiantil.obtenerCantidadesActuales();
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
		return bienestarEstudiantil.agregarInstructor(nombre, documento, tipoDocumento, asignatura, usuario, contrasena);
	}

	/**
	 * Método que permite actualizar in instructor
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
			String asignatura, String usuario, String contrasena) throws NoActualizadoException
	{
		return bienestarEstudiantil.actualizarInstructor(documentoActual, documento, nombre, tipoDocumento, asignatura,
				usuario, contrasena);
	}

	/**
	 * Método que permite eliminar un instructor
	 * @param documento
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarInstructor(String documento) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarInstructor(documento);
	}

	/**
	 * Método que permite obtener la lista de instructores
	 * @return lstInstructores
	 */
	public ArrayList<Instructor> obtenerListaInstructoresData()
	{
		return bienestarEstudiantil.getLstInstructores();
	}

	/**
	 * Método que permite agregar un lugar a la lista
	 * @param nombre
	 * @param codigo
	 * @throws UsuarioExisteException
	 * @throws IOException
	 */

	public Lugar agregarLugar(String nombre, String codigo) throws IOException, NoCreadoException
	{
		return bienestarEstudiantil.agregarLugar(nombre, codigo);
	}

	/**
	 * Método que permite actualizar in lugar
	 * @param documentoActual
	 * @param codigo
	 * @param codigoActual
	 * @return actualizado
	 * @throws NoActualizadoException
	 */
	public boolean actualizarLugar(String nombre, String codigo, String codigoActual) throws NoActualizadoException
	{
		return bienestarEstudiantil.actualizarLugar(nombre, codigo, codigoActual);
	}

	/**
	 * Método que permite eliminar un lugar
	 * @param codigo
	 * @return eliminado
	 * @throws NoEliminadoException
	 *
	 */
	public boolean eliminarLugar(String codigo) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarLugar(codigo);
	}

	/**
	 * Método que permite agregar un horario a la lista
	 * @param horaIni,horaFin,dia
	 * @param code
	 * @throws UsuarioExisteException
	 * @throws IOException
	 */

	public Horario agregarHorario(String code, String horaIni, String horaFin, Dias dia) throws IOException, NoCreadoException
	{
		return bienestarEstudiantil.agregarHorario(code, horaIni, horaFin, dia);
	}

	/**
	 * Método que permite actualizar in lugar
	 * @param documentoActual
	 * @param code,horaIni,horaFin,dia
	 * @param codigoActual
	 * @return actualizado
	 * @throws NoActualizadoException
	 */
	public boolean actualizarHorario( String code, String codigoActual, String horaIni,String horaFin, Dias dia) throws NoActualizadoException
	{
		return bienestarEstudiantil.actualizarHorario(code, codigoActual, horaIni,horaFin,dia);
	}

	/**
	 * Método que permite eliminar un Horario
	 * @param codigo
	 * @return eliminado
	 * @throws NoEliminadoException
	 */

	public boolean eliminarHorario(String code) throws NoEliminadoException
	{

		return bienestarEstudiantil.eliminarHorario(code);
	}

	/**
	 * Método que permite obtener la lista de lugares
	 * @return lstLugares
	 */
	public ArrayList<Lugar> obtenerListaLugaresData()
	{
		return bienestarEstudiantil.getLstLugares();
	}

	/**
	 * Método que permite obtener la lista de Horarios
	 * @return lstHorario
	 */
	public ArrayList<Horario> obtenerListaHorarioData()
	{
		return bienestarEstudiantil.getLstHorarios();
	}

	/**
	 * Método que permite guardar los datos
	 * de respaldo de la aplicación
	 */
	public void guardarDatosRespaldo()
	{
		bienestarEstudiantil.guardarDatosRespaldo();
	}

	/**
	 * Método que permite obtener la lista de días de los
	 * horarios
	 * @return lstDias
	 */
	public ArrayList<Dias> obtenerListaDiasData()
	{
		return bienestarEstudiantil.obtenerListaDiasData();
	}

	/**
	 * Método que permite agregar un credito
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
	 * @return credito
	 */
	public Credito agregarCredito(String codigo, String nombre, int duracion, int cupoMaximo, Lugar lugar,
			Instructor instructor, TipoCredito tipo, boolean homologable, double costo, int asistenciaMinima, ArrayList<Horario> lstHorariosSeleccionados) throws CreditoExisteException
	{
		return bienestarEstudiantil.agregarCredito(codigo, nombre, duracion, cupoMaximo, lugar, instructor, tipo, homologable, costo, asistenciaMinima, lstHorariosSeleccionados);
	}

	/**
	 * Método que permite obtener la lista de creditos
	 * @return
	 */
	public ArrayList<Credito> obtenerListaCreditosData()
	{
		return bienestarEstudiantil.getLstCreditos();
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
		return bienestarEstudiantil.actualizarCredito(codigoActual, codigo, nombre, duracion, cupoMaximo, lugar, instructor,
													  tipo, homologable, costo, asistenciaMinima);
	}

	/**
	 * Método que permite eliminar un credito
	 * @param codigo
	 * @return eliminado
	 */
	public boolean eliminarCredito(String codigo) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarCredito(codigo);
	}

	/**
	 * Método que permite obtener el usuario conectado
	 * @return usuario
	 */
	public Usuario obtenerUsuarioActual()
	{
		return bienestarEstudiantil.getUsuarioActual();
	}

	/**
	 * Método que permite obtener la lista de
	 * creditos por estudiante
	 * @param estudiante
	 * @return lstCreditos
	 */
	public ArrayList<Credito> obtenerListaCreditosInscritosEstudiante(Estudiante estudiante)
	{
		return bienestarEstudiantil.obtenerListaCreditosInscritosEstudiante(estudiante);
	}

	/**
	 * Método que permite actualizar la inscripcion de Creditos
	 * @param lstCreditosData
	 * @param lstCreditosInscritosData
	 * @param estudiante
	 * @return actualizado
	 */
	public boolean actualizarInscripcionCreditos(ObservableList<Credito> lstCreditosData,
			ObservableList<Credito> lstCreditosInscritosData, Estudiante estudiante) throws CupoMaximoException
	{
		return bienestarEstudiantil.actualizarInscripcionCreditos(lstCreditosData, lstCreditosInscritosData, estudiante);
	}

	/**
	 * Método que permite obtener la lista de creditos
	 * por instructor
	 * @param instructor
	 * @return lstCreditos
	 */
	public ArrayList<Credito> obtenerListaCreditosInstructor(Instructor instructor)
	{
		return bienestarEstudiantil.obtenerListaCreditosInstructor(instructor);
	}
}
