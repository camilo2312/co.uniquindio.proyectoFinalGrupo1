package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controlador de la vista de seleccionar horarios
 * @author Juan Camilo Ramos R.
 *
 */
public class SeleccionarHorariosController implements Initializable
{
	private Aplicacion aplicacion;
	private ObservableList<Horario> lstHorariosData = FXCollections.observableArrayList();
	private ObservableList<Horario> lstHorariosDataAux = FXCollections.observableArrayList();
	private ArrayList<Horario> lstHorariosSeleccionados = new ArrayList<>();

	@FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ListView<Horario> listViewHorarios;

    @FXML
    void guardarAction(ActionEvent event)
    {
    	guardar();
    }

	@FXML
    void cancelarAction(ActionEvent event)
    {
    	cerrarVentana();
    }

	/**
	 * Método que permite inicializar la vista
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		listViewHorarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	/**
	 * Método que permite guardar la información y devolverla a la
	 * ventana anterior
	 */
    private void guardar()
    {
    	lstHorariosDataAux = listViewHorarios.getSelectionModel().getSelectedItems();

    	if(lstHorariosDataAux != null && lstHorariosDataAux.size() > 0)
    	{
    		for (Horario horario : lstHorariosDataAux)
        	{
        		lstHorariosSeleccionados.add(horario);
    		}
    		lstHorariosDataAux = null;
        	cerrarVentana();
    	}
    	else
    	{
    		mostrarMensaje("Advantencia", "Seleccionar Horarios", "Debe seleccionar por lo menos 1 horario", AlertType.WARNING);
    	}
	}

    /**
     * Método que permite cerrar la ventana emergente
     */
	private void cerrarVentana()
	{
		Stage currentStage = (Stage) this.btnCancelar.getScene().getWindow();
		currentStage.close();
	}

	/**
	 * Método que permite asignar la clase principal(aplicacion)
	 * al controlador
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;
		listViewHorarios.getItems().clear();
		listViewHorarios.setItems(obtenerHorarios());

		lstHorariosSeleccionados = new ArrayList<>();
	}

	/**
	 * Método que permite obtener los horarios
	 * @return lstHorariosData
	 */
	private ObservableList<Horario> obtenerHorarios()
	{
		lstHorariosData.addAll(aplicacion.obtenerListaHorarioData());
		return lstHorariosData;
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
     * Método que permite obtener los horarios seleccionados
     * @return lstHorarioSeleccionados
     */
	public ArrayList<Horario> getLstHorariosSeleccionados()
	{
		return lstHorariosSeleccionados;
	}


}
