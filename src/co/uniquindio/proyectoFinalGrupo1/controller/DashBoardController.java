package co.uniquindio.proyectoFinalGrupo1.controller;


import java.io.IOException;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
public class DashBoardController
{

	private Aplicacion aplicacion;

	@FXML
    private Button btnCreditos;

	@FXML
    private Label lblNombreUsuario;

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
    void gestionEstudiantesAction(ActionEvent event)
    {
    	abrirGestionEstudiantes();
    }

	@FXML
    void gestionInstructoresAction(ActionEvent event) {

    }

    @FXML
    void gestionCreditosAction(ActionEvent event) {

    }

    @FXML
    void gestionLugaresAction(ActionEvent event) {

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
	}

	private void insertarImagenTipoUsuario(TipoUsuario tipoUsuario, String nombreUsuario)
	{
		Image image = null;
		switch (tipoUsuario) {
		case ADMINISTRADOR:
			image = new Image(getClass().getResourceAsStream("../images/business.png"));
			break;
		case ESTUDIANTE:
			image = new Image(getClass().getResourceAsStream("images/graduates.png"));
			break;
		case INSTRUCTOR:
			image = new Image(getClass().getResourceAsStream("images/teacher.png"));
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

	private void abrirGestionEstudiantes()
    {
		try {
			anchorPanePrincipal = new AnchorPane((Node)FXMLLoader.load(getClass().getResource("../view/LoginView.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			anchorPanePrincipal.getChildren().setAll(();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
