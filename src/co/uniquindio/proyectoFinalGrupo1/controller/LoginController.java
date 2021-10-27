package co.uniquindio.proyectoFinalGrupo1.controller;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.Administrador;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.TipoUsuario;
import co.uniquindio.proyectoFinalGrupo1.model.Usuario;
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
     * Método que permite ingresar a la aplicación
     */
	private void ingresar()
	{
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		TipoUsuario tipoUsuario = comboBoxTipoUsuario.getValue();
		Usuario usuarioObtenido = null;

		usuarioObtenido = aplicacion.ingresar(usuario, contrasena, tipoUsuario);


		if(usuario != null)
		{
			String nombreUsuario = "";
			if(usuarioObtenido instanceof Estudiante)
			{
				Estudiante estudiante = (Estudiante)usuarioObtenido;
				nombreUsuario = estudiante.getNombre();
			}
			else if(usuarioObtenido instanceof Instructor)
			{
				Instructor instructor = (Instructor)usuarioObtenido;
				nombreUsuario = instructor.getNombre();
			}
			else
			{
				Administrador admin = (Administrador)usuarioObtenido;
				nombreUsuario = admin.getNombre();
			}

			aplicacion.mostrarVentanaPrincipal(tipoUsuario, nombreUsuario);
		}
		else
		{
			mostrarMensajeError();
		}
	}

	/**
	 * Método que permite mostrar el mensaje de error de inicio de sesión
	 */
	private void mostrarMensajeError()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Mensaje sistema");
		alert.setHeaderText("Error en inicio de sesión");
		alert.setContentText("Usuario, contraseña y/o tipo de usuario invalidos");
		alert.showAndWait();
	}

	/**
	 * Método que pasa la clase principal para realizar la
	 * comunicación
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;

		comboBoxTipoUsuario.getItems().clear();
		comboBoxTipoUsuario.setItems(obtenerListaTipoUsuarios());
	}

	/**
	 * Método que permite obtener la lista de tipos de usuario
	 * @return
	 */
	private ObservableList<TipoUsuario> obtenerListaTipoUsuarios()
	{
		lstTipoUsuariosData.addAll(aplicacion.obtenerListaTiposUsuarios());
		return lstTipoUsuariosData;
	}
}
