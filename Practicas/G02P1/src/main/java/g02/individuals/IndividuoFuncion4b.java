package g02.individuals;

import java.util.Random;

/**
 * Individuo de la funcion 4 codificado en reales.
 */
public class IndividuoFuncion4b extends Individuo<Double> {

  /**
   * Instantiates a new individuo funcion 4 b.
   *
   * @param precision precision de la codificacion
   * @param dim dimensiones de la funcion
   */
  public IndividuoFuncion4b(double precision, int dim) {
    this.precision = precision;
    this.tamTotal = dim;
    this.numFenotipos = dim;

    this.chromosome = new Double[this.tamTotal];
    for (int i = 0; i < this.tamTotal; i++) {
      this.chromosome[i] = this.rand.nextDouble() * Math.PI;
    }
  }

  /**
   * Instantiates a new individuo funcion 4 b.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   * @param dim dimensiones de la funcion
   */
  public IndividuoFuncion4b(Double[] chromosome, double precision, int dim) {
    this.precision = precision;
    this.tamTotal = dim;
    this.numFenotipos = dim;

    this.chromosome = new Double[this.tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = chromosome[i];
    }
  }

  /**
   * Fitness.
   *
   * @return fitness
   */
  @Override
  public double fitness() {
    return getValor();
  }

  /**
   * Mutar.
   *
   * @param individuo individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<Double> mutar(Individuo<Double> individuo, double prob) {
    Random r = new Random();

    if (r.nextDouble() < prob) {
      int pos = (int) (r.nextDouble() * this.tamTotal);

      chromosome[pos] = r.nextDouble() * Math.PI;
    }
    return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return the cromosoma
   */
  @Override
  public Double[] getCromosoma() {
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
    return (double)chromosome[index];
  }

  /**
   * Gets the valor.
   *
   * @return valor del individuo en la funcion
   */
  @Override
  public double getValor() {
    double sum = 0;
    int M = 10;

    for (int i = 0; i < this.tamTotal; ++i) {
      sum += Math.sin(this.chromosome[i])
          * Math.pow(Math.sin((i + 1) * Math.pow(this.chromosome[i], 2) / Math.PI), 2 * M);
    }
    return -sum;
  }

  /**
   * Copy individuo.
   *
   * @return nuevo individuo con el mismo cromosoma y precision
   */
  @Override
  public Individuo<Double> copyIndividuo() {
    return new IndividuoFuncion4b(this.chromosome, this.precision, this.tamTotal);
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
