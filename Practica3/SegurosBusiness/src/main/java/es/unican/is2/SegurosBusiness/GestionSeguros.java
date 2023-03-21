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
		
		if (clientes.cliente(dni) == null) {
			throw new OperacionNoValida("Cliente no existente");
		} else if (seguros.seguro(s.getMatricula()) != null) {
			throw new OperacionNoValida("Seguro ya existente");
		}
		
		seguros.creaSeguro(s);
		
		return s;
	}
	
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		
		Seguro tmp = seguros.seguro(matricula);
		
		if (clientes.cliente(dni) == null) {
			throw new OperacionNoValida("Cliente no existente");
		} else if (seguros.seguro(matricula) == null) {
			throw new OperacionNoValida("Seguro no existente");
		} else if (clientes.cliente(dni).getSeguros().contains(seguros.seguro(matricula))) {
			throw new OperacionNoValida("Seguro no esta asociado al cliente");
		}
		
		seguros.eliminaSeguro(matricula);
		return tmp;
	}
	
	public Cliente nuevoCliente(Cliente c) {
		if (clientes.cliente(c.getDni()) == null) {
			clientes.creaCliente(c);
			return c;
		}
		
		throw new OperacionNoValida("Cliente ya existente");
	}
	
	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		
		Cliente tmp = clientes.cliente(dni);
		
		if (tmp == null) {
			throw new OperacionNoValida("Cliente no existe");
		} else if (!tmp.getSeguros().isEmpty()) {
			throw new OperacionNoValida("Seguros a su nombre existentes");
		}
		
		clientes.eliminaCliente(dni);
		
		return tmp;
	}
	
	public Cliente cliente(String dni) {
		return clientes.cliente(dni);
		
	}
	
	public Seguro seguro(String matricula) {
		return seguros.seguro(matricula);
	}

}
