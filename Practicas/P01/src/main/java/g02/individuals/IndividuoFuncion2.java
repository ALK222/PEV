package g02.individuals;

import java.util.Random;

public class IndividuoFuncion2 extends Individuo<Boolean> {
	
	private int dimension;

  public IndividuoFuncion2(double precision, int dim) {
	  this.dimension = dim;
	  this.tamGenes = new int[this.dimension];
	  this.min = new double[this.dimension];
	  this.max = new double[this.dimension];
	  this.precision = precision;
	  int tamTotal = 0;
	  for(int i = 0; i < this.dimension; i++) {
		  this.min[i] = -600.000;
		  this.max[i] = 600.000;
		  this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
		  tamTotal += tamGenes[i];
	  }
  
    this._chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this._chromosome[i] = this.rand.nextBoolean();
    }

  }

  public IndividuoFuncion2(Boolean chromosome[], double precision, int dim) {
	  this.dimension = dim;
	  this.tamGenes = new int[this.dimension];
	  this.min = new double[this.dimension];
	  this.max = new double[this.dimension];
	  this.precision = precision;
	  int tamTotal = 0;
	  for(int i = 0; i < this.dimension; i++) {
		  this.min[i] = -600.000;
		  this.max[i] = 600.000;
		  this.tamGenes[i] = this.tamGen(precision, min[i], max[i]);
		  tamTotal += tamGenes[i];
	  }
    this._chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this._chromosome[i] = chromosome[i];
    }
  }
  

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
    return _chromosome;
  }

  // Obtiene el fenotipo a partir del cromosoma
  // Necesario para calcular el fitness
  @Override
  public double getFenotipo(int index) {
	  int start, end;

	    if (index == 0) {
	    	start = 0;
	    	end = this.tamGenes[index] - 1;
	    }
	    else {
	    	start = this.tamGenes[index - 1];
	    	end = this.tamGenes[index] + this.tamGenes[index -1] - 1;
	    }
	      
	    
	    double res = 0;
	    int power = 0;
	    for (int i = end; i >= start; i--) {
	      if (this._chromosome[i])
	        res += Math.pow(2, power);
	      
	      power++;
	    }
	    
	    return this.min[index]
	        + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index])-1));
  }

  // Calculo del fitness
  @Override
  public double getValor() {
    double[] xi = new double[this.dimension];
    double fst = 0, snd= 1;
    for(int i = 1; i < this.dimension + 1; i++) {
    	xi[i-1] = this.getFenotipo(i-1);
    	fst += (Math.pow(xi[i-1], 2)) / 4000;
    	snd *= Math.cos(xi[i-1]/Math.sqrt(i));
    }
    return (fst - snd + 1);
  }
  
  @Override
  public Individuo<Boolean> copyIndividuo(){
    return new IndividuoFuncion2(this._chromosome, this.precision, this.dimension);
  }
  
  public boolean isMax() {
	  return false;
  }

  @Override
  public boolean isMax() {
    return false;
  }

}
