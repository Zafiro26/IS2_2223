package es.unican.is2.SegurosCommon.test;

//import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;



import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

//import org.junit.Test;

import es.unican.is2.SegurosCommon.*;

public class SegurosTest {
	
	@Test
	public void Matricula() {
		String tmp = "12345678V";
		Seguro seguro = new Seguro();
		seguro.setMatricula(tmp);
		String tmp2 = seguro.getMatricula();
		assertTrue(tmp.equals(tmp2));
	}
	
	@Test
	public void Potencia() {
		
		int tmp;
		Seguro seguro = new Seguro();		
		
		//Valor correcto
		tmp = 70;
		seguro.setPotencia(tmp);
		int tmp2 = seguro.getPotencia();
		assertTrue(tmp == tmp2);
		
		//Valor incorrecto
		tmp = -10;
		final int tmp3 = tmp;
		assertThrows(OperacionNoValida.class, () -> {seguro.setPotencia(tmp3);});
		
	}
	
	@Test
	public void Fecha() {
		
		LocalDate fecha = LocalDate.of(2020, 1, 1);
		Seguro seguro = new Seguro();
		
		//Valor Correcto
		seguro.setFecha(fecha);
		LocalDate tmp2 = seguro.getFecha();
		assertTrue(fecha.equals(tmp2));
		
		//Valor incorrecto
		fecha = LocalDate.of(2024, 1, 1);
		final LocalDate fecha2 = fecha;
		assertThrows(OperacionNoValida.class, () -> {seguro.setFecha(fecha2);});
		
		fecha = LocalDate.of(2023, 9, 20);
		final LocalDate fecha3 = fecha;
		assertThrows(OperacionNoValida.class, () -> {seguro.setFecha(fecha3);});
		
	}

	@Test
	public void pago() {
		
		LocalDate fecha = LocalDate.of(2022, 5, 30);
		double pago;
		
		//Primer caso valido
		Seguro seguro1 = new Seguro();
		seguro1.setCobertura(Cobertura.TERCEROS);
		seguro1.setPotencia(120);
		seguro1.setFecha(fecha);
		pago = seguro1.precio();
		assertTrue(pago == 384);
		fecha = LocalDate.of(2023, 1, 1);
		seguro1.setFecha(fecha);
		pago = seguro1.precio();
		assertTrue(pago == 384);
		
		
		//Segundo caso valido
		seguro1.setCobertura(Cobertura.TERCEROSLUNAS);
		seguro1.setPotencia(100);
		fecha = LocalDate.of(2021, 5, 30);
		seguro1.setFecha(fecha);
		pago = seguro1.precio();
		assertTrue(pago == 567);
		
		//Tercer caso valido
		seguro1.setCobertura(Cobertura.TODORIESGO);
		seguro1.setPotencia(70);
		fecha = LocalDate.of(2020, 5, 30);
		seguro1.setFecha(fecha);
		pago = seguro1.precio();
		assertTrue(pago == 1000);
		
		
		
	}
	
	@Test
	public void DNI() {
		
		String DNI = "12345678A";
			
		//Caso valido
		Cliente cliente = new Cliente();
		cliente.setDni(DNI);
		String tmp = cliente.getDni();
		assertTrue(tmp.equals(DNI));
			
		//Caso no valido
		DNI = "1234567AB";
		final String tmp2 = DNI;
		assertThrows(OperacionNoValida.class, () -> {cliente.setDni(tmp2);});
		
		}
	
	@Test
	public void nombre() {
		
		String nombre = "hola";
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		String tmp = cliente.getNombre();
		assertTrue(tmp.equals(nombre));
		
		assertThrows(OperacionNoValida.class, () -> {cliente.setNombre(null);});
		
		
	}
	
	@Test
	public void total() {
		
		Seguro seguro1 = new Seguro();
		Seguro seguro2 = new Seguro();
		Seguro seguro3 = new Seguro();
		
		LocalDate fecha = LocalDate.of(2022, 5, 30);
		seguro1.setCobertura(Cobertura.TERCEROS);
		seguro1.setPotencia(120);
		seguro1.setFecha(fecha);
		
		seguro2.setCobertura(Cobertura.TERCEROSLUNAS);
		seguro2.setPotencia(100);
		fecha = LocalDate.of(2021, 5, 30);
		seguro2.setFecha(fecha);
		
		seguro3.setCobertura(Cobertura.TODORIESGO);
		seguro3.setPotencia(70);
		fecha = LocalDate.of(2020, 5, 30);
		seguro3.setFecha(fecha);
		
		Cliente cliente = new Cliente();
		cliente.setMinusvalia(false);
		List<Seguro> seguros = new LinkedList<Seguro>();
		seguros.add(seguro1);
		seguros.add(seguro2);
		seguros.add(seguro3);
		cliente.setSeguros(seguros);
		double pago = cliente.totalSeguros();
		assertTrue(pago == 1951);
		
		cliente.setMinusvalia(true);
		pago = cliente.totalSeguros();
		assertTrue(pago == 1463.25);
		
	}

}
