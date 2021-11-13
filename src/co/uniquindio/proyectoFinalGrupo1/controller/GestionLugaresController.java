package co.uniquindio.proyectoFinalGrupo1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.model.Lugar;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador que maneja la vista de gestión lugares
 * @author Orlando Narvaez Baracaldo.
 *
 */
public class GestionLugaresController implements Initializable
{
	private Aplicacion aplicacion;
	ObservableList<Lugar> lstLugaresData = FXCollections.observableArrayList();
	FilteredList<Lugar> filterDataLugares;
	
	Lugar lugarSeleccionado;
	
	@FXML
    private TextField txtNombre;
	
	@FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtBuscar;
    
    @FXML
    private Button btnNuevo;
    
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private TableView<Lugar> tableLugares;

    @FXML
    private TableColumn<Lugar, String> columnNombre;
    
    @FXML
    private TableColumn<Lugar, String> columnCodigo;

    @FXML
    void nuevoAction(ActionEvent event) 
    {
    	limpiarFormulario();
    }

    @FXML
    void agregarAction(ActionEvent event) 
    {
    	agregarLugar();
    }

    @FXML
    void actualizarAction(ActionEvent event) 
    {
    	actualizarLugar();
    }

    @FXML
    void eliminarAction(ActionEvent event) 
    {
    	eliminarLugar();
    }

    /**
     * Método que permite inicializar los controles de la vista
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		tableLugares.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			lugarSeleccionado = newSelection;
			mostrarLugar(lugarSeleccionado);
		});
		
		filterDataLugares = new FilteredList<>(lstLugaresData, p -> true);
		
		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			limpiarFormulario();
			filterDataLugares.setPredicate(lugares -> {
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (lugares.getNombre().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				else if (lugares.getCodigo().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				return false;
			});
		});		
	}
	
	/**
     * Método que permite mostrar los datos de los
     * lugares
     * @param lugarSeleccionado
     */
    private void mostrarLugar(Lugar lugarSeleccionado)
    {
		if(lugarSeleccionado != null)
		{
			txtNombre.setText(lugarSeleccionado.getNombre());
			txtCodigo.setText(lugarSeleccionado.getCodigo());
		}
	}
    
    /**
     * Método que permite agregar un lugar a la lista
     */
    private void agregarLugar()
    {
		Lugar lugar = null;
		if(camposValidos())
		{
			String nombre = txtNombre.getText();
    		String codigo = txtCodigo.getText();

    		try
    		{
    			try {
					lugar = aplicacion.agregarLugar(nombre, codigo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			if(lugar != null)
        		{
        			lstLugaresData.add(lugar);
        			limpiarFormulario();
            		mostrarMensaje("Almacenar registro", "Datos guardados", "El registro ha sido almacenado correctamente", AlertType.INFORMATION);
        		}
			}
    		catch (NoCreadoException e)
    		{
				mostrarMensaje("Agregar datos", "Datos no agregados", "El lugar con el código "+ codigo  + " de la clase lugar ya existe",
						AlertType.INFORMATION);
				e.printStackTrace();
				Persistencia.guardaRegistroLogLugar("Se genero un NoCreadoException en agregarLugar",2,"NoCreadoException");;
			}
		}
	}
    
    /**
     * Método que permite actualizar un lugar
     */
	private void actualizarLugar()
	{
		if(lugarSeleccionado != null)
		{
			if(camposValidos())
			{
				boolean actualizado = false;

				String nombre = txtNombre.getText();
				String codigoActual = lugarSeleccionado.getCodigo();
	    		String codigo = txtCodigo.getText();
				try 
				{
					actualizado = aplicacion.actualizarLugar(nombre, codigo, codigoActual);

		    		if(actualizado)
		    		{
		    			tableLugares.refresh();
		    			limpiarFormulario();
		    			mostrarMensaje("Actualizar registro", "Datos guardados",
								"El registro ha sido actualizado correctamente", AlertType.INFORMATION);
		    		}
				} catch (NoActualizadoException e)
				{
					mostrarMensaje("Actualizar registro", "Actualizar Lugar", "No se pudo actualizar el instructor",
							AlertType.WARNING);
					e.printStackTrace();
					Persistencia.guardaRegistroLogLugar("Nombre:"+nombre+" código "+codigo, 2, "NoActualizadoException");;
				}
			
			}
		}
		else
		{
			mostrarMensaje("Actualizar registro", "Actualizar Lugar", "Debe seleccionar un Lugar",
					AlertType.WARNING);
		}	
	}
	
	/**
	 * Método que permite eliminar un lugar
	 */
    private void eliminarLugar()
    {
		boolean eliminado = false;
		try
		{
			String nombre = txtNombre.getText();
    		String codigo = txtCodigo.getText();
			if(lugarSeleccionado != null)
			{
				boolean confirmado = mostrarMensajeConfirmacion("¿Esta seguro de eliminar el registro?");
				if(confirmado)
				{
					eliminado = aplicacion.eliminarLugar(codigo);
					if(eliminado)
					{
						lstLugaresData.remove(lugarSeleccionado);
						tableLugares.getSelectionModel().clearSelection();
						lugarSeleccionado = null;
						limpiarFormulario();
						mostrarMensaje("Eliminar registro", "Eliminar instructor", "Registro eliminado correctamente",
								AlertType.INFORMATION);
						Persistencia.guardaRegistroLogLugar("Nombre:"+nombre+" código "+codigo,3,"Se elimina un instructor");
					}
				}
			}
			else
			{
				mostrarMensaje("Eliminar registro", "", "Debe seleccionar un lugar", AlertType.WARNING);
			}

		} catch (NoEliminadoException e)
		{
			mostrarMensaje("Eliminar registro", "", "El instructor no existe", AlertType.WARNING);
			e.printStackTrace();
			Persistencia.guardaRegistroLogLugar("Se intento eliminar un instructor que no existe", 1, "NoEliminadoException");
			
		}
	}
    
    
    
    /**
	 * Método que permite limpiar los valores del formulario
	 */
    private void limpiarFormulario()
    {
    	tableLugares.getSelectionModel().clearSelection();
		txtNombre.clear();
		txtCodigo.clear();
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

		if(txtCodigo == null || txtCodigo.getText().trim().equals(""))
			mensaje += "El campo código es inválido \n";

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
     * Método que permite asignar la clase principal
     * al controlador
     * @param aplicacion
     */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;

		tableLugares.getItems().clear();
		tableLugares.setItems(obtenerListaLugaresData());

		SortedList<Lugar> sortedDataLugares = new SortedList<>(filterDataLugares);
		sortedDataLugares.comparatorProperty().bind(tableLugares.comparatorProperty());

		tableLugares.setItems(sortedDataLugares);
	}
	
	/**
	 * Método que permite obtener la lista de instructores
	 * @return lstInstructoresData
	 */
	private ObservableList<Lugar> obtenerListaLugaresData()
	{
		lstLugaresData.addAll(aplicacion.obtenerListaLugaresData());
		return lstLugaresData;
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
