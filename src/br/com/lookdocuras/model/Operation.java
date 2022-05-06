package br.com.lookdocuras.model;

public interface Operation {
	
	public int SUM = 1;
	public int SUB = 2;
	public int DIV = 3;
	public int MULT = 4;
	public int PERC = 5;
	public int EQUALS = 6;

	public double calc(double x, double y);
	public int getType();
	
}
