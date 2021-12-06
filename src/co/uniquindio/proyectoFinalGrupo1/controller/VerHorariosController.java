package co.uniquindio.proyectoFinalGrupo1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import co.uniquindio.proyectoFinalGrupo1.model.Dias;
import co.uniquindio.proyectoFinalGrupo1.model.Horario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller de la vista de ver horarios
 * @author Juan Camilo Ramos R.
 *
 */
public class VerHorariosController implements Initializable
{
	private Aplicacion aplicacion;
	private ObservableList<Horario> lstHorariosData = FXCollections.observableArrayList();
	private ArrayList<Horario> lstHorarios = new ArrayList<>();

	@FXML
    private TableColumn<Horario, Dias> columnDia;

    @FXML
    private TableColumn<Horario, String> columnCodigo;

    @FXML
    private Button btnAceptar;

    @FXML
    private TableView<Horario> tableHorarios;

    @FXML
    private TableColumn<Horario, String> columnHoraFinal;

    @FXML
    private TableColumn<Horario, String> columnHoraInicio;

    @FXML
    void aceptarAction(ActionEvent event)
    {
    	cerrar();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("code"));
		this.columnHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
		this.columnHoraFinal.setCellValueFactory(new PropertyValueFactory<>("horaFinal"));
		this.columnDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
	}

	/**
	 * Método que permite cerrar la ventana emergente
	 */
	private void cerrar()
	{
		Stage currentStage = (Stage) this.btnAceptar.getScene().getWindow();
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

		this.tableHorarios.getItems().clear();
		this.tableHorarios.setItems(obtenerListaHorariosData());
	}

	private ObservableList<Horario> obtenerListaHorariosData()
	{
		return lstHorariosData;
	}




	/**
	 * Método que permite asignar la lista de horarios
	 * @param lstHorarios
	 */
	public void setLstHorarios(ArrayList<Horario> lstHorarios)
	{
		this.lstHorarios = lstHorarios;
		this.lstHorariosData.addAll(lstHorarios);
	}


}
