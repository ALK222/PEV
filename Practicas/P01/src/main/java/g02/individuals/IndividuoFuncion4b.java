package g02.individuals;

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
    // TODO Auto-generated method stub
    return getValor();
  }

  @Override
  public Individuo<Double> mutar(Individuo<Double> individuo, double prob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Double[] getCromosoma() {
	  return this._chromosome;
  }

  @Override
  public double getFenotipo(int index) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getValor() {
    // TODO Auto-generated method stub
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
