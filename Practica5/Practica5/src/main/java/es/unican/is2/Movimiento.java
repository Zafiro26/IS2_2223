package es.unican.is2;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;
	
	public Movimiento(String mConcepto, LocalDateTime mFecha, double mImporte) {
		this.mConcepto = mConcepto;
		this.mFecha = mFecha;
		this.mImporte = mImporte;
	}

	public double getI() {
		return mImporte;
	}

	public void setI(double newMImporte) {
		mImporte = newMImporte;
	}
	
	public String getC() {
		return mConcepto;
	}

	public void setC(String newMConcepto) {
		mConcepto = newMConcepto;
	}

	public LocalDateTime getF() {
		return mFecha;
	}

	public void setF(LocalDateTime newMFecha) {
		mFecha = newMFecha;
	}
	
}