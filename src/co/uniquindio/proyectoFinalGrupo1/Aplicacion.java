package co.uniquindio.proyectoFinalGrupo1;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.controller.DashBoardController;
import co.uniquindio.proyectoFinalGrupo1.controller.LoginController;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.BienestarEstudiantil;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
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
//		mostrarVentanaPrincipal(TipoUsuario.ADMINISTRADOR, "Juan Camilo Ramos R.");
	}

	public static void main(String[] args)
	{
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
			primaryStage.setScene(scene);
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
	 */
	public Estudiante agregarEstudiante(String nombre, String documento, String tipoDocumento, int edad, String usuario,
			String contrasena) throws UsuarioExisteException 
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
	 */
	public boolean actualizarEstudiante(String documentoActual, String documento, String nombre, String tipoDocumento,
			int edad, String usuario, String contrasena)
	{
		return bienestarEstudiantil.actualizarEstudiante(documentoActual, documento, nombre, tipoDocumento, edad, usuario, contrasena);
	}

	/**
	 * Método que permite eliminar un estudiante
	 * @param documento
	 * @return eliminado
	 */
	public boolean eliminarEstudiante(String documento)
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
}
