package g02.individuals;

import java.util.Random;

/**
 * Individuo de calibrado.
 */
public class IndividuoFuncion1 extends Individuo<Boolean> {


  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param precision precision de la codificacion
   */
  public IndividuoFuncion1(double precision) {
    this.numFenotipos = 2;
    this.tamGenes = new int[2];
    this.min = new double[2];
    this.max = new double[2];
    this.min[0] = -3.000;
    this.min[1] = 4.100;
    this.max[0] = 12.100;
    this.max[1] = 5.800;
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
   * Instantiates a new individuo funcion 1.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoFuncion1(Boolean[] chromosome, double precision) {
    this.numFenotipos = 2;
    this.tamGenes = new int[2];
    this.min = new double[2];
    this.max = new double[2];
    this.min[0] = -3.000;
    this.min[1] = 4.100;
    this.max[0] = 12.100;
    this.max[1] = 5.800;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);

    this.tamTotal = this.tamGenes[0] + this.tamGenes[1];
    this.chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = chromosome[i];
    }
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
   * @return el cromosoma
   */
  @Override
  public Boolean[] getCromosoma() {
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
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
    double x1 = this.getFenotipo(0);
    double x2 = this.getFenotipo(1);
    return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<Boolean> copyIndividuo() {
    return new IndividuoFuncion1(this.chromosome, this.precision);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return true;
  }


}
