package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	static final double COMISION = 0.05;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)							//+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		
		LocalDateTime now = LocalDateTime.now();
		x += x * COMISION; // Añadimos una comisión de un 5%
		Movimiento m = new Movimiento("Retirada en cajero automático", now, -x);
		
		
		if (getGastosAcumulados()+x > mCredito)			//+1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)			//+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito)			//+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Compra a crédito en: " + datos, now, -x);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {		//+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() {
		LocalDateTime now = LocalDateTime.now();
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {		//+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		Movimiento liq = new Movimiento("Liquidación de operaciones tarjeta crédito", now, r);
	
		if (r != 0)										//+1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}

}