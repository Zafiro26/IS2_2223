package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws datoErroneoException {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0)			//+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Ingreso en efectivo", now, x);
		this.mMovimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x <= 0)			//+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)			//+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Retirada de efectivo", now, -x);
		this.mMovimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException {
		if (x <= 0)			//+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, x);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x)			//+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)				//+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");		
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, -x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {		//+1
			Movimiento m = mMovimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() {
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}