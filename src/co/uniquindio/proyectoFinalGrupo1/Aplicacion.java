package co.uniquindio.proyectoFinalGrupo1;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.controller.DashBoardController;
import co.uniquindio.proyectoFinalGrupo1.controller.LoginController;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.BienestarEstudiantil;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.Lugar;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import co.uniquindio.proyectoFinalGrupo1.model.Usuario;
import javafx.application.Application;
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
		mostrarVentanaLogin();
		//mostrarVentanaPrincipal(TipoUsuario.ADMINISTRADOR, "Juan Camilo Ramos R.");
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
	 * M�todo que permite mostrar la ventana login del sistema
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
	 * M�todo que permite mostrar la ventana principal de la aplicaci�n
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
	 * M�todo que permite ingresar a la aplicaci�n
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
	 * M�todo que permite obtener la lista de tipos de usuario
	 * @return lstTiposUsuario
	 */
	public ArrayList<TipoUsuario> obtenerListaTiposUsuarios()
	{
		return bienestarEstudiantil.obtenerListaTiposUsuario();
	}

	/**
	 * M�todo que permite obtener la lista de estudiantes predeterminados
	 * @return lstEstudiantes
	 */
	public ArrayList<Estudiante> obtenerListaEstudiantesData()
	{
		return bienestarEstudiantil.getLstEstudiantes();
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
	 * @throws UsuarioExisteException
	 * @throws IOException
	 */
	public Estudiante agregarEstudiante(String nombre, String documento, String tipoDocumento, int edad, String usuario,
			String contrasena) throws UsuarioExisteException, IOException
	{
		return bienestarEstudiantil.agregarEstudiante(nombre, documento, tipoDocumento, edad, usuario, contrasena);
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
	 * @throws NoActualizadoException
	 */
	public boolean actualizarEstudiante(String documentoActual, String documento, String nombre, String tipoDocumento,
			int edad, String usuario, String contrasena) throws NoActualizadoException
	{
		return bienestarEstudiantil.actualizarEstudiante(documentoActual, documento, nombre, tipoDocumento, edad, usuario, contrasena);
	}

	/**
	 * M�todo que permite eliminar un estudiante
	 * @param documento
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarEstudiante(String documento) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarEstudiante(documento);
	}

	/**
	 * M�todo que permite obtener las cantidades actuales
	 * de los datos de la aplicaci�n
	 * @return cantidades
	 */
	public int[] obtenerCantidadesActuales()
	{
		return bienestarEstudiantil.obtenerCantidadesActuales();
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
		return bienestarEstudiantil.agregarInstructor(nombre, documento, tipoDocumento, asignatura, usuario, contrasena);
	}

	/**
	 * M�todo que permite actualizar in instructor
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
	 * M�todo que permite eliminar un instructor
	 * @param documento
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarInstructor(String documento) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarInstructor(documento);
	}

	/**
	 * M�todo que permite obtener la lista de instructores
	 * @return lstInstructores
	 */
	public ArrayList<Instructor> obtenerListaInstructoresData()
	{
		return bienestarEstudiantil.getLstInstructores();
	}

	/**
	 * M�todo que permite agregar un lugar a la lista
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
	 * M�todo que permite actualizar in lugar
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
	 * M�todo que permite eliminar un lugar
	 * @param codigo
	 * @return eliminado
	 * @throws NoEliminadoException
	 */
	public boolean eliminarLugar(String codigo) throws NoEliminadoException
	{
		return bienestarEstudiantil.eliminarLugar(codigo);
	}

	/**
	 * M�todo que permite obtener la lista de lugares
	 * @return lstLugares
	 */
	public ArrayList<Lugar> obtenerListaLugaresData()
	{
		return bienestarEstudiantil.getLstLugares();
	}

	public void guardarDatosRespaldo() 
	{
		BienestarEstudiantil.guardarDatosRespaldo();
	}
}
