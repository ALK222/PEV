package g02.individuals;

import java.util.concurrent.ThreadLocalRandom;

public class Cromosoma {
	public static String terminales[];
	public static final String terminales6[] = { "x", "-2", "-1", "0", "1", "2" };
	public static final String funciones[] = { "add", "sub", "mul" };
	private Arbol arbol;
	private double fitness;
	private double fitness_bruto; //Aptitud antes de transformarla
	private double punt;
	private double puntAcum;
	private String fenotipo;
	
	public Cromosoma(int profundidad, int tipoCreacion, boolean useIf, int tipoMultiplexor) {
		arbol = new Arbol(profundidad, useIf);
		switch(tipoCreacion){
		case 0:
			arbol.inicializacionCreciente(0);
			break;
		case 1:
			arbol.inicializacionCompleta(0,0);
			break;
		case 2:
			int ini = ThreadLocalRandom.current().nextInt(2);
			if(ini == 0) arbol.inicializacionCreciente(0);
			else arbol.inicializacionCompleta(0,0);
			break;
		}
	}
	
	// Cálculo del fitness según una x dada
	public double updateFitness(double x, Arbol a) {
		double fit = 0;
		if(!a.getEsRaiz()) {
			if(a.getValor() == "x") return x;
			else return Double.valueOf(a.getValor());
		}
		else {
			if(a.getValor() == "add") {
				fit = updateFitness(x,a.getHijos().get(0)) + updateFitness(x, a.getHijos().get(1));
			}
			else if(a.getValor() == "sub") {
				fit = updateFitness(x,a.getHijos().get(0)) - updateFitness(x, a.getHijos().get(1));
			}
			else if(a.getValor() == "mul") {
				fit = updateFitness(x,a.getHijos().get(0)) * updateFitness(x, a.getHijos().get(1));
			}
		}
		return fit;
	}
	
	public Arbol getArbol() {
		return arbol;
	}
}
