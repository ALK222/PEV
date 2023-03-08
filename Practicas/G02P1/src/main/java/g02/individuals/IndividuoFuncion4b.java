package g02.individuals;

import java.util.Random;

public class IndividuoFuncion4b extends Individuo<Double> {

  public IndividuoFuncion4b(double precision, int dim) {
    this.precision = precision;
    this.tamTotal = dim;
    this.numFenotipos = dim;
    
    this.chromosome = new Double[this.tamTotal];
    for (int i = 0; i < this.tamTotal; i++) {
      this.chromosome[i] = this.rand.nextDouble() * Math.PI;
    }
  }
  
  public IndividuoFuncion4b(Double[] chromosome, double precision, int dim) {
	    this.precision = precision;
	    this.tamTotal = dim;
	    
	    this.chromosome = new Double[this.tamTotal];
	    for (int i = 0; i < tamTotal; i++) {
	        this.chromosome[i] = chromosome[i];
	      }
	  }

  @Override
  public double fitness() {
    return getValor();
  }

  @Override
  public Individuo<Double> mutar(Individuo<Double> individuo, double prob) {
	  Random r = new Random();

	    if (r.nextDouble() < prob) {
	      int pos = (int) (r.nextDouble() * this.tamTotal);

	      chromosome[pos] = r.nextDouble() * Math.PI;
	    }
    return this;
  }

  @Override
  public Double[] getCromosoma() {
	  return this.chromosome;
  }

  @Override
  public double getFenotipo(int index) {
    return chromosome[index];
  }

  @Override
  public double getValor() {
	  double sum = 0;
	  int M = 10;

	    for (int i = 0; i < this.tamTotal; ++i) {
	      sum += Math.sin(this.chromosome[i]) * Math.pow(Math.sin((i + 1) * Math.pow(this.chromosome[i], 2) / Math.PI), 2 * M);
	    }
    return -sum;
  }

  @Override
  public Individuo<Double> copyIndividuo() {
	  return new IndividuoFuncion4b(this.chromosome, this.precision, this.tamTotal);
  }

  @Override
  public boolean isMax() {
    return false;
  }

}
