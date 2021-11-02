package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista de gestión estudiantes
 * @author Juan Camilo Ramos R.
 *
 */
public class GestionEstudiantesController implements Initializable
{
	private Aplicacion aplicacion;
	ObservableList<Estudiante> lstEstudiantesData = FXCollections.observableArrayList();
	FilteredList<Estudiante> filterDataEstudiantes;

	Estudiante estudianteSeleccionado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDocumento;

	@FXML
    private ComboBox<String> comboBoxTipoDocumento;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Estudiante> tableEstudiantes;

    @FXML
    private TableColumn<Estudiante, String> columnNombre;

    @FXML
    private TableColumn<Estudiante, String> columnUsuario;

    @FXML
    private TableColumn<Estudiante, String> columnDocumento;

    @FXML
    private TableColumn<Estudiante, String> columnTipoDocumento;

    @FXML
    private TableColumn<Estudiante, Integer> columnEdad;

	@FXML
    void nuevoAction(ActionEvent event)
    {
    	limpiarFormulario();
    }

    @FXML
    void agregarAction(ActionEvent event)
    {
    	agregarEstudiante();
    }

    @FXML
    void actualizarAction(ActionEvent event)
    {
    	actualizarEstudiante();
    }

	@FXML
    void eliminarAction(ActionEvent event)
    {
		eliminarEstudiante();
    }

	/**
     * Método que permite inicializar los controles de la vista
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		comboBoxTipoDocumento.getItems().clear();
		comboBoxTipoDocumento.getItems().addAll("CC", "TI");

		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
		this.columnDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
		this.columnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		this.columnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		tableEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			estudianteSeleccionado = newSelection;
			mostrarEstudiante(estudianteSeleccionado);
		});

    	filterDataEstudiantes = new FilteredList<>(lstEstudiantesData, p -> true);

    	txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			limpiarFormulario();
			filterDataEstudiantes.setPredicate(estudiante -> {
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (estudiante.getNombre().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				else if (estudiante.getDocumento().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				return false;
			});
		});
	}

	/**
	 * Método que permite mostrar los datos de un estudiante
	 * @param estudianteSeleccionado
	 */
	private void mostrarEstudiante(Estudiante estudianteSeleccionado)
	{
		if(estudianteSeleccionado != null)
		{
			txtNombre.setText(estudianteSeleccionado.getNombre());
			txtDocumento.setText(estudianteSeleccionado.getDocumento());
			comboBoxTipoDocumento.setValue(estudianteSeleccionado.getTipoDocumento());
			txtEdad.setText(estudianteSeleccionado.getEdad() + "");
			txtContrasena.setText(estudianteSeleccionado.getContrasena());
			txtUsuario.setText(estudianteSeleccionado.getUsuario());
		}
	}

	/**
	 * Método que permite agregar un estudiante
	 */
    private void agregarEstudiante()
    {
    	Estudiante estudiante = null;
    	if(camposValidos())
    	{
    		String nombre = txtNombre.getText();
    		String documento = txtDocumento.getText();
    		String tipoDocumento = comboBoxTipoDocumento.getValue();
    		int edad = Integer.parseInt(txtEdad.getText());
    		String usuario = txtUsuario.getText();
    		String contrasena = txtContrasena.getText();

    		try {
    			estudiante = aplicacion.agregarEstudiante(nombre, documento, tipoDocumento, edad, usuario, contrasena);

    			if(estudiante != null)
        		{
        			lstEstudiantesData.add(estudiante);
        			limpiarFormulario();
            		mostrarMensaje("Almacenar registro", "Datos guardados", "El registro ha sido almacenado correctamente", AlertType.INFORMATION);
        		}
			} catch (UsuarioExisteException e)
    		{
				mostrarMensaje("Agregar datos", "Datos no agregados", e.getMessage(), AlertType.INFORMATION);
				e.printStackTrace();
			}

    	}
	}

    /**
     * Método que permite actualizar un estudiante
     */
	private void actualizarEstudiante()
	{
		if(estudianteSeleccionado != null)
		{
			if(camposValidos())
			{
				boolean actualizado = false;

				String nombre = txtNombre.getText();
	    		String documento = txtDocumento.getText();
	    		String documentoActual = estudianteSeleccionado.getDocumento();
	    		String tipoDocumento = comboBoxTipoDocumento.getValue();
	    		int edad = Integer.parseInt(txtEdad.getText());
	    		String usuario = txtUsuario.getText();
	    		String contrasena = txtContrasena.getText();

	    		actualizado = aplicacion.actualizarEstudiante(documentoActual, documento, nombre, tipoDocumento, edad, usuario, contrasena);

	    		if(actualizado)
	    		{
	    			tableEstudiantes.refresh();
	    			limpiarFormulario();
	    			mostrarMensaje("Actualizar registro", "Datos guardados",
							"El registro ha sido actualizado correctamente", AlertType.INFORMATION);
	    		}
	    		else
	    		{
	    			mostrarMensaje("Actualizar registro", "Datos NO guardados",
							"No se pudo actualizar el registro", AlertType.ERROR);
	    		}
			}
		}
		else
		{
			mostrarMensaje("Actualizar registro", "Actualizar Estudiante", "Debe seleccionar un estudiante",
					AlertType.WARNING);
		}
	}

	/**
	 * Método que permite eliminar un estudiante
	 */
    private void eliminarEstudiante()
    {
		boolean eliminado = false;
		if(estudianteSeleccionado != null)
		{
			boolean confirmado = mostrarMensajeConfirmacion("¿Esta seguro de eliminar el registro?");
			if(confirmado)
			{
				eliminado = aplicacion.eliminarEstudiante(estudianteSeleccionado.getDocumento());
				if(eliminado)
				{
					lstEstudiantesData.remove(estudianteSeleccionado);
					tableEstudiantes.getSelectionModel().clearSelection();
					estudianteSeleccionado = null;
					limpiarFormulario();
					mostrarMensaje("Eliminar registro", "Eliminar estudiante", "Registro eliminado correctamente",
							AlertType.INFORMATION);
				}
			}
		}
		else
		{
			mostrarMensaje("Eliminar registro", "", "Debe seleccionar un estudiante",
					AlertType.WARNING);
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

		if(txtEdad == null || txtEdad.getText().trim().equals(""))
			mensaje += "El campo edad es inválido \n";

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
    	tableEstudiantes.getSelectionModel().clearSelection();
		txtDocumento.clear();
		txtEdad.clear();
		txtNombre.clear();
		comboBoxTipoDocumento.getSelectionModel().clearSelection();
		txtUsuario.clear();
		txtContrasena.clear();
	}

    /**
     * Método que permite asignar la clase aplicacion(principal)
     * al controlador
     * @param aplicacion
     */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;

		tableEstudiantes.getItems().clear();
		tableEstudiantes.setItems(obtenerListaEstudiantesData());

		SortedList<Estudiante> sortedDataEstudiantes = new SortedList<>(filterDataEstudiantes);
		sortedDataEstudiantes.comparatorProperty().bind(tableEstudiantes.comparatorProperty());

		tableEstudiantes.setItems(sortedDataEstudiantes);
	}

	/**
	 * Método que permite obtener la lista de estudiantes predeterminados
	 * @return lstEstudiantes
	 */
	private ObservableList<Estudiante> obtenerListaEstudiantesData()
	{
		lstEstudiantesData.addAll(aplicacion.obtenerListaEstudiantesData());
		return lstEstudiantesData;
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
