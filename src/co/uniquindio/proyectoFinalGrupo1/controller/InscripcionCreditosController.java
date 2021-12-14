package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.exceptions.CupoMaximoException;
import co.uniquindio.proyectoFinalGrupo1.model.Credito;
import co.uniquindio.proyectoFinalGrupo1.model.Estudiante;
import co.uniquindio.proyectoFinalGrupo1.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Controlador de la vista de inscripciones
 * @author Juan Camilo Ramos R.
 *
 */
public class InscripcionCreditosController implements Initializable
{
	private Aplicacion aplicacion;
	private Estudiante estudiante;
	private ObservableList<Credito> lstCreditosData = FXCollections.observableArrayList();
	private ObservableList<Credito> lstCreditosInscritosData = FXCollections.observableArrayList();
	private ObservableList<Credito> lstCreditosSeleccionados = FXCollections.observableArrayList();

	@FXML
    private TextField txtNombre;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnAgregarCredito;

    @FXML
    private Button btnEliminarCredito;

    @FXML
    private ListView<Credito> listViewCreditosInscritos;

    @FXML
    private TextField txtDocumento;

    @FXML
    private ListView<Credito> listViewCreditos;

    @FXML
    void guardarAction(ActionEvent event)
    {
    	guardar();
    }

	@FXML
    void agregarCreditoAction(ActionEvent event)
    {
    	agregarCredito();
    }

	@FXML
    void eliminarCreditoAction(ActionEvent event)
    {
		eliminarCredito();
    }

	/**
     * Método que permite inicializar la vista
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		listViewCreditos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewCreditosInscritos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		txtNombre.setDisable(true);
		txtDocumento.setDisable(true);
	}

	/**
	 * Método que permite agregar los creditos a la lista
	 */
    private void agregarCredito()
    {
    	lstCreditosSeleccionados = listViewCreditos.getSelectionModel().getSelectedItems();
    	if(lstCreditosSeleccionados != null && lstCreditosSeleccionados.size() > 0)
    	{
    		for (Credito credito : lstCreditosSeleccionados)
    		{
				lstCreditosData.remove(credito);
				lstCreditosInscritosData.add(credito);
			}

    		lstCreditosSeleccionados = FXCollections.observableArrayList();
    	}
	}

    /**
     * Método que permite eliminar los creditos
     */
    private void eliminarCredito()
    {
    	lstCreditosSeleccionados = listViewCreditosInscritos.getSelectionModel().getSelectedItems();
    	if(lstCreditosSeleccionados != null && lstCreditosSeleccionados.size() > 0)
    	{
    		for (Credito credito : lstCreditosSeleccionados)
    		{
				lstCreditosInscritosData.remove(credito);
				lstCreditosData.add(credito);
			}

    		lstCreditosSeleccionados = FXCollections.observableArrayList();
    	}
	}

    /**
     * Método que permite guardar las asignaciones
     */
    private void guardar()
    {
    	try
    	{
    		if(lstCreditosData.size() > 0 || lstCreditosInscritosData.size() > 0)
        	{
        		if(aplicacion.actualizarInscripcionCreditos(lstCreditosData, lstCreditosInscritosData, estudiante))
        		{
        			mostrarMensaje("Inscripción", "Creditos", "Inscripción de creditos exitoso", AlertType.INFORMATION);
        		}
        		else
        		{
        			mostrarMensaje("Inscripción", "Creditos", "Error al intentar inscribir los creditos", AlertType.ERROR);
        		}
        	}
		}
    	catch (CupoMaximoException e)
    	{
    		Persistencia.guardaRegistroLogEstudiante(e.getMessage(), 2, "CupoMaximoException");
		}
	}

	/**
	 * Método que permite inicializar la clase principal(aplicacion)
	 * en el controlador
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;
		listViewCreditos.getItems().clear();
		listViewCreditos.setItems(obtenerListaCreditosData());

		asignarEstudianteCampos();
	}

	/**
	 * Método que permite asignar el nombre del estudiante
	 * en los campos
	 */
	private void asignarEstudianteCampos()
	{
		estudiante = (Estudiante)aplicacion.obtenerUsuarioActual();
		if(estudiante != null)
		{
			txtNombre.setText(estudiante.getNombre());
			txtDocumento.setText(estudiante.getDocumento());

			listViewCreditosInscritos.getItems().clear();
			listViewCreditosInscritos.setItems(obtenerListaCreditosInscritosEstudiante(estudiante));

			actualizarListaCreditos();
		}
	}

	private void actualizarListaCreditos()
	{
		for (Credito credito : lstCreditosInscritosData)
		{
			lstCreditosData.remove(credito);
		}
	}

	/**
	 * Método que permite obtener la lista de creditos que
	 * tiene un estudiante
	 * @param documento
	 * @return
	 */
	private ObservableList<Credito> obtenerListaCreditosInscritosEstudiante(Estudiante estudiante)
	{
		lstCreditosInscritosData.addAll(aplicacion.obtenerListaCreditosInscritosEstudiante(estudiante));
		return lstCreditosInscritosData;
	}

	/**
	 * Método que permite obtener la lista de creditos
	 * @return lstCreditosData
	 */
	private ObservableList<Credito> obtenerListaCreditosData()
	{
		lstCreditosData.addAll(aplicacion.obtenerListaCreditosData());
		return lstCreditosData;
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
