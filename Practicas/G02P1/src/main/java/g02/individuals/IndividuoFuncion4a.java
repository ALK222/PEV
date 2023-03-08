package g02.individuals;

import java.util.Random;

/**
 * Individuo de la funcion 4 codificado en binario.
 */
public class IndividuoFuncion4a extends Individuo<Boolean> {

  /** The dimension. */
  private int dimension;

  /**
   * Instantiates a new individuo funcion 4 a.
   *
   * @param precision precision de la codificacion
   * @param dimensiones dimensiones de la funcion
   */
  public IndividuoFuncion4a(double precision, int dimensiones) {
    this.numFenotipos = dimensiones;
    this.tamGenes = new int[dimensiones];
    this.min = new double[dimensiones];
    this.max = new double[dimensiones];

    for (int i = 0; i < dimensiones; ++i) {
      min[i] = 0;
      max[i] = Math.PI;
    }
    this.dimension = dimensiones;
    this.precision = precision;

    for (int i = 0; i < dimension; ++i) {
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
    }

    this.tamTotal = 0;

    for (int i = 0; i < dimension; ++i) {
    	this.tamTotal += tamGenes[i];
    }


    this.chromosome = new Boolean[this.tamTotal];
    for (int i = 0; i < this.tamTotal; i++) {
      this.chromosome[i] = this.rand.nextBoolean();
    }

  }

  /**
   * Instantiates a new individuo funcion 4 a.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   * @param dimensiones dimensiones de la funcion
   */
  public IndividuoFuncion4a(Boolean[] chromosome, double precision, int dimensiones) {
    this.numFenotipos = dimensiones;
    this.tamGenes = new int[dimensiones];
    this.min = new double[dimensiones];
    this.max = new double[dimensiones];

    for (int i = 0; i < dimensiones; ++i) {
      min[i] = 0;
      max[i] = Math.PI;
    }
    this.dimension = dimensiones;
    this.precision = precision;

    for (int i = 0; i < dimension; ++i) {
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
    }

    int tamTotal = 0;

    for (int i = 0; i < dimension; ++i) {
      tamTotal += tamGenes[i];
    }
    this.chromosome = new Boolean[tamTotal];
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
    Random r = new Random();

    if (r.nextDouble() < prob) {
    	int pos = r.nextInt(this.tamTotal - 1);

      chromosome[pos] = !chromosome[pos];
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
      start = this.tamGenes[index - 1] * index;
      end = start + this.tamGenes[index] - 1;
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
    double sum = 0;

    int M = 10;

    for (int i = 0; i < dimension; i++) {
      double x = this.getFenotipo(i);
      sum += Math.sin(x) * Math.pow(Math.sin((i + 1) * Math.pow(x, 2) / Math.PI), 2 * M);
    }

    return -sum;
  }

  /**
   * Copy individuo.
   *
   * @return nuevo individuo con el mismo cromosoma y precision
   */
  @Override
  public Individuo<Boolean> copyIndividuo() {
    return new IndividuoFuncion4a(this.chromosome, this.precision, this.dimension);
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
