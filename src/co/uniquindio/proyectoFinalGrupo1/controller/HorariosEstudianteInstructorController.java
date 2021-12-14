package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.Credito;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.model.Instructor;
import co.uniquindio.proyectoFinalGrupo1.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador de la vista de horarios
 * @author Juan Camilo Ramos R.
 *
 */
public class HorariosEstudianteInstructorController implements Initializable
{
	private Aplicacion aplicacion;
	private Credito creditoSeleccionado;
	private ObservableList<Credito> lstCreditosData = FXCollections.observableArrayList();
	private ObservableList<Estudiante> lstEstudiantesData = FXCollections.observableArrayList();
	private ObservableList<Instructor> lstInstructoresData = FXCollections.observableArrayList();
	private Usuario usuarioActual;

	@FXML
	private Label lblTexto;

	@FXML
	private Label lblTipo;

	@FXML
    private TableColumn<Credito, Instructor> columnInstructor;

    @FXML
    private TableColumn<Credito, String> columnCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblCantEstudiantes;

    @FXML
    private TextField txtCodigo;

    @FXML
    private ComboBox<Instructor> comboBoxInstructores;

    @FXML
    private TableView<Credito> tableCreditos;

    @FXML
    private TableColumn<Credito, String> columnNombreCredito;

    @FXML
    private ComboBox<Estudiante> comboBoxEstudiantes;

    @FXML
    private Button btnVerDetalle;

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    void verDetalleAction(ActionEvent event)
    {
    	verDetalleCredito();
    }

	@FXML
    void tipoAction(ActionEvent event)
    {
    	changeTipo();
    }

    @FXML
    void changeInstructorAction(ActionEvent event)
    {
    	changeInstructor();
    }

	@FXML
    void changeEstudianteAction(ActionEvent event)
    {
    	changeEstudiante();
    }

	/**
     * Método que permite inicializar la vista
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		comboBoxTipo.getItems().clear();
		comboBoxTipo.getItems().addAll("Estudiante", "Instructor");
		comboBoxTipo.getSelectionModel().select("Estudiante");

		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnNombreCredito.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));

		tableCreditos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			creditoSeleccionado = newSelection;
			lblCantEstudiantes.setText("Cantidad estudiantes: " + creditoSeleccionado.getLstEstudiantes().size());
		});

		txtNombre.setDisable(true);
		txtCodigo.setDisable(true);

	}


	/**
	 * Método que permite obtener el tipo seleccionado y cambiar el estado de los controles
	 */
    private void changeTipo()
    {
		String valor = comboBoxTipo.getValue();
		tableCreditos.getItems().clear();
		switch (valor.toLowerCase()) {
		case "estudiante":
			lblCantEstudiantes.setVisible(false);
			lblTexto.setText("Estudiantes");
			comboBoxInstructores.setVisible(false);
			comboBoxEstudiantes.setVisible(true);
			break;
		case "instructor":
			lblCantEstudiantes.setVisible(true);
			lblTexto.setText("Instructores");
			comboBoxInstructores.setVisible(true);
			comboBoxEstudiantes.setVisible(false);
			break;
		default:
			break;
		}
	}

	/**
	 * Método que permite agregar la clase aplicacion(principal)
	 * al controlador
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;
		comboBoxEstudiantes.getItems().clear();
		comboBoxEstudiantes.setItems(obtenerListaEstudiantesData());
		comboBoxEstudiantes.setVisible(true);
		comboBoxInstructores.getItems().clear();
		comboBoxInstructores.setItems(obtenerListaInstructoresData());

		lblTexto.setText("Estudiantes");

		usuarioActual = aplicacion.obtenerUsuarioActual();
		obtenerCreditos();
	}

	/**
	 * Método que permite obtener la lista de estudiantes
	 * @return lstEstudiantesData
	 */
	private ObservableList<Estudiante> obtenerListaEstudiantesData()
	{
		lstEstudiantesData.addAll(aplicacion.obtenerListaEstudiantesData());
		return lstEstudiantesData;
	}

	/**
	 * Método que permite obtener la lista de instructores
	 * @return lstIntructoresData
	 */
	private ObservableList<Instructor> obtenerListaInstructoresData()
	{
		lstInstructoresData.addAll(aplicacion.obtenerListaInstructoresData());
		return lstInstructoresData;
	}

	/**
	 * Método que permite obtener la lista de creditos cuando
	 * el usuario es diferente de administrador
	 */
	private void obtenerCreditos()
	{
		ArrayList<Credito> lstCreditos = new ArrayList<>();
		if(usuarioActual instanceof Estudiante)
		{
			Estudiante estudiante = (Estudiante)usuarioActual;
			lblCantEstudiantes.setVisible(false);
			lblTexto.setVisible(false);
			lblTipo.setVisible(false);
			comboBoxTipo.setVisible(false);
			comboBoxEstudiantes.setVisible(false);
			comboBoxInstructores.setVisible(false);
			txtNombre.setText(estudiante.getNombre());
			txtCodigo.setText(estudiante.getDocumento());
			lstCreditos = aplicacion.obtenerListaCreditosInscritosEstudiante(estudiante);
		}
		else if(usuarioActual instanceof Instructor)
		{
			Instructor instructor = (Instructor)usuarioActual;
			lblCantEstudiantes.setVisible(true);
			lblTexto.setVisible(false);
			lblTipo.setVisible(false);
			comboBoxTipo.setVisible(false);
			comboBoxEstudiantes.setVisible(false);
			comboBoxInstructores.setVisible(false);
			txtNombre.setText(instructor.getNombre());
			txtCodigo.setText(instructor.getDocumento());
			lstCreditos = aplicacion.obtenerListaCreditosInstructor(instructor);
		}
		else
		{
			comboBoxTipo.setVisible(true);
			lblCantEstudiantes.setVisible(true);
			changeTipo();

		}

		lstCreditosData.addAll(lstCreditos);
		tableCreditos.getItems().clear();
		tableCreditos.setItems(lstCreditosData);

	}


	/**
	 * Método que permite obtener los creditos dependiendo de la
	 * seleccion
	 */
    private void changeInstructor()
    {
		Instructor instructor = comboBoxInstructores.getValue();
		if(instructor != null)
		{
			txtNombre.setText(instructor.getNombre());
			txtCodigo.setText(instructor.getDocumento());
			lstCreditosData = FXCollections.observableArrayList();
			lstCreditosData.addAll(aplicacion.obtenerListaCreditosInstructor(instructor));
			tableCreditos.getItems().clear();
			tableCreditos.setItems(lstCreditosData);
		}
	}

    /**
	 * Método que permite obtener los creditos dependiendo de la
	 * seleccion
	 */
    private void changeEstudiante()
    {
		Estudiante estudiante = comboBoxEstudiantes.getValue();
		if(estudiante != null)
		{
			txtNombre.setText(estudiante.getNombre());
			txtCodigo.setText(estudiante.getDocumento());
			lstCreditosData = FXCollections.observableArrayList();
			lstCreditosData.addAll(aplicacion.obtenerListaCreditosInscritosEstudiante(estudiante));
			tableCreditos.getItems().clear();
			tableCreditos.setItems(lstCreditosData);
		}
	}

    /**
     * Método que permite ver el detalle del credito
     */
    private void verDetalleCredito()
    {
    	try
		{
			if(creditoSeleccionado != null)
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VerHorariosView.fxml"));
				Parent root = loader.load();

				VerHorariosController verHorariosController = loader.getController();
				verHorariosController.setAplicacion(this.aplicacion);

				verHorariosController.setLstHorarios(creditoSeleccionado.getHorarios());

				Scene scene = new Scene(root);
				Stage dialogStage = new Stage();
				dialogStage.setResizable(false);
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				dialogStage.setScene(scene);
				dialogStage.showAndWait();
			}
			else
			{
				mostrarMensaje("Ver horarios", "Horarios", "Debe seleccionar un horario", AlertType.WARNING);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
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
}
