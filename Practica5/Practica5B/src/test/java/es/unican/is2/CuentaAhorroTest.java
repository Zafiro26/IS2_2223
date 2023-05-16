package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CuentaAhorroTest {
	
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;
	
	@BeforeAll
	public static void inicializaAuxiliares() {
		LocalDateTime now = LocalDateTime.now();
		m1 = new Movimiento(null, null, 100);
		m1.setI(100);
		m2 = new Movimiento(null, null, 200);
		m2.setI(200);
		m3 = new Movimiento(null, null, 1500);
		m3.setI(1500);
	}

	@BeforeEach
	void inicializa() {
		sut = new CuentaAhorro("794311", LocalDate.now().plusYears(3), LocalDate.now().plusYears(4));
	}

	@Test
	void testConstructor() {
		assertEquals(sut.getCaducidadDebito(), LocalDate.now().plusYears(3));
		assertEquals(sut.getCaducidadCredito(),LocalDate.now().plusYears(4));
		assertEquals(sut.getLimiteDebito(), 1000);
		assertEquals(sut.getMovimientos().size(), 0);
		assertEquals(sut.getNumCuenta(), "794311");
	}
	
	@Test
	void testGetSaldoYAddMovimiento() {
		assertEquals(0, sut.getSaldo());	

		sut.addMovimiento(m1);
		assertEquals(100, sut.getSaldo());
		assertEquals(1, sut.getMovimientos().size());
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertEquals(1800, sut.getSaldo());
		assertEquals(3, sut.getMovimientos().size());
	}
	
	@Test
	void testRetirarSinConcepto() {
		
		try {
			sut.retirar(-10);
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(50);
			assertEquals(50, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			assertEquals("Retirada de efectivo", sut.getMovimientos().get(1).getC());
		} catch (datoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(100);
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) { }
	
	}
	
	@Test
	void testIngresarSinConcepto () {
	
		try {
			sut.ingresar(-1);
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		try {
			sut.ingresar(0.01);
			assertEquals(0.01, sut.getSaldo());
			assertEquals(1, sut.getMovimientos().size());
			assertEquals("Ingreso en efectivo", sut.getMovimientos().get(0).getC());
			
			sut.ingresar(100);
			assertEquals(100.01, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			
		} catch (datoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}
	
	
	@Test
	void testIngresarConConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar("Ingreso", -1);
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar("Ingreso1", 0.01);
			assertEquals(0.01, sut.getSaldo());
			assertEquals(1, sut.getMovimientos().size());
			assertEquals("Ingreso1", sut.getMovimientos().get(0).getC());
			
			sut.ingresar("Ingreso2", 100);
			assertEquals(100.01, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			assertEquals(sut.getMovimientos().get(1).getC(), "Ingreso2");
			
		} catch (datoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}
	
	@Test
	void testRetirarConConcepto() {
		
		try {
			sut.retirar("Retirada", -10);
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar("Retirada1", 50);
			assertEquals(50, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			assertEquals(sut.getMovimientos().get(1).getC(),"Retirada1");
		} catch (datoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar("Retirada2", 100);
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) {
			
		}
	
	}
	

	
}
