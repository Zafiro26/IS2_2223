package es.unican.is2.SegurosCommon;
import es.unican.is2.*;


import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Clase que representa un seguro de coche.
 * Un seguro se identifica por la matricula
 * del coche para el que se contrata el seguro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seguro")
public class Seguro {
	
	private static final double PORCENTAJE_TRAMO_1 = 0.05;
	private static final double PORCENTAJE_TRAMO_2 = 0.20;
	private static final int INICIO_TRAMO_1= 90;
	private static final int FIN_TRAMO_1=110;
	private static final double DESCUENTO_PRIMER_ANHO = 0.8;
	private static final double DESCUENTO_SEGUNDO_ANHO = 0.9;
	
    
    @XmlAttribute(required = true)
    private int potencia;
    
    @XmlAttribute(required = true)
    private String matricula;
    
    @XmlAttribute(required = true)
    private Cobertura cobertura;
    
    @XmlAttribute(name="fecha", required=true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaContratacion;
    
    public Seguro() {}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Define el valor para la matricula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Define el valor para la cobertura
	 */
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}


	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Define el valor de la potencia.
     */
    public void setPotencia(int value) throws OperacionNoValida {
    	
    	if (value < 0) {
    		throw new OperacionNoValida("Valor negativo");
    	} else {
        this.potencia = value;
    	}
    }
    
    public void setFecha(LocalDate fecha) {
    	
    	LocalDate fechaActual = LocalDate.now();
    	
    	int anhos = fechaActual.getYear() - fecha.getYear();
    	int dia = fechaActual.getDayOfYear() - fecha.getDayOfYear();
    	
    	if (anhos < 0) {
    		throw new OperacionNoValida("Fecha no valida");
    	} else if (anhos == 0 && dia < 0) {
    		throw new OperacionNoValida("Fecha no valida");
    	} else {
    		this.fechaContratacion = fecha;
    	}
    }
    
    public LocalDate getFecha() {
    	return this.fechaContratacion;
    }
    
    /**
     * Retorna el precio del seguro
     * @return
     */
    public double precio() {
    	
    	double pago = 0;
    	if (cobertura == Cobertura.TERCEROS) {
    		pago = 400;
    	} else if (cobertura == Cobertura.TERCEROSLUNAS) {
    		pago = 600;
    	} else {
    		pago = 1000;
    	}
    	
    	if (this.potencia > FIN_TRAMO_1) {
    		pago = pago + (pago * PORCENTAJE_TRAMO_2);
    	} else if (this.potencia > INICIO_TRAMO_1) {
    		pago = pago + (pago * PORCENTAJE_TRAMO_1);
    	}
    	
    	LocalDate fechaActual = LocalDate.now();
    	int anhos = fechaActual.getYear() - this.fechaContratacion.getYear();
    	int dia = fechaActual.getDayOfYear() - this.fechaContratacion.getDayOfYear();
    	
    	if (anhos < 1) {
    		pago = pago * DESCUENTO_PRIMER_ANHO;
    	} else if (anhos < 2 && dia < 0) {
    		pago = pago * DESCUENTO_PRIMER_ANHO;
    	} else if (anhos == 2 && dia < 0) {
    		pago = pago * DESCUENTO_SEGUNDO_ANHO;
    	}
    	
    	
    	return pago;
    }

}
