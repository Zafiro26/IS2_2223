package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	private String nombre;
	private String calle;
	private String zip;
	private String localidad;
	private String telefono;
	private String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {  						// +1
			total = c.getSaldoContizacion(total);
		}
		return total;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCalle() {
		return calle;
	}

	public String getZip() {
		return zip;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDni() {
		return dni;
	}
	
	
	
}