package g02.individuals;

import java.util.Random;

public class IndividuoFuncion3 extends Individuo<Boolean> {

  private static int DIMENSION = 2;

  public IndividuoFuncion3(double precision) {
    this.tamGenes = new int[DIMENSION];
    this.min = new double[DIMENSION];
    this.max = new double[DIMENSION];
    this.min[0] = this.min[1] = -5;
    this.max[0] = this.max[1] = 5;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);
    int tamTotal = tamGenes[0] + tamGenes[1];
    this._chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this._chromosome[i] = this.rand.nextBoolean();
    }

  }

  public IndividuoFuncion3(Boolean cromosoma[], double precision) {
    this.tamGenes = new int[DIMENSION];
    this.min = new double[DIMENSION];
    this.max = new double[DIMENSION];
    this.min[0] = this.min[1] = -5;
    this.max[0] = this.max[1] = 5;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);
    int tamTotal = tamGenes[0] + tamGenes[1];
    this._chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this._chromosome[i] = cromosoma[i];
    }

  }

  @Override
  public double fitness() {

    return this.getValor();
  }

  @Override
  public Individuo<Boolean> mutar(Individuo<Boolean> individuo, double prob) {
    Random r = new Random();

    if (r.nextDouble() < prob) {
      int pos = r.nextInt() * this.tamTotal;

      _chromosome[pos] = !_chromosome[pos];
    }

    return this;
  }

  @Override
  public Boolean[] getCromosoma() {
    return this._chromosome;
  }

  @Override
  public double getFenotipo(int index) {
    int start, end;

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
      if (this._chromosome[i])
        res += Math.pow(2, power);

      power++;
    }

    return this.min[index]
        + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index]) - 1));
  }

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

  @Override
  public Individuo<Boolean> copyIndividuo() {

    return new IndividuoFuncion3(_chromosome, precision);
  }

  @Override
  public boolean isMax() {
    return false;
  }

}
