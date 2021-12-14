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
import javafx.scene.layout.VBox;

/**
 * Controlador de la vista principal
 * @author Juan Camilo Ramos R.
 *
 */
public class DashBoardController implements Initializable
{

	private Aplicacion aplicacion;
	private GestionEstudiantesController gestionEstudiantesController;
	private GestionInstructoresController gestionInstructoresController;
	private GestionLugaresController gestionLugaresController;
	private GestionHorarioController gestionHorarioController;
	private GestionCreditosController gestionCreditosController;
	private InscripcionCreditosController inscripcionCreditosController;
	private HorariosEstudianteInstructorController horariosEstudianteInstructorController;
	private InicioController inicioController;

	@FXML
	private VBox vBoxMenus;

	@FXML
    private Button btnCreditos;

	@FXML
    private Button btnInscripcionCreditos;

    @FXML
    private Button btnAdminCreditos;

    @FXML
    private Button btnVerHorarios;

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
    private Button btnHorarios;

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
		abrirVentana(NombreVentana.GESTION_INSTRUCTOR);
    }

    @FXML
    void gestionCreditosAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.GESTION_CREDITOS);
    }

    @FXML
    void inscripcionCreditosAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.INSCRIPCION_CREDITOS);
    }

    @FXML
    void gestionLugaresAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.GESTION_LUGARES);
    }

    @FXML
    void gestionHorariosAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.GESTION_HORARIOS);
    }

    @FXML
    void verHorariosAction(ActionEvent event)
    {
    	abrirVentana(NombreVentana.VER_HORARIOS);
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
		habilitarDeshabilitarPermisos(tipoUsuario);
		insertarImagenTipoUsuario(tipoUsuario, nombreUsuario);
	}

	/**
	 * Método que permite inicializar los menus que puede ver
	 * el tipo de usuario que ingreso
	 * @param tipoUsuario
	 */
	private void habilitarDeshabilitarPermisos(TipoUsuario tipoUsuario)
	{
		switch (tipoUsuario)
		{
			case ADMINISTRADOR:
				vBoxMenus.getChildren().remove(btnInscripcionCreditos);
				abrirVentana(NombreVentana.INICIO);
				break;
			case ESTUDIANTE:
				vBoxMenus.getChildren().remove(btnInicio);
				vBoxMenus.getChildren().remove(btnEstudiantes);
				vBoxMenus.getChildren().remove(btnCreditos);
				vBoxMenus.getChildren().remove(btnInstructores);
				vBoxMenus.getChildren().remove(btnHorarios);
				vBoxMenus.getChildren().remove(btnLugares);
				abrirVentana(NombreVentana.INSCRIPCION_CREDITOS);
				break;
			case INSTRUCTOR:
				vBoxMenus.getChildren().remove(btnInicio);
				vBoxMenus.getChildren().remove(btnEstudiantes);
				vBoxMenus.getChildren().remove(btnCreditos);
				vBoxMenus.getChildren().remove(btnInstructores);
				vBoxMenus.getChildren().remove(btnHorarios);
				vBoxMenus.getChildren().remove(btnLugares);
				vBoxMenus.getChildren().remove(btnInscripcionCreditos);
				abrirVentana(NombreVentana.VER_HORARIOS);
				break;
			default:
				break;
		}
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
				loader.setLocation(getClass().getResource("../view/GestionInstructoresView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.gestionInstructoresController = loader.getController();
				this.gestionInstructoresController.setAplicacion(this.aplicacion);
				break;
			case GESTION_CREDITOS:
				loader.setLocation(getClass().getResource("../view/GestionCreditosView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.gestionCreditosController = loader.getController();
				this.gestionCreditosController.setAplicacion(this.aplicacion);
				break;
			case GESTION_LUGARES:
				loader.setLocation(getClass().getResource("../view/GestionLugaresView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.gestionLugaresController = loader.getController();
				this.gestionLugaresController.setAplicacion(this.aplicacion);;
				break;
			case GESTION_HORARIOS:
				loader.setLocation(getClass().getResource("../view/GestionHorarioView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.gestionHorarioController = loader.getController();
				this.gestionHorarioController.setAplicacion(this.aplicacion);;
				break;
			case INSCRIPCION_CREDITOS:
				loader.setLocation(getClass().getResource("../view/InscripcionCreditosView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.inscripcionCreditosController = loader.getController();
				this.inscripcionCreditosController.setAplicacion(this.aplicacion);
				break;
			case VER_HORARIOS:
				loader.setLocation(getClass().getResource("../view/HorariosEstudianteInstructorView.fxml"));
				vistaCargada = loader.load();
				anchorPanePrincipal.getChildren().setAll(vistaCargada);
				this.horariosEstudianteInstructorController = loader.getController();
				this.horariosEstudianteInstructorController.setAplicacion(this.aplicacion);
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
    	aplicacion.guardarDatosRespaldo();
    	aplicacion.mostrarVentanaLogin();
    }


}
