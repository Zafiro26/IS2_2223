package es.unican.is2.SegurosCommon;

import es.unican.is2.*;
import java.util.List;

/**
 * Interfaz DAO para los vehiculos
 */
public interface IClientesDAO  {
	
	/** 
	 * Persite un nuevo Cliente
	 * @param c Cliente a anadir
	 * @return El cliente una vez anadido
	 *         null si no se puede anadir
	 */
	public Cliente creaCliente(Cliente c);
	
	/**
	 * Retorna el cliente cuyo dni se pasa 
	 * como parametro
	 * @param dni
	 * @return El cliente
	 * 		   null si no se encuentra
	 */
	public Cliente cliente(String dni);
	
	/**
	 * Actualiza el estado del cliente que se pasa
	 * como parametro
	 * @param nuevo Nuevo estado del cliente
	 * @return El cliente actualizado
	 */
	public Cliente actualizaCliente(Cliente nuevo);
	
	/**
	 * Elimina el cliente cuyo dni se pasa
	 * como parametro
	 * @param dni
	 * @return El cliente eliminado
	 *         null si no se encuentra el cliente
	 */
	public Cliente eliminaCliente(String dni);
	
	/**
	 * Retorna la lista completa de clientes
	 * @return La lista de clientes
	 *         Lista vacia si no hay ninguno
	 */
	public List<Cliente> clientes();
	
}
