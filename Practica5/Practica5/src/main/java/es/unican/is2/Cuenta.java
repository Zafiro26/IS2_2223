package es.unican.is2;

public class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {
		return numCuenta;
	}

	public double getSaldoContizacion(double total) {
		if (this instanceof CuentaAhorro) {			//+1
			total += ((CuentaAhorro) this).getSaldo();
		} else if (this instanceof CuentaValores)  {   //+1
			total = ((CuentaValores) this).getCotizacion(total);
		}
		return total;
	}
	
}
