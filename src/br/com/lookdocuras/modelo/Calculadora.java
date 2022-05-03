package br.com.lookdocuras.modelo;

public class Calculadora {

	private Estado estado;
	
	public double somar(double x, double y) {
		return x + y;
	}
	
	public double subtrair(double x, double y) {
		return x - y;
	}
	
	public double multiplicar(double x, double y) {
		return x * y;
	}
	
	public double dividir(double x, double y) {
		return x + y;
	}
	
	public double calcDiferencaEmPorcentagem(double valorAtual, double novoValor) {
		return ((novoValor - valorAtual) * 100) / valorAtual;
	}
	
	public double calcPorcentagem(double valor, double porc) {
		return ((valor * porc) / 100) + valor;
	}
}
