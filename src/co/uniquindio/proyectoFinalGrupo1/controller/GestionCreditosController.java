package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.CreditoExisteException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoActualizadoException;
import co.uniquindio.proyectoFinalGrupo1.exceptions.NoEliminadoException;
import co.uniquindio.proyectoFinalGrupo1.model.Credito;
import co.uniquindio.proyectoFinalGrupo1.model.CreditoAcademico;
import co.uniquindio.proyectoFinalGrupo1.model.CreditoCultural;
import co.uniquindio.proyectoFinalGrupo1.model.CreditoDeportivo;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.Lugar;
import co.uniquindio.proyectoFinalGrupo1.model.TipoCredito;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador de la vista de gestión de creditos
 * @author Juan Camilo Ramos R.
 *
 */
public class GestionCreditosController implements Initializable
{
	private Aplicacion aplicacion;
	private ObservableList<Credito> lstCreditosData = FXCollections.observableArrayList();
	private ObservableList<Lugar> lstLugaresData = FXCollections.observableArrayList();
	private ObservableList<Instructor> lstInstructoresData = FXCollections.observableArrayList();
	private ArrayList<Horario> lstHorariosSeleccionados = new ArrayList<>();
	private RadioButton rbSeleccionado;
	private FilteredList<Credito> filterDataCredito;
	private Credito creditoSeleccionado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtCupoMaximo;

    @FXML
    private ComboBox<Lugar> comboBoxLugar;

    @FXML
    private ComboBox<Instructor> comboBoxInstructor;

    @FXML
    private RadioButton rbtnAcademico;

    @FXML
    private RadioButton rbtnCultural;

    @FXML
    private RadioButton rbtnDeportivo;

    @FXML
    private Label lblNombreCampo;

    @FXML
    private ComboBox<String> comboBoxHomologable;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtAsistenciaMinima;

    @FXML
    private Button btnSeleccionarHorarios;

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
    private Button btnVerHorarios;

    @FXML
    private TableView<Credito> tableCreditos;

	@FXML
    private TableColumn<Credito, String> columnCodigo;

    @FXML
    private TableColumn<Credito, String> columnNombre;

    @FXML
    private TableColumn<Credito, Lugar> columnLugar;

    @FXML
    private TableColumn<Credito, TipoCredito> columnTipo;

    @FXML
    private TableColumn<Credito, Integer> columnDuracion;

    @FXML
    private TableColumn<Credito, Instructor> columnInstructor;

    @FXML
    private TableColumn<Credito, Integer> columnCupoMaximo;

    @FXML
    void seleccionarHorariosAction(ActionEvent event)
    {
    	abrirDialogSeleccionarHorarios();
    }

	@FXML
    void nuevoAction(ActionEvent event)
    {
		limpiarFormulario();
    }

    @FXML
    void agregarAction(ActionEvent event)
    {
    	agregarCredito();
    }

	@FXML
    void actualizarAction(ActionEvent event)
    {
		actualizarCredito();
    }

	@FXML
    void eliminarAction(ActionEvent event)
    {
		eliminarCredito();
    }

	@FXML
    void verHorariosAction(ActionEvent event)
	{
		abrirDialogVerHorarios();
    }

	/**
     * Método que permite inicializar la vista
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
		this.columnCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
		this.columnLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
		this.columnInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
		this.columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCredito"));
		this.btnVerHorarios.setDisable(true);

		this.txtCosto.setVisible(false);
		this.txtAsistenciaMinima.setVisible(false);

		ToggleGroup group = new ToggleGroup();
		rbtnAcademico.setToggleGroup(group);
		rbtnCultural.setToggleGroup(group);
		rbtnDeportivo.setToggleGroup(group);

		rbtnAcademico.setSelected(true);
		rbSeleccionado = rbtnAcademico;


		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
            {
                rbSeleccionado = (RadioButton)group.getSelectedToggle();

                if (rbSeleccionado != null)
                {
                    changeRadioButton(rbSeleccionado.getText());
                }
            }
        });

		tableCreditos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			creditoSeleccionado = newSelection;
			mostrarCredito(creditoSeleccionado);
		});


		filterDataCredito = new FilteredList<>(lstCreditosData, p -> true);

		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			filterDataCredito.setPredicate(credito -> {
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (credito.getCodigo().toString().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}

				else if (credito.getNombre().toLowerCase().contains(lowerCaseFilter))
				{
					return true;
				}
				return false;

			});
		});
	}

	/**
	 * Método que permite mostrar los datos del credito
	 * @param creditoSeleccionado
	 */
	private void mostrarCredito(Credito creditoSeleccionado)
	{
		if(creditoSeleccionado != null)
		{
			txtCodigo.setText(creditoSeleccionado.getCodigo());
			txtNombre.setText(creditoSeleccionado.getNombre());
			txtDuracion.setText(creditoSeleccionado.getDuracion() + "");
			txtCupoMaximo.setText(creditoSeleccionado.getCupoMaximo() + "");
			comboBoxLugar.setValue(creditoSeleccionado.getLugar());
			comboBoxInstructor.setValue(creditoSeleccionado.getInstructor());
			btnVerHorarios.setDisable(false);
			btnSeleccionarHorarios.setDisable(true);

			if(creditoSeleccionado instanceof CreditoAcademico)
			{
				CreditoAcademico creditoAcademico = (CreditoAcademico)creditoSeleccionado;
				comboBoxHomologable.setValue(creditoAcademico.getHomologable() ? "SI" : "NO");
				rbtnAcademico.setSelected(true);
			}
			else if(creditoSeleccionado instanceof CreditoCultural)
			{
				CreditoCultural creditoCultural = (CreditoCultural)creditoSeleccionado;
				txtCosto.setText(creditoCultural.getCosto() + "");
				rbtnCultural.setSelected(true);
			}
			else
			{
				CreditoDeportivo creditoDeportivo = (CreditoDeportivo)creditoSeleccionado;
				txtAsistenciaMinima.setText(creditoDeportivo.getAsistenciaMinima() + "");
				rbtnDeportivo.setSelected(true);
			}

		}
	}

	/**
	 * Método que permite agregar un credito
	 */
    private void agregarCredito()
    {
    	Credito credito = null;
		if(camposValidos())
		{
			if(lstHorariosSeleccionados != null && lstHorariosSeleccionados.size() > 0)
			{
				String codigo = txtCodigo.getText();
				String nombre = txtNombre.getText();
				int duracion = Integer.parseInt(txtDuracion.getText());
				int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText());
				Lugar lugar = comboBoxLugar.getValue();
				Instructor instructor = comboBoxInstructor.getValue();
				TipoCredito tipo = encontrarTipoCredito();
				boolean homologable = false;
				double costo = 0;
				int asistenciaMinima = 0;
				switch (tipo) {
				case ACADEMICO:
					homologable = comboBoxHomologable.getValue().toLowerCase().equals("si") ? true : false;
					break;
				case CULTURAL:
					costo = Double.parseDouble(txtCosto.getText());
					break;
				case DEPORTIVO:
					asistenciaMinima = Integer.parseInt(txtAsistenciaMinima.getText());
					break;
				default:
					break;
				}

				try
				{
					credito = aplicacion.agregarCredito(codigo, nombre, duracion, cupoMaximo, lugar, instructor,
							tipo, homologable, costo, asistenciaMinima, lstHorariosSeleccionados);

					if(credito != null)
					{
						lstCreditosData.add(credito);
						limpiarFormulario();
						Persistencia.guardaRegistroLogCredito("Nombre: " + nombre + " Codigo: " + codigo, 1, "Se agrega un credito");
						mostrarMensaje("Exito", "Guardar registro", "Registro agregado correctamente", AlertType.INFORMATION);
					}

				}
				catch(CreditoExisteException e)
				{
					mostrarMensaje("Agregar datos", "Datos no agregados", "El credito de código " + codigo + " ya existe",
							AlertType.INFORMATION);
					e.printStackTrace();
	    			Persistencia.guardaRegistroLogEstudiante("Nombre:"+nombre+" código " + codigo,2,"CreditoExisteException");
				}
			}
			else
			{
				mostrarMensaje("Error", "Seleccionar Horarios", "Debe seleccionar al menos 1 horarios", AlertType.ERROR);
			}
		}
	}

    /**
     * Método que permite actualizar credito
     */
    private void actualizarCredito()
    {
		if(creditoSeleccionado != null)
		{
			if(camposValidos())
			{
				boolean actualizado = false;

				String codigo = txtCodigo.getText();
				String nombre = txtNombre.getText();
				int duracion = Integer.parseInt(txtDuracion.getText());
				int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText());
				Lugar lugar = comboBoxLugar.getValue();
				Instructor instructor = comboBoxInstructor.getValue();
				TipoCredito tipo = encontrarTipoCredito();
				boolean homologable = false;
				double costo = 0;
				int asistenciaMinima = 0;
				switch (tipo) {
				case ACADEMICO:
					homologable = comboBoxHomologable.getValue().toLowerCase().equals("si") ? true : false;
					break;
				case CULTURAL:
					costo = Double.parseDouble(txtCosto.getText());
					break;
				case DEPORTIVO:
					asistenciaMinima = Integer.parseInt(txtAsistenciaMinima.getText());
					break;
				default:
					break;
				}

				try
				{
					actualizado = aplicacion.actualizarCredito(creditoSeleccionado.getCodigo(),codigo, nombre, duracion, cupoMaximo, lugar, instructor,
																tipo, homologable, costo, asistenciaMinima);

					if(actualizado)
					{
						tableCreditos.getItems().clear();
						tableCreditos.setItems(obtenerListaCreditosData());
						limpiarFormulario();
						Persistencia.guardaRegistroLogCredito("Nombre: " + nombre + "Código: " + codigo, 1, "Actualiza credito");
						mostrarMensaje("Exito", "Actualizar registro", "Registro actualizado correctamente", AlertType.INFORMATION);
					}

				}
				catch (NoActualizadoException e)
				{
					mostrarMensaje("Error", "Actualizar registrp", "No fue posible actualizar el registro", AlertType.ERROR);
					Persistencia.guardaRegistroLogCredito("Nombre:"+nombre+" código: "+codigo, 2, "NoActualizadoException");
				}
			}
		}
	}

    /**
     * Método que permite eliminar un credito
     */
    private void eliminarCredito()
    {
    	String nombre = txtNombre.getText();
    	String codigo = txtCodigo.getText();
		try
		{
			if(creditoSeleccionado != null)
			{
				boolean eliminado = false;

				if(mostrarMensajeConfirmacion("¿Esta seguro de eliminar el registro?"))
				{
					eliminado = aplicacion.eliminarCredito(creditoSeleccionado.getCodigo());

					if(eliminado)
					{
						lstCreditosData.remove(creditoSeleccionado);
						tableCreditos.getSelectionModel().clearSelection();
						creditoSeleccionado = null;
						limpiarFormulario();
						mostrarMensaje("Eliminar registro", "Eliminar crédito", "Registro eliminado correctamente",
								AlertType.INFORMATION);
						Persistencia.guardaRegistroLogCredito("Nombre:"+nombre+" Código "+codigo,3,"Se elimina un crédito");
					}
				}
			}
		}
		catch (NoEliminadoException e)
		{
			mostrarMensaje("Eliminar registro", "", "El crédito no existe", AlertType.WARNING);
			Persistencia.guardaRegistroLogCredito("Nombre: " + nombre + " Código: " + codigo, 2, "NoEliminadoException");
			e.printStackTrace();
		}
	}

    /**
     * Método que permite encontrar el tipo de credito
     * @return tipoCredito
     */
    private TipoCredito encontrarTipoCredito()
    {
		TipoCredito tipoCredito = TipoCredito.ACADEMICO;
		if(rbSeleccionado == rbtnAcademico)
		{
			tipoCredito = TipoCredito.ACADEMICO;
		}
		else if(rbSeleccionado ==  rbtnCultural)
		{
			tipoCredito = TipoCredito.CULTURAL;
		}
		else
		{
			tipoCredito = TipoCredito.DEPORTIVO;
		}

		return tipoCredito;
	}

	/**
     * Método que permite validar los campos
     * @return validos
     */
	private boolean camposValidos()
	{
		boolean validos = false;
		String mensaje = "";

		if(txtCodigo == null || txtCodigo.getText().trim().equals(""))
		{
			mensaje += "El campo código es inválido \n";
		}

		if(txtNombre == null || txtNombre.getText().trim().equals(""))
		{
			mensaje += "El campo nombre es inválido \n";
		}

		if(txtDuracion == null || txtDuracion.getText().trim().equals(""))
		{
			mensaje += "El campo duración es inválido \n";
		}

		if(txtCupoMaximo == null || txtCupoMaximo.getText().trim().equals(""))
		{
			mensaje += "El campo cupo máximo es inválido \n";
		}

		if(comboBoxLugar.getValue() == null)
		{
			mensaje += "El campo lugar es inválido \n";
		}

		if(comboBoxInstructor.getValue() == null)
		{
			mensaje += "El campo instructor es inválido \n";
		}

		if(rbSeleccionado == rbtnAcademico)
		{
			if(comboBoxHomologable.getValue() == null || comboBoxHomologable.getValue().toString().trim().equals(""))
			{
				mensaje += "El campo homologable es inválido \n";
			}
		}
		else if(rbSeleccionado == rbtnCultural)
		{
			if(txtCosto == null || txtCosto.getText().trim().equals(""))
			{
				mensaje += "El campo costo es inválido \n";
			}
		}
		else
		{
			if(txtAsistenciaMinima == null || txtAsistenciaMinima.getText().trim().equals(""))
			{
				mensaje += "El campo asistencia mínima es inválido \n";
			}
		}

		if(mensaje.equals(""))
		{
			validos = true;
		}
		else
		{
			mostrarMensaje("Advertencia", "Campos inválidos", mensaje, AlertType.WARNING);
		}

		return validos;
	}

	/**
	 * Método que permite abrir el modal de
	 * selección de horarios
	 */
    private void abrirDialogSeleccionarHorarios()
    {
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SeleccionarHorariosView.fxml"));
			Parent root = loader.load();

			SeleccionarHorariosController seleccionarHController = loader.getController();
			seleccionarHController.setAplicacion(this.aplicacion);

			Scene scene = new Scene(root);
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();

			lstHorariosSeleccionados = seleccionarHController.getLstHorariosSeleccionados();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

    /**
     * Método que permite abrir el modal
     * de ver horarios
     */
	private void abrirDialogVerHorarios()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VerHorariosView.fxml"));
			Parent root = loader.load();

			VerHorariosController verHorariosController = loader.getController();
			verHorariosController.setAplicacion(this.aplicacion);

			if(creditoSeleccionado != null)
			{
				verHorariosController.setLstHorarios(creditoSeleccionado.getHorarios());
			}

			Scene scene = new Scene(root);
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

    private void limpiarFormulario()
    {
    	txtCodigo.clear();
    	txtNombre.clear();
    	comboBoxLugar.getSelectionModel().clearSelection();
    	comboBoxInstructor.getSelectionModel().clearSelection();
    	txtDuracion.clear();
    	txtCupoMaximo.clear();
    	rbtnAcademico.setSelected(true);
    	comboBoxHomologable.getSelectionModel().clearSelection();
    	txtCosto.clear();
    	txtAsistenciaMinima.clear();
    	lstHorariosSeleccionados = new ArrayList<>();
    	btnVerHorarios.setDisable(true);
    	btnSeleccionarHorarios.setDisable(false);
    }

    /**
     * Método que permite pasar la clase principal (aplicacion)
     * al controlador
     * @param aplicacion
     */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;
		comboBoxLugar.getItems().clear();
		comboBoxLugar.setItems(obtenerListaLugaresData());

		comboBoxInstructor.getItems().clear();
		comboBoxInstructor.setItems(obtenerListaInstructoresData());

		comboBoxHomologable.getItems().clear();
		comboBoxHomologable.getItems().add("SI");
		comboBoxHomologable.getItems().add("NO");

		tableCreditos.getItems().clear();
		tableCreditos.setItems(obtenerListaCreditosData());
	}

	/**
	 * Método que permite obtener la lista de lugares
	 * @return lstLugaresData
	 */
	private ObservableList<Lugar> obtenerListaLugaresData()
	{
		lstLugaresData.addAll(aplicacion.obtenerListaLugaresData());
		return lstLugaresData;
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
	 * Método que permite obtener la lista de créditos
	 * @return lstCreditosData
	 */
	private ObservableList<Credito> obtenerListaCreditosData()
	{
		lstCreditosData.addAll(aplicacion.obtenerListaCreditosData());
		return lstCreditosData;
	}

	/**
	 * Método que permite cambiar el formulario dependiendo
	 * del radio button selccionado
	 * @param text
	 */
	private void changeRadioButton(String text)
	{
		switch (text.toLowerCase()) {
		case "academico":
			lblNombreCampo.setText("Homologable");
			comboBoxHomologable.setVisible(true);
			txtCosto.setVisible(false);
			txtAsistenciaMinima.setVisible(false);
			break;
		case "cultural":
			lblNombreCampo.setText("Costo");
			txtCosto.setVisible(true);
			comboBoxHomologable.setVisible(false);
			txtAsistenciaMinima.setVisible(false);
			break;
		case "deportivo":
			lblNombreCampo.setText("Asistencia mínima");
			txtAsistenciaMinima.setVisible(true);
			txtCosto.setVisible(false);
			comboBoxHomologable.setVisible(false);
			break;
		default:
			break;
		}
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
