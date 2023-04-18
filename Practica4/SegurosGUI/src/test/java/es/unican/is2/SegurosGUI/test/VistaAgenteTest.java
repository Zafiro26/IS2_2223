package es.unican.is2.SegurosGUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import es.unican.is2.SegurosCommon.*;
import es.unican.is2.SegurosDAO.*;
import es.unican.is2.SegurosGUI.VistaAgente;

import org.fest.swing.fixture.FrameFixture;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

class VistaAgenteTest {
	
	
	private FrameFixture demo;
	
	@BeforeEach
	public void setUp() {
		
		Cliente c = new Cliente();
		Seguro s = new Seguro();
		LocalDate fecha = LocalDate.of(2022, 5, 30);
		c.setDni("12345678B");
		c.setMinusvalia(false);
		c.setNombre("Pepe");
		s.setFecha(fecha);
		s.setCobertura(Cobertura.TERCEROS);
		s.setPotencia(120);
		
		List<Seguro> lista = new LinkedList<Seguro>();
		lista.add(s);
		c.setSeguros(lista);
		
		IClientesDAO cDAO = new ClientesDAO();
		cDAO.creaCliente(c);
		ISegurosDAO sDAO = new SegurosDAO();
		sDAO.creaSeguro(s);
		
		VistaAgente gui = new VistaAgente(IClientesDAO, ISegurosDAO, null);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	void test() {
		
		
		demo.button("btnBuscar").requireText("Buscar");
		demo.label("lblTotalCliente").requireText("Total A Pagar");
		demo.label("lblSeguros").requireText("Seguros");
		demo.label("lblNombreCliente").requireText("Nombre");
		demo.label("lblDatosCliente").requireText("Datos Cliente");
		demo.label("lblDniCliente").requireText("DNI Cliente");
		
		demo.textBox("txtDniClinete").enterText("12345678B");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Pepe");
		demo.textBox("txtTotalCliente").requireText("384");
		
		
		//demo.textBox("txtTotalCliente").requireText(null);
		//demo.textBox("");
		fail("Not yet implemented");
	}

}



