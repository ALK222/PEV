package g02.individuals;

import java.util.Random;

public class IndividuoFuncion4a extends Individuo<Boolean> {

  private int _dimension;

  public IndividuoFuncion4a(double precision, int dimensiones) {
    this.numFenotipos = dimensiones;
    this.tamGenes = new int[dimensiones];
    this.min = new double[dimensiones];
    this.max = new double[dimensiones];

    for (int i = 0; i < dimensiones; ++i) {
      min[i] = 0;
      max[i] = Math.PI;
    }
    this._dimension = dimensiones;
    this.precision = precision;

    for (int i = 0; i < _dimension; ++i) {
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
    }

    tamTotal = 0;

    for (int i = 0; i < _dimension; ++i) {
      tamTotal += tamGenes[i];
    }


    this.chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = this.rand.nextBoolean();
    }

  }

  public IndividuoFuncion4a(Boolean chromosome[], double precision, int dimensiones) {
    this.numFenotipos = dimensiones;
    this.tamGenes = new int[dimensiones];
    this.min = new double[dimensiones];
    this.max = new double[dimensiones];

    for (int i = 0; i < dimensiones; ++i) {
      min[i] = 0;
      max[i] = Math.PI;
    }
    this._dimension = dimensiones;
    this.precision = precision;

    for (int i = 0; i < _dimension; ++i) {
      this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
    }

    int tamTotal = 0;

    for (int i = 0; i < _dimension; ++i) {
      tamTotal += tamGenes[i];
    }
    this.chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = chromosome[i];
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

      chromosome[pos] = !chromosome[pos];
    }

    return this;
  }

  @Override
  public Boolean[] getCromosoma() {
    return this.chromosome;
  }

  @Override
  public double getFenotipo(int index) {
    int start, end;

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
      if (this.chromosome[i])
        res += Math.pow(2, power);

      power++;
    }

    return this.min[index]
        + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index]) - 1));
  }

  @Override
  public double getValor() {
    double sum = 0;

    int M = 10;

    for (int i = 0; i < _dimension; i++) {
      double x = this.getFenotipo(i);
      sum += Math.sin(x) * Math.pow(Math.sin((i + 1) * Math.pow(x, 2) / Math.PI), 2 * M);
    }

    return -sum;
  }

  @Override
  public Individuo<Boolean> copyIndividuo() {
    return new IndividuoFuncion4a(this.chromosome, this.precision, this._dimension);
  }

  @Override
  public boolean isMax() {
    return false;
  }

}
