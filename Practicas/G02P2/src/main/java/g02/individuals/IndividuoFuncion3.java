package g02.individuals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Individuo para la funcion 3.
 */
public class IndividuoFuncion3 extends Individuo<Boolean> {

  /** The dimension. */
  private static int DIMENSION = 2;

  /**
   * Instantiates a new individuo funcion 3.
   *
   * @param precision precision de la codificacion
   */
  public IndividuoFuncion3(double precision) {
    this.numFenotipos = DIMENSION;
    this.tamGenes = new int[DIMENSION];
    this.min = new double[DIMENSION];
    this.max = new double[DIMENSION];
    this.min[0] = this.min[1] = -5;
    this.max[0] = this.max[1] = 5;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);
    this.tamTotal = tamGenes[0] + tamGenes[1];
    this.chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = this.rand.nextBoolean();
    }

  }

  /**
   * Instantiates a new individuo funcion 3.
   *
   * @param cromosoma cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoFuncion3(Boolean[] cromosoma, double precision) {
    this.numFenotipos = DIMENSION;
    this.tamGenes = new int[DIMENSION];
    this.min = new double[DIMENSION];
    this.max = new double[DIMENSION];
    this.min[0] = this.min[1] = -5;
    this.max[0] = this.max[1] = 5;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);
    this.tamTotal = tamGenes[0] + tamGenes[1];
    this.chromosome = new Boolean[this.tamTotal];
    for (int i = 0; i < this.tamTotal; i++) {
      this.chromosome[i] = cromosoma[i];
    }

  }

  /**
   * Fitness.
   *
   * @return fitness
   */
  @Override
  public double fitness() {

    return this.getValor();
  }

  /**
   * Mutar.
   *
   * @param individuo individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<Boolean> mutar(Individuo<Boolean> individuo, double prob) {
	  for(int i = 0; i < this.tamTotal; i++) {
		  if (ThreadLocalRandom.current().nextDouble() < prob) {

		      chromosome[i] = !chromosome[i];
		  }
	  }

    return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return the cromosoma
   */
  @Override
  public Boolean[] getCromosoma() {
    return this.chromosome;
  }

  /**
   * Gets the fenotype.
   *
   * @param index indice del fenotipo
   * @return el fenotipo
   */
  @Override
  public double getFenotipo(int index) {
    int start;
    int end;

    if (index == 0) {
      start = 0;
      end = this.tamGenes[index] - 1;
    } else {
      start = this.tamGenes[index - 1];
      end = this.tamGenes[index] + this.tamGenes[index - 1] - 1;
    }


    double res = 0;
    int power = 0;
    for (int i = end; i >= start; i--) {
      if (this.chromosome[i]) {
        res += Math.pow(2, power);
      }

      power++;
    }

    return this.min[index]
        + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index]) - 1));
  }

  /**
   * Gets the valor.
   *
   * @return valor del individuo en la funcion
   */
  @Override
  public double getValor() {

    double x = 0;
    double sum = 0;

    for (int i = 0; i < DIMENSION; ++i) {
      x = this.getFenotipo(i);
      sum += Math.pow(x, 4) - 16 * (Math.pow(x, 2)) + 5 * x;
    }
    return 0.5 * sum;
  }

  /**
   * Copy individuo.
   *
   * @return nuevo individuo con el mismo cromosoma y precision
   */
  @Override
  public Individuo<Boolean> copyIndividuo() {

    return new IndividuoFuncion3(chromosome, precision);
  }

  /**
   * Comprueba si es una funcion de maximizar o minimizar.
   *
   * @return false
   */
  @Override
  public boolean isMax() {
    return false;
  }

}
