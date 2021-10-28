package co.uniquindio.proyectoFinalGrupo1.controller;

import co.uniquindio.proyectoFinalGrupo1.Aplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la vista inicio
 * @author Juan Camilo Ramos R.
 *
 */
public class InicioController
{

	Aplicacion aplicacion;

	@FXML
    private Label lblCantidadCreditos;

    @FXML
    private Label lblCantidadInstructores;

    @FXML
    private Label lblCantidadEstudiantes;

    @FXML
    private Label lblCantidadLugares;

	/**
	 * Método que permite asignar la instacia de la clase
	 * aplicacion(principal)
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion)
	{
		this.aplicacion = aplicacion;
		obtenerCantidadesActuales();
	}

	/**
	 * Método que permite obtener las cantidades actuales de
	 * los datos de la aplicacion
	 */
	private void obtenerCantidadesActuales()
	{
		int[] cantidades = aplicacion.obtenerCantidadesActuales();

		lblCantidadEstudiantes.setText(cantidades[0] + "");
		lblCantidadInstructores.setText(cantidades[1] + "");
		lblCantidadCreditos.setText(cantidades[2] + "");
		lblCantidadLugares.setText(cantidades[3] + "");
	}



}
