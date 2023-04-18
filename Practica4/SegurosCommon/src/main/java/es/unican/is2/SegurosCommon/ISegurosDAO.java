package es.unican.is2.SegurosCommon;
import es.unican.is2.*;

import java.util.List;

/**
 * Interfaz DAO para los seguros
 */
public interface ISegurosDAO  {
	
	/** 
	 * Persite un nuevo seguro
	 * @param v Seguro a anadir
	 * @return El seguro una vez anadido
	 *         null si no se anade el seguro
	 */
	public Seguro creaSeguro(Seguro v);
	
	/**
	 * Elimina el seguro cuya matricula se pasa
	 * como parametro
	 * @param matricula
	 * @return El seguro eliminado
	 *         null si no se encuentra el seguro
	 */
	public Seguro eliminaSeguro(String matricula);
	
	/**
	 * Actualiza el estado del seguro que se pasa
	 * como parametro
	 * @param nuevo Nuevo estado del seguro
	 * @return El seguro actualizado
	 */
	public Seguro actualizaSeguro(Seguro nuevo);
	
	/**
	 * Retorna el seguro cuya matricula asociada
	 * se pasa como parametro
	 * @param matricula
	 * @return El seguro
	 * 		   null si no se encuentra
	 */
	public Seguro seguro(String matricula);
	
	/**
	 * Retorna la lista completa de seguros
	 * @return La lista de seguros
	 *         Lista vacia si no hay ninguno
	 */
	public List<Seguro> seguros();
}
