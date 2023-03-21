package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.*;



public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros {
	
	private IClientesDAO clientes;
	private ISegurosDAO seguros;
	
	public GestionSeguros(IClientesDAO clientes,
			ISegurosDAO seguros) {
		
		this.clientes = clientes;
		this.seguros = seguros;
		
	}
	
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		return null;
	}
	
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		return null;
	}
	
	public Cliente nuevoCliente(Cliente c) {
		return null;
	}
	
	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		
		return null;
	}
	
	public Cliente cliente(String dni) {
		return null;
		
	}
	
	public Seguro seguro(String matricula) {
		return null;
	}

}
