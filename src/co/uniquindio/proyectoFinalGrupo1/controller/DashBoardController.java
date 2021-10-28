package co.uniquindio.proyectoFinalGrupo1.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
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
    private ImageView imgUser;

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    void inicioAction(ActionEvent event)
    {

    }

    @FXML
    void gestionEstudiantesAction(ActionEvent event)
    {
    	abrirGestionEstudiantes();
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


    /**
     * M�todo que permite inicializar los componentes de la vista
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

    /**
     * M�todo que permite inicializar la clase aplicaci�n
     * @param aplicacion
     * @param tipoUsuario
     */
	public void setAplicacion(Aplicacion aplicacion, TipoUsuario tipoUsuario, String nombreUsuario)
	{
		this.aplicacion = aplicacion;
		insertarImagenTipoUsuario(tipoUsuario, nombreUsuario);
	}

	/**
	 * M�todo que permite mostrar la imagen asociada al tipo
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
	 * M�todo que permite mostrar el componente de gesti�n de estudiantes
	 * @throws IOException
	 */
	private void abrirGestionEstudiantes()
    {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/GestionEstudiantesView.fxml"));
		AnchorPane vistaCargada = null;
		try
		{
			vistaCargada = loader.load();
			anchorPanePrincipal.getChildren().setAll(vistaCargada);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.gestionEstudiantesController = loader.getController();
		this.gestionEstudiantesController.setAplicacion(this.aplicacion);
	}
}
