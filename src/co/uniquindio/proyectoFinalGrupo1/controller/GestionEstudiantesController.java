package co.uniquindio.proyectoFinalGrupo1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.UsuarioExisteException;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;
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
 * Controlador de la vista de gesti�n estudiantes
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
     * M�todo que permite inicializar los controles de la vista
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
	 * M�todo que permite mostrar los datos de un estudiante
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
	 * M�todo que permite agregar un estudiante
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

    		try
    		{
    			estudiante = aplicacion.agregarEstudiante(nombre, documento, tipoDocumento, edad, usuario, contrasena);

    			if(estudiante != null)
        		{
        			lstEstudiantesData.add(estudiante);			
        			limpiarFormulario();
            		mostrarMensaje("Almacenar registro", "Datos guardados", "El registro ha sido almacenado correctamente", AlertType.INFORMATION);

        		}
			}
    		catch (UsuarioExisteException e)
    		{
				mostrarMensaje("Agregar datos", "Datos no agregados", "El usuario de c�digo " + documento + " de la clase Estudiante ya existe",
						AlertType.INFORMATION);
				e.printStackTrace();
    			Persistencia.guardaRegistroLogEstudiante("Nombre:"+nombre+" identificaci�n "+documento,2,"UsuarioExisteException");

			} catch (IOException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
				Persistencia.guardaRegistroLogEstudiante("Se genero un IOExeption en agregarEstudiante",2,"IOException");
			}
    	}
	}

    /**
     * M�todo que permite actualizar un estudiante
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
				try 
				{

				

		    		actualizado = aplicacion.actualizarEstudiante(documentoActual, documento, nombre, tipoDocumento, edad, usuario, contrasena);

			    		if(actualizado)
			    		{
			    			tableEstudiantes.refresh();
			    			limpiarFormulario();
			    			mostrarMensaje("Actualizar registro", "Datos guardados",
									"El registro ha sido actualizado correctamente", AlertType.INFORMATION);

			    			Persistencia.guardaRegistroLogEstudiante("Nombre:"+nombre+" identificaci�n "+documento,1,"Se actualiza un estudiante");
			    		}		    			

				}
				
				 catch (NoActualizadoException e)

				{
					mostrarMensaje("Actualizar registro", "Actualizar Estudiante", "No se pudo actualizar el estudiante",
							AlertType.WARNING);
					e.printStackTrace();
					Persistencia.guardaRegistroLogEstudiante("Nombre:"+nombre+" identificaci�n "+documento, 2, "NoActualizadoException");
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
	 * M�todo que permite eliminar un estudiante
	 */
    private void eliminarEstudiante()
    {
		boolean eliminado = false;
		try
		{
			String nombre = txtNombre.getText();
			String documento = txtDocumento.getText();
			if(estudianteSeleccionado != null)
			{
				boolean confirmado = mostrarMensajeConfirmacion("�Esta seguro de eliminar el registro?");
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
						Persistencia.guardaRegistroLogEstudiante("Nombre:"+nombre+" identificaci�n "+documento,3,"Se elimina un estudiante");
					}
				}
			}
			else
			{
				mostrarMensaje("Eliminar registro", "", "Debe seleccionar un estudiante", AlertType.WARNING);
			}

		} catch (NoEliminadoException e)
		{
			mostrarMensaje("Eliminar registro", "", "El estudiante no existe", AlertType.WARNING);
			e.printStackTrace();
		}

	}

    /**
     * M�todo que permite validar si los campos
     * est�n o no llenos
     * @return sonValidos
     */
	private boolean camposValidos()
	{
		boolean sonValidos = false;
		String mensaje = "";

		if(txtNombre == null || txtNombre.getText().trim().equals(""))
			mensaje += "El campo nombre es inv�lido \n";

		if(comboBoxTipoDocumento.getValue() == null || comboBoxTipoDocumento.getValue().toString().trim().equals(""))
			mensaje += "El campo tipo documento es inv�lido \n";

		if(txtDocumento == null || txtDocumento.getText().trim().equals(""))
			mensaje += "El campo documento es inv�lido \n";

		if(txtEdad == null || txtEdad.getText().trim().equals(""))
			mensaje += "El campo edad es inv�lido \n";

		if(txtUsuario == null || txtUsuario.getText().trim().equals(""))
			mensaje += "El campo usuario es inv�lido \n";

		if(txtContrasena == null || txtContrasena.getText().trim().equals(""))
			mensaje += "El campo contrase�a es inv�lido \n";

		if(mensaje.equals(""))
		{
			sonValidos = true;
		}
		else
		{
			mostrarMensaje("Error", "Campos inv�lidos", mensaje, AlertType.ERROR);
		}

		return sonValidos;
	}

	/**
	 * M�todo que permite limpiar los valores del formulario
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
     * M�todo que permite asignar la clase aplicacion(principal)
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
	 * M�todo que permite obtener la lista de estudiantes predeterminados
	 * @return lstEstudiantes
	 */
	private ObservableList<Estudiante> obtenerListaEstudiantesData()
	{
		lstEstudiantesData.addAll(aplicacion.obtenerListaEstudiantesData());
		return lstEstudiantesData;
	}


    /**
	 * M�todo que permite mostrar un mensaje en pantalla
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
	 * M�todo que permite mostrar nu mensaje de validaci�n
	 *
	 * @param mensaje
	 * @return acepto
	 */
	private boolean mostrarMensajeConfirmacion(String mensaje)
	{
		boolean acepto = false;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmaci�n");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			acepto = true;
		}

		return acepto;
	}
}
