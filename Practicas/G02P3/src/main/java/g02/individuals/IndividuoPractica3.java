package g02.individuals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IndividuoPractica3 extends Individuo<Cromosoma> {


  /**
   * Instantiates a new individuo practica 3
   *
   * @param precision precision de la codificacion
   */
  public IndividuoPractica3(double precision) {
	  this.chromosome = new Cromosoma(5,0,2);

  }

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoPractica3(Cromosoma chromosome) {
    this.chromosome = chromosome;
    
  }


  /**
   * Calcula el fitness del individuo.
   *
   * @return fitness del individuo
   */
  public double fitness() {
    return this.getValor();
  }

  /**
   * Muta un individuo.
   *
   * @param individuo Individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<Cromosoma> mutar(Individuo<Cromosoma> individuo, double prob, int mut) {
	  for(int i = 0; i < this.tamTotal; i++) {
		  if (ThreadLocalRandom.current().nextDouble() < prob) {

		      //chromosome[i] = !chromosome[i];
	  }
    }

    return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public Cromosoma getCromosoma() {
    return chromosome;
  }

  /**
   * Gets the fenotipo of a given chromosome.
   *
   * @param index the index of the fenotype
   * @return the fenotype
   */
  // Necesario para calcular el fitness
  @Override
  public double getFenotipo(int index) {
//    int start;
//    int end;
//
//    if (index == 0) {
//      start = 0;
//      end = this.tamGenes[index] - 1;
//    } else {
//      start = this.tamGenes[index - 1];
//      end = this.tamGenes[index] + this.tamGenes[index - 1] - 1;
//    }
//
//
//    double res = 0;
//    int power = 0;
//    for (int i = end; i >= start; i--) {
//      if (this.chromosome[i]) {
//        res += Math.pow(2, power);
//      }
//      power++;
//    }
//
//    return this.min[index]
//        + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index]) - 1));
	  return 0;
  }

  /**
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
	  double diferencia = 0;
    for(int i = 0; i < 101; ++i) {
    	double valorX = (-1.0) + ((2.0/100.0) * i);
    	double valor1 = Math.pow(valorX, 4) + Math.pow(valorX, 3) + Math.pow(valorX, 2) + valorX + 1;
    	double valor2 = this.chromosome.updateFitness(valorX, this.chromosome.getArbol());
    	diferencia += Math.pow(valor1 - valor2, 2);
    }
    return Math.sqrt(diferencia/101);
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<Cromosoma> copyIndividuo() {
    return new IndividuoPractica3(this.chromosome);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return false;
  }


}
