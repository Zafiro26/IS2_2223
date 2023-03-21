package es.unican.is2.SegurosCommon;
import es.unican.is2.*;


/**
 * Interfaz con métodos de consulta de información
 * de la empresa de seguros
 */
public interface IInfoSeguros {
	
	/**
	 * Retorna el cliente cuyo dni se pasa como parametro
	 * @param dni DNI del cliente buscado
	 * @return El cliente cuyo dni coincide con el parametro
	 * 		   null en caso de que no exista
	 */
	public Cliente cliente(String dni); 
	
	/**
	 * Retorna el seguro cuya matricula asociada se pasa como parametro
	 * @param matricula Identificador del seguro
	 * @return El seguro indicado
	 * 	       null si no existe
	 */
	public Seguro seguro(String matricula); 	
	

}
