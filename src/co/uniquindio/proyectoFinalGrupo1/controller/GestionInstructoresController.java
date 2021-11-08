package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador que maneja la vista de gestión instructores
 * @author Juan Camilo Ramos R.
 *
 */
public class GestionInstructoresController implements Initializable
{

	private Aplicacion aplicacion;
	ObservableList<Instructor> lstInstructoresData = FXCollections.observableArrayList();
	FilteredList<Instructor> filterDataInstructores;

	Instructor instructorSeleccionado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDocumento;

	@FXML
    private ComboBox<String> comboBoxTipoDocumento;

    @FXML
    private TextField txtAsignatura;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Instructor> tableInstructores;

    @FXML
    private TableColumn<Instructor, String> columnNombre;

    @FXML
    private TableColumn<Instructor, String> columnUsuario;

    @FXML
    private TableColumn<Instructor, String> columnDocumento;

    @FXML
    private TableColumn<Instructor, String> columnTipoDocumento;

    @FXML
    private TableColumn<Instructor, String> columnAsignatura;

    @FXML
    void nuevoAction(ActionEvent event)
    {
    	limpiarFormulario();
    }

    @FXML
    void agregarAction(ActionEvent event)
    {
    	agregarInstructor();
    }

	@FXML
    void actualizarAction(ActionEvent event)
    {
		actualizarInstructor();
    }

    @FXML
    void eliminarAction(ActionEvent event)
    {
    	eliminarInstructor();
    }

    /**
     * Método que permite inicializar los controles de la vista
     * @param location
     * @param resources
     */
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	comboBoxTipoDocumento.getItems().clear();
		comboBoxTipoDocumento.getItems().addAll("CC");

		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
		this.columnDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
		this.columnAsignatura.setCellValueFactory(new PropertyValueFactory<>("asignatura"));
		this.columnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		tableInstructores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			instructorSeleccionado = newSelection;
			mostrarInstructor(instructorSeleccionado);
		});

    	filterDataInstructores = new FilteredList<>(lstInstructoresData, p -> true);

    	txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			limpiarFormulario();
			filterDataInstructores.setPredicate(instructor -> {
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (instructor.getNombre().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				else if (instructor.getDocumento().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				return false;
			});
		});
	}

    /**
     * Método que permite mostrar los datos del
     * instructor
     * @param instructorSeleccionado
     */
    private void mostrarInstructor(Instructor instructorSeleccionado)
    {
		if(instructorSeleccionado != null)
		{
			txtNombre.setText(instructorSeleccionado.getNombre());
			txtDocumento.setText(instructorSeleccionado.getDocumento());
			comboBoxTipoDocumento.setValue(instructorSeleccionado.getTipoDocumento());
			txtAsignatura.setText(instructorSeleccionado.getAsignatura());
			txtUsuario.setText(instructorSeleccionado.getUsuario());
			txtContrasena.setText(instructorSeleccionado.getContrasena());
		}
	}

    /**
     * Método que permite agregar un instructor a la lista
     */
    private void agregarInstructor()
    {
		Instructor instructor = null;
		if(camposValidos())
		{
			String nombre = txtNombre.getText();
    		String documento = txtDocumento.getText();
    		String tipoDocumento = comboBoxTipoDocumento.getValue();
    		String asignatura = txtAsignatura.getText();
    		String usuario = txtUsuario.getText();
    		String contrasena = txtContrasena.getText();

    		try
    		{
    			instructor = aplicacion.agregarInstructor(nombre, documento, tipoDocumento, asignatura, usuario, contrasena);

    			if(instructor != null)
        		{
        			lstInstructoresData.add(instructor);
        			limpiarFormulario();
            		mostrarMensaje("Almacenar registro", "Datos guardados", "El registro ha sido almacenado correctamente", AlertType.INFORMATION);
        		}
			}
    		catch (UsuarioExisteException e)
    		{
				mostrarMensaje("Agregar datos", "Datos no agregados", "El usuario de código " + documento + " de la clase Instructor ya existe",
						AlertType.INFORMATION);
				e.printStackTrace();
			}
		}
	}

    /**
     * Método que permite actualizar un instructor
     */
	private void actualizarInstructor()
	{
		try
		{
			if(instructorSeleccionado != null)
			{
				if(camposValidos())
				{
					boolean actualizado = false;

					String nombre = txtNombre.getText();
		    		String documento = txtDocumento.getText();
		    		String documentoActual = instructorSeleccionado.getDocumento();
		    		String tipoDocumento = comboBoxTipoDocumento.getValue();
		    		String asignatura = txtAsignatura.getText();
		    		String usuario = txtUsuario.getText();
		    		String contrasena = txtContrasena.getText();

		    		actualizado = aplicacion.actualizarInstructor(documentoActual, documento, nombre, tipoDocumento, asignatura, usuario, contrasena);

			    		if(actualizado)
			    		{
			    			tableInstructores.refresh();
			    			limpiarFormulario();
			    			mostrarMensaje("Actualizar registro", "Datos guardados",
									"El registro ha sido actualizado correctamente", AlertType.INFORMATION);
			    		}
				}
				else
				{
					mostrarMensaje("Actualizar registro", "Actualizar Instructor", "Debe seleccionar un instructor",
							AlertType.WARNING);
				}
			}
		} catch (NoActualizadoException e)
		{
			mostrarMensaje("Actualizar registro", "Actualizar Instructor", "No se pudo actualizar el instructor",
					AlertType.WARNING);
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite eliminar un instructor
	 */
    private void eliminarInstructor()
    {
		boolean eliminado = false;
		try
		{
			if(instructorSeleccionado != null)
			{
				boolean confirmado = mostrarMensajeConfirmacion("¿Esta seguro de eliminar el registro?");
				if(confirmado)
				{
					eliminado = aplicacion.eliminarInstructor(instructorSeleccionado.getDocumento());
					if(eliminado)
					{
						lstInstructoresData.remove(instructorSeleccionado);
						tableInstructores.getSelectionModel().clearSelection();
						instructorSeleccionado = null;
						limpiarFormulario();
						mostrarMensaje("Eliminar registro", "Eliminar instructor", "Registro eliminado correctamente",
								AlertType.INFORMATION);
					}
				}
			}
			else
			{
				mostrarMensaje("Eliminar registro", "", "Debe seleccionar un instructor", AlertType.WARNING);
			}

		} catch (NoEliminadoException e)
		{
			mostrarMensaje("Eliminar registro", "", "El instructor no existe", AlertType.WARNING);
			e.printStackTrace();
		}

	}

    /**
     * Método que permite validar si los campos
     * están o no llenos
     * @return sonValidos
     */
	private boolean camposValidos()
	{
		boolean sonValidos = false;
		String mensaje = "";

		if(txtNombre == null || txtNombre.getText().trim().equals(""))
			mensaje += "El campo nombre es inválido \n";

		if(comboBoxTipoDocumento.getValue() == null || comboBoxTipoDocumento.getValue().toString().trim().equals(""))
			mensaje += "El campo tipo documento es inválido \n";

		if(txtDocumento == null || txtDocumento.getText().trim().equals(""))
			mensaje += "El campo documento es inválido \n";

		if(txtAsignatura == null || txtAsignatura.getText().trim().equals(""))
			mensaje += "El campo asignatura es inválido \n";

		if(txtUsuario == null || txtUsuario.getText().trim().equals(""))
			mensaje += "El campo usuario es inválido \n";

		if(txtContrasena == null || txtContrasena.getText().trim().equals(""))
			mensaje += "El campo contraseña es inválido \n";

		if(mensaje.equals(""))
		{
			sonValidos = true;
		}
		else
		{
			mostrarMensaje("Error", "Campos inválidos", mensaje, AlertType.ERROR);
		}

		return sonValidos;
	}

	/**
	 * Método que permite limpiar los valores del formulario
	 */
    private void limpiarFormulario()
    {
    	tableInstructores.getSelectionModel().clearSelection();
		txtDocumento.clear();
		txtAsignatura.clear();
		txtNombre.clear();
		comboBoxTipoDocumento.getSelectionModel().clearSelection();
		txtUsuario.clear();
		txtContrasena.clear();
	}

	/**
     * Método que permite asignar la clase principal
     * al controlador
     * @param aplicacion
     */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;

		tableInstructores.getItems().clear();
		tableInstructores.setItems(obtenerListaInstructoresData());

		SortedList<Instructor> sortedDataInstructores = new SortedList<>(filterDataInstructores);
		sortedDataInstructores.comparatorProperty().bind(tableInstructores.comparatorProperty());

		tableInstructores.setItems(sortedDataInstructores);
	}

	/**
	 * Método que permite obtener la lista de instructores
	 * @return lstInstructoresData
	 */
	private ObservableList<Instructor> obtenerListaInstructoresData()
	{
		lstInstructoresData.addAll(aplicacion.obtenerListaInstructoresData());
		return lstInstructoresData;
	}

	/**
	 * Método que permite mostrar un mensaje en pantalla
	 *
	 * @param titulo
	 * @param encabezado
	 * @param mensaje
	 * @param alertType
	 */
	private void mostrarMensaje(String titulo, String encabezado, String mensaje, AlertType alertType)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(encabezado);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/**
	 * Método que permite mostrar nu mensaje de validación
	 *
	 * @param mensaje
	 * @return acepto
	 */
	private boolean mostrarMensajeConfirmacion(String mensaje)
	{
		boolean acepto = false;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			acepto = true;
		}

		return acepto;
	}

}
