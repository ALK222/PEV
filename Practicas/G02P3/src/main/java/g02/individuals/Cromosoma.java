package g02.individuals;

import java.util.concurrent.ThreadLocalRandom;

public class Cromosoma {
	public static final String terminales[] = { "x", "-2", "-1", "0", "1", "2" };
	public static final String funciones[] = { "add", "sub", "mul" };
	private Arbol arbol;
	private double[] fitness;
	private double fitness_bruto; // Aptitud antes de transformarla
	private double punt;
	private double puntAcum;
	private String fenotipo;

	public Cromosoma(int profundidad, int tipoCreacion, int nodos) {
		arbol = new Arbol(profundidad, nodos);
		switch (tipoCreacion) {
		case 0:
			arbol.inicializacionCreciente(0, nodos);
			break;
		case 1:
			arbol.inicializacionCompleta(0, nodos);
			break;
		case 2:
			int ini = ThreadLocalRandom.current().nextInt(2);
			if (ini == 0)
				arbol.inicializacionCreciente(0, nodos);
			else
				arbol.inicializacionCompleta(0, nodos);
			break;
		}

		this.fitness = new double[101];
		this.updateFitness();

	}

	public Cromosoma(Arbol arbol) {
		this.arbol = new Arbol(arbol);
		this.fitness = new double[101];
		this.updateFitness();
	}

	public void updateFitness() {
		for (int i = 0; i < 101; ++i) {
			double valorX = (-1.0) + ((2.0 / 100.0) * i);
			this.fitness[i] = this.updateFitnessAux(valorX, this.getArbol());
		}
	}

	// Cálculo del fitness según una x dada
	private double updateFitnessAux(double x, Arbol a) {
		double fit = 0;
		if (!a.getEsRaiz()) {
			if (a.getValor() == "x")
				return x;
			else
				return Double.valueOf(a.getValor());
		} else {
			if (a.getValor() == "add") {
				fit = updateFitnessAux(x, a.getHijos().get(0)) + updateFitnessAux(x, a.getHijos().get(1));
			} else if (a.getValor() == "sub") {
				fit = updateFitnessAux(x, a.getHijos().get(0)) - updateFitnessAux(x, a.getHijos().get(1));
			} else if (a.getValor() == "mul") {
				fit = updateFitnessAux(x, a.getHijos().get(0)) * updateFitnessAux(x, a.getHijos().get(1));
			}
		}
		return fit;
	}

	public Arbol getArbol() {
		return this.arbol;
	}

	public int getTam() {
		return this.arbol.getNumNodos();
	}

	public double[] getFitness() {
		return this.fitness;
	}
}
