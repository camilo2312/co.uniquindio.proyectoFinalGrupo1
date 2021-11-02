package co.uniquindio.proyectoFinalGrupo1.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.NombreVentana;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Juan Camilo Ramos R.
 *
 */
public class DashBoardController implements Initializable
{

	private Aplicacion aplicacion;
	private GestionEstudiantesController gestionEstudiantesController;
	private InicioController inicioController;

	@FXML
    private Button btnCreditos;

	@FXML
    private Label lblNombreUsuario;

	@FXML
    private Button btnInicio;

    @FXML
    private Button btnEstudiantes;

    @FXML
    private Button btnInstructores;

    @FXML
    private Button btnLugares;
    
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private ImageView imgUser;

    @FXML
    private AnchorPane anchorPanePrincipal;
    
    

    @FXML
    void inicioAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.INICIO);
    }


	@FXML
    void gestionEstudiantesAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.GESTION_ESTUDIANTES);
    }

	@FXML
    void gestionInstructoresAction(ActionEvent event)
	{

    }

    @FXML
    void gestionCreditosAction(ActionEvent event)
    {

    }

    @FXML
    void gestionLugaresAction(ActionEvent event)
    {

    }
    
    @FXML
    void cerrarSesionAction(ActionEvent event) 
    {
    	cerrarSesion();
    }


    /**
     * Método que permite inicializar los componentes de la vista
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

    /**
     * Método que permite inicializar la clase aplicación
     * @param aplicacion
     * @param tipoUsuario
     */
	public void setAplicacion(Aplicacion aplicacion, TipoUsuario tipoUsuario, String nombreUsuario)
	{
		this.aplicacion = aplicacion;
		insertarImagenTipoUsuario(tipoUsuario, nombreUsuario);
		abrirVentana(NombreVentana.INICIO);
	}

	/**
	 * Método que permite mostrar la imagen asociada al tipo
	 * de usuario
	 * @param tipoUsuario
	 * @param nombreUsuario
	 */
	private void insertarImagenTipoUsuario(TipoUsuario tipoUsuario, String nombreUsuario)
	{
		Image image = null;
		switch (tipoUsuario) {
		case ADMINISTRADOR:
			image = new Image(getClass().getResourceAsStream("../images/business.png"));
			break;
		case ESTUDIANTE:
			image = new Image(getClass().getResourceAsStream("../images/graduates.png"));
			break;
		case INSTRUCTOR:
			image = new Image(getClass().getResourceAsStream("../images/teacher.png"));
			break;
		default:
			break;
		}

		if(image != null)
		{
			imgUser.setImage(image);
		}

		lblNombreUsuario.setText(nombreUsuario);
	}

	/**
	 * Método que permite abrir las vistas de los menus
	 */
    private void abrirVentana(NombreVentana nombreVentana)
    {
    	FXMLLoader loader = new FXMLLoader();
		AnchorPane vistaCargada = null;

		try
		{
	    	switch (nombreVentana) {
			case INICIO:
				loader.setLocation(getClass().getResource("../view/InicioView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.inicioController = loader.getController();
				this.inicioController.setAplicacion(this.aplicacion);
				break;
			case GESTION_ESTUDIANTES:
				loader.setLocation(getClass().getResource("../view/GestionEstudiantesView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.gestionEstudiantesController = loader.getController();
				this.gestionEstudiantesController.setAplicacion(this.aplicacion);
				break;
			case GESTION_INSTRUCTOR:
				break;
			case GESTION_CREDITOS:
				break;
			case GESTION_LUGARES:
				break;
			default:
				break;
	    	}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
    
    /**
	 * Método que permite cerrar sesion y lo devuelve al login
	 */
    private void cerrarSesion()
    {
    	aplicacion.mostrarVentanaLogin();
    }
}
