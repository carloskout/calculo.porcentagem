package br.com.lookdocuras.model;

public class Equals implements Operation {

	@Override
	public double calc(double x, double y) {
		return 0;
	}

	@Override
	public int getType() {
		return EQUALS;
	}

}
