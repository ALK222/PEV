package g02.individuals;

import java.util.Random;

/**
 * Individuo de la funcion 2.
 */
public class IndividuoFuncion2 extends Individuo<Boolean> {

  /** The dimension. */
  private int dimension;

  /**
   * Instantiates a new individuo funcion 2.
   *
   * @param precision precision de la codificacion
   * @param dim dimensiones de la funcion
   */
  public IndividuoFuncion2(double precision, int dim) {
    this.numFenotipos = dim;
    this.dimension = dim;
    this.tamGenes = new int[this.dimension];
    this.min = new double[this.dimension];
    this.max = new double[this.dimension];
    this.precision = precision;
    int tamTotal = 0;
    for (int i = 0; i < this.dimension; i++) {
      this.min[i] = -600.000;
      this.max[i] = 600.000;
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
      tamTotal += tamGenes[i];
    }

    this.chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = this.rand.nextBoolean();
    }

  }

  /**
   * Instantiates a new individuo funcion 2.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   * @param dim dimension de la funcion
   */
  public IndividuoFuncion2(Boolean[] chromosome, double precision, int dim) {
    this.numFenotipos = dim;
    this.dimension = dim;
    this.tamGenes = new int[this.dimension];
    this.min = new double[this.dimension];
    this.max = new double[this.dimension];
    this.precision = precision;
    int tamTotal = 0;
    for (int i = 0; i < this.dimension; i++) {
      this.min[i] = -600.000;
      this.max[i] = 600.000;
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
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
      int pos = r.nextInt() * this.tamTotal;

      chromosome[pos] = !chromosome[pos];
    }

    return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public Boolean[] getCromosoma() {
    return chromosome;
  }

  /**
   * Gets the fenotype.
   *
   * @param index indice del fenotipo en el cromosoma
   * @return el fenotipo
   */
  // Necesario para calcular el fitness
  @Override
  public double getFenotipo(int index) {
    int start;
    int end;

    if (index == 0) {
      start = 0;
      end = this.tamGenes[index] - 1;
    } else {
      start = this.tamGenes[index - 1] * index;
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
   * @return Valor del individuo en la funcion
   */
  @Override
  public double getValor() {
    double[] xi = new double[this.dimension];
    double fst = 0;
    double snd = 1;
    for (int i = 1; i < this.dimension + 1; i++) {
      xi[i - 1] = this.getFenotipo(i - 1);
      fst += (Math.pow(xi[i - 1], 2)) / 4000;
      snd *= Math.cos(xi[i - 1] / Math.sqrt(i));
    }
    return (fst - snd + 1);
  }

  /**
   * Copy individuo.
   *
   * @return nuevo individuo con el mismo cromosoma y precision
   */
  @Override
  public Individuo<Boolean> copyIndividuo() {
    return new IndividuoFuncion2(this.chromosome, this.precision, this.dimension);
  }


  /**
   * Comprueba si hay que maximizar o minimizar.
   *
   * @return false
   */
  @Override
  public boolean isMax() {
    return false;
  }

}
