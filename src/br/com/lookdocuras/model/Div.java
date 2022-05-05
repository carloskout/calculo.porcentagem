package br.com.lookdocuras.model;

public class Div implements Operation {

	@Override
	public double calc(double x, double y) {
		// TODO Auto-generated method stub
		if(x > 0)
			return x / y;
		else
			return 0;
	}

	@Override
	public int getType() {
		return DIV;
	}

}
