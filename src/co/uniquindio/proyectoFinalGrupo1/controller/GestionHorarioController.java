package co.uniquindio.proyectoFinalGrupo1.controller;

import  java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoCreadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class GestionHorarioController implements Initializable 
{

	private Aplicacion aplicacion;
	ObservableList<Horario> lstHorarioData = FXCollections.observableArrayList();
	FilteredList<Horario> filterDataHorario;
	
	Horario horarioSeleccionado;
	
	 @FXML
	    private TableColumn<Horario, String> columnFin;

	    @FXML
	    private TextField txtHoraFin;

	    @FXML
	    private Button btnNuevo;

	    @FXML
	    private TextField txtBuscar;

	    @FXML
	    private Button btnAgregar;

	    @FXML
	    private TextField txtDia;

	    @FXML
	    private ComboBox<String> comboBoxDia;

	    @FXML
	    private Button btnEliminar;

	    @FXML
	    private TableColumn<Horario, String> columnCodigoH;

	    @FXML
	    private TableColumn<Horario, String> columnIni;
	    
	    @FXML
	    private TableColumn<Horario, String> columnDia;

	    @FXML
	    private TableView<Horario> tableHorarios;

	    @FXML
	    private TextField txtCodigoH;

	    @FXML
	    private TextField txtHoraIni;

	    @FXML
	    private Button btnActualizar;

	    @FXML
	    void nuevoAction(ActionEvent event) {

	    }

	    @FXML
	    void agregarAction(ActionEvent event) {

	    }

	    @FXML
	    void actualizarAction(ActionEvent event) {

	    }

	    @FXML
	    void eliminarAction(ActionEvent event) {

	    }
	    
	   
	    /**
	     * Método que permite inicializar los controles de la vista
	     * @param location
	     * @param resources
	     */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) 
		{
			comboBoxDia.getItems().clear();
			comboBoxDia.getItems().addAll("LUNES", "MARTES","MIERCOLES","JUEVES","VIERNES");
		
			this.columnCodigoH.setCellValueFactory(new PropertyValueFactory<>("code"));
			this.columnIni.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
			this.columnFin.setCellValueFactory(new PropertyValueFactory<>("horaFinal"));
			this.columnDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
			
			tableHorarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
				horarioSeleccionado = (Horario) newSelection;
				mostrarHorario(horarioSeleccionado);
			});
			
			
			filterDataHorario= new FilteredList<>(lstHorarioData, p -> true);
			
			txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
				limpiarFormulario();
				filterDataHorario.setPredicate(horario -> {
					if (newValue == null || newValue.isEmpty())
					{
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();

					if (horario.getCode().toString().toLowerCase().contains(lowerCaseFilter))
					{
						return true;
					}
	
					else if (horario.getHoraInicio().toLowerCase().contains(lowerCaseFilter))
					{
						return true;
					}
					else if (horario.getHoraFinal().toLowerCase().contains(lowerCaseFilter))
					{
						return true;
					}
					else if (horario.getDia().toString().toLowerCase().contains(lowerCaseFilter))
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
	     * @param HorarioSeleccionado
	     */
		
		private void mostrarHorario(Horario horarioSeleccionado) 
		{
			if(horarioSeleccionado != null)
			{
				txtCodigoH.setText(horarioSeleccionado.getCode());
				txtHoraIni.setText(horarioSeleccionado.getHoraInicio());
				txtHoraFin.setText(horarioSeleccionado.getHoraFinal());
			  comboBoxDia.setValue (horarioSeleccionado.getDias());
			}
			
		}
		
		/**
	     * Método que permite agregar un Horario a la lista
	     */
		
	    private void agregarHorario()
	    {
			Horario horario = null;
			if(camposValidos())
			{
	    		String code = txtCodigoH.getText();
	    		String horaInicio = txtHoraIni.getText();
	    		String horaFinal = txtHoraFin.getText();
	    		String  dia = txtDia.getText();

	    		try
	    		{
	    			try {
	    				horario = aplicacion.agregarHorario(code,horaInicio,horaFinal,dia);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	    			if(horario != null)
	        		{
	        			lstHorarioData.add(horario);
	        			limpiarFormulario();
	            		mostrarMensaje("Almacenar registro", "Datos guardados", "El registro ha sido almacenado correctamente", AlertType.INFORMATION);
	        		}
				}
	    		catch (NoCreadoException e)
	    		{
					mostrarMensaje("Agregar datos", "Datos no agregados", "El horario con el código "+ code  + " de la clase horario ya existe",
							AlertType.INFORMATION);
					e.printStackTrace();
					Persistencia.guardaRegistroLogLugar("Se genero un NoCreadoException en agregarHorario",2,"NoCreadoException");;
				}
			}
		}
	    
	    /**
	     * Método que permite actualizar un horario
	     */
		private void actualizarHorario()
		{
			if(horarioSeleccionado != null)
			{
				if(camposValidos())
				{
					boolean actualizado = false;

					
					String code = txtCodigoH.getText();
					String codigoActual = horarioSeleccionado.getCode();
		    		String horaInicio = txtHoraIni.getText();
		    		String horaFinal = txtHoraFin.getText();
		    		Dias dia = txtDia.getText();
					try 
					{
						actualizado = aplicacion.actualizarHorario( code, codigoActual,horaInicio,horaFinal,dia);

			    		if(actualizado)
			    		{
			    			tableHorarios.refresh();
			    			limpiarFormulario();
			    			mostrarMensaje("Actualizar registro", "Datos guardados",
									"El registro ha sido actualizado correctamente", AlertType.INFORMATION);
			    		}
					} catch (NoActualizadoException e)
					{
						mostrarMensaje("Actualizar registro", "Actualizar horario", "No se pudo actualizar el horario",
								AlertType.WARNING);
						e.printStackTrace();
						Persistencia.guardaRegistroLogLugar( "código "+"Hora inicio" + horaInicio + "Hora final" + horaFinal + "Dia" + dia +  code, 2, "NoActualizadoException");;
						
					}
				
				}
			}
			else
			{
				mostrarMensaje("Actualizar registro", "Actualizar horario", "Debe seleccionar un horario",
						AlertType.WARNING);
			}
		}
			
			/**
			 * Método que permite eliminar un horario
			 */
		    private void eliminarHorario()
		    {
				boolean eliminado = false;
				try
				{
					String code = txtCodigoH.getText();
		    		String horaInicio = txtHoraIni.getText();
		    		String horaFinal = txtHoraFin.getText();
		    		Dias dia = txtDia.getText();
		    		
					if(horarioSeleccionado != null)
					{
						boolean confirmado = mostrarMensajeConfirmacion("¿Esta seguro de eliminar el registro?");
						if(confirmado)
						{
							eliminado = aplicacion.eliminarHorario(code);
							if(eliminado)
							{
								lstHorarioData.remove(horarioSeleccionado);
								tableHorarios.getSelectionModel().clearSelection();
								horarioSeleccionado = null;
								limpiarFormulario();
								mostrarMensaje("Eliminar registro", "Eliminar horario", "Registro eliminado correctamente",
										AlertType.INFORMATION);
								Persistencia.guardaRegistroLogLugar(" código "+ code ,3, "Se elimina un horario");
							}
						}
					}
					else
					{
						mostrarMensaje("Eliminar registro", "", "Debe seleccionar un horario", AlertType.WARNING);
					}

				} catch (NoEliminadoException e)
				{
					mostrarMensaje("Eliminar registro", "", "El horario no existe", AlertType.WARNING);
					e.printStackTrace();
					Persistencia.guardaRegistroLogLugar("Se intento eliminar un horario que no existe", 1, "NoEliminadoException");
					
				}
			}
		
		/**
		 * Método que permite limpiar los valores del formulario
		 */
	    private void limpiarFormulario()
	    {
	    	tableHorarios.getSelectionModel().clearSelection();
			txtCodigoH.clear();
			txtHoraIni.clear();
			txtHoraFin.clear();
			txtDia.clear();
			
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


			if(txtCodigoH == null || txtCodigoH.getText().trim().equals(""))
				mensaje += "El campo código es inválido \n";
			if(txtHoraIni == null || txtHoraIni.getText().trim().equals(""))
				mensaje += "El campo código es inválido \n";
			if(txtHoraFin == null || txtHoraFin.getText().trim().equals(""))
				mensaje += "El campo código es inválido \n";
			if(txtDia == null || txtDia.getText().trim().equals(""))
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

				tableHorarios.getItems().clear();
				tableHorarios.setItems(obtenerListaHorarioData());

				SortedList<Horario> sortedDataHorario = new SortedList<>(filterDataHorario);
				sortedDataHorario.comparatorProperty().bind(tableHorarios.comparatorProperty());

				tableHorarios.setItems(sortedDataHorario);
			}
			
			/**
			 * Método que permite obtener la lista de horarios
			 * @return lstInstructoresHorario
			 */
			private ObservableList<Horario> obtenerListaHorarioData()
			{
				lstHorarioData.addAll(aplicacion.obtenerListaHorarioData());
				return lstHorarioData;
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
