package co.uniquindio.proyectoFinalGrupo1.controller;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista Login
 * @author Juan Camilo Ramos R
 *
 */
public class LoginController
{
	Aplicacion aplicacion;
	ObservableList<TipoUsuario> lstTipoUsuariosData = FXCollections.observableArrayList();

    @FXML
    private TextField txtUsuario;

	@FXML
    private PasswordField txtContrasena;

	@FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    private Button btnIngresar;

    @FXML
    void ingresarAction(ActionEvent event)
    {
    	ingresar();
    }

    /**
     * M�todo que permite ingresar a la aplicaci�n
     */
	private void ingresar()
	{
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		TipoUsuario tipoUsuario = comboBoxTipoUsuario.getValue();

		if(aplicacion.ingresar(usuario, contrasena, tipoUsuario))
		{
			aplicacion.mostrarVentanaPrincipal();
		}
		else
		{
			mostrarMensajeError();
		}
	}

	/**
	 * M�todo que permite mostrar el mensaje de error de inicio de sesi�n
	 */
	private void mostrarMensajeError()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Mensaje sistema");
		alert.setHeaderText("Error en inicio de sesi�n");
		alert.setContentText("Usuario, contrase�a y/o tipo de usuario invalidos");
		alert.showAndWait();
	}

	/**
	 * M�todo que pasa la clase principal para realizar la
	 * comunicaci�n
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;

		comboBoxTipoUsuario.getItems().clear();
		comboBoxTipoUsuario.setItems(obtenerListaTipoUsuarios());
	}

	/**
	 * M�todo que permite obtener la lista de tipos de usuario
	 * @return
	 */
	private ObservableList<TipoUsuario> obtenerListaTipoUsuarios()
	{
		lstTipoUsuariosData.addAll(aplicacion.obtenerListaTiposUsuarios());
		return lstTipoUsuariosData;
	}
}
