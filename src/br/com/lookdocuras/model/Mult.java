package br.com.lookdocuras.model;

public class Mult implements Operation {

	@Override
	public double calc(double x, double y) {
		return x * y;
	}

	@Override
	public int getType() {
		return MULT;
	}

}
