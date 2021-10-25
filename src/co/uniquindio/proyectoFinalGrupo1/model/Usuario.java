package co.uniquindio.proyectoFinalGrupo1.model;

/**
 * Clase usuario
 * @author Juan Camilo Ramos R.
 *
 */
public class Usuario
{
	private String usuario;
	private String contrasena;
	private TipoUsuario tipoUsuario;

	/**
	 * Constructor de la clase usuario
	 */
	public Usuario()
	{

	}

	/**
	 * Método que permite obtener el usuario
	 * @return usuario
	 */
	public String getUsuario()
	{
		return usuario;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el nombre de usuario
	 * @param usuario
	 */
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * Método que permite obtener la contraseña del usuario
	 * @return contrasena
	 */
	public String getContrasena()
	{
		return contrasena;
	}

	/**
	 * Método que permite asignar y}/o actualizar la contraseña
	 * del usuario
	 * @param contrasena
	 */
	public void setContrasena(String contrasena)
	{
		this.contrasena = contrasena;
	}

	/**
	 * Método que permite obtener el tipo de usuario
	 * @return tipoUsuario
	 */
	public TipoUsuario getTipoUsuario()
	{
		return tipoUsuario;
	}

	/**
	 * Método que permite asignar y/o actualizar
	 * el tipo de usuario
	 * @param tipoUsuario
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Método que permite imprimir el usuario ingresado
	 */
	@Override
	public String toString()
	{
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", tipoUsuario=" + tipoUsuario + "]";
	}


}
