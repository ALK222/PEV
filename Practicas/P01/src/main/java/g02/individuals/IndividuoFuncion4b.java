package g02.individuals;

import java.util.Random;

public class IndividuoFuncion4b extends Individuo<Double> {

  public IndividuoFuncion4b(double precision, int dim) {
    this.precision = precision;
    this.tamTotal = dim;
    
    this._chromosome = new Double[this.tamTotal];
    for (int i = 0; i < this.tamTotal; i++) {
      this._chromosome[i] = this.rand.nextDouble() * Math.PI;
    }
  }
  
  public IndividuoFuncion4b(Double[] chromosome, double precision, int dim) {
	    this.precision = precision;
	    this.tamTotal = dim;
	    
	    this._chromosome = new Double[this.tamTotal];
	    for (int i = 0; i < tamTotal; i++) {
	        this._chromosome[i] = chromosome[i];
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

	      _chromosome[pos] = r.nextDouble() * Math.PI;
	    }
    return this;
  }

  @Override
  public Double[] getCromosoma() {
	  return this._chromosome;
  }

  @Override
  public double getFenotipo(int index) {
    return _chromosome[index];
  }

  @Override
  public double getValor() {
	  double sum = 0;
	  int M = 10;

	    for (int i = 0; i < this.tamTotal; ++i) {
	      sum += Math.sin(this._chromosome[i]) * Math.pow(Math.sin(i * Math.pow(this._chromosome[i], 2) / Math.PI), 2 * M);
	    }
    return -sum;
  }

  @Override
  public Individuo<Double> copyIndividuo() {
	  return new IndividuoFuncion4b(this._chromosome, this.precision, this.tamTotal);
  }

  @Override
  public boolean isMax() {
    return false;
  }

}
