package es.unican.is2.SegurosCommon;

import es.unican.is2.*;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Cliente")
public class Cliente {

	@XmlElement(required=true, name="seguro")
    private List<Seguro> seguros = new LinkedList<Seguro>();
    
    @XmlAttribute(required = true)
    private String nombre;
    
    @XmlAttribute(required = true)
    private String dni;
    
    @XmlAttribute(required = true)
    private boolean minusvalia;
    
    
    public Cliente(){}  

	/**
     * Retorna los seguros del cliente 
     */
    public List<Seguro> getSeguros() {
        if (seguros == null) {
        	seguros = new LinkedList<Seguro>();
        }
        return seguros;
    }
    
    /**
     * Define el valor de la propiedad seguros
     */
    public void setSeguros(List<Seguro> value) {
		this.seguros = value;
		
	}

    /**
     * Retorna el nombre del cliente.   
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.  
     */
    public void setNombre(String value) throws OperacionNoValida {
    	if (value == null) {
    		throw new OperacionNoValida("Nombre no es valido");
    	} else {
    		this.nombre = value;
    	}
    }

    /**
     * Retorna el dni del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Define el valor de la propiedad dni.
     */
    public void setDni(String value) throws OperacionNoValida {
    	
    	boolean valido = value.matches("[1-9]{8}[A-Z]");
    	if (!valido) {
    		throw new OperacionNoValida("DNI no es valido");
    	} else {
    		this.dni = value;
    	}
    }
    
    /**
     * Indica si el cliente es minusvalido
     */
    public boolean getMinusvalia() {
    	return minusvalia;
    }
    
    public void setMinusvalia(boolean minus)  {
    	minusvalia = minus;
    }
    
    /**
     * Calcula el total a pagar por el cliente por 
     * todos los seguros a su nombre
     */
    public double totalSeguros() {
    	
    	double total = 0;
    	double pago;
    	for(int i = 0; i < seguros.size(); i++) {
    		pago = seguros.get(i).precio();
    		total += pago;
    	}
    	
    	if (this.minusvalia) {
    		total = total * 0.75;
    	}
    	return total;
    }

}
