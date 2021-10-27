package co.uniquindio.proyectoFinalGrupo1;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.proyectoFinalGrupo1.controller.DashBoardController;
import co.uniquindio.proyectoFinalGrupo1.controller.LoginController;
import co.uniquindio.proyectoFinalGrupo1.model.BienestarEstudiantil;
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
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * M�todo que permite mostrar la ventana login del sistema
	 */
	private void mostrarVentanaLogin()
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
			primaryStage.setScene(scene);
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
}
