package g02.individuals;

import java.util.Random;

public class IndividuoFuncion1 extends Individuo<Boolean> {
  

  public IndividuoFuncion1(double precision) {
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
    int tamTotal = tamGenes[0] + tamGenes[1];
    this._chromosome = new Boolean[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this._chromosome[i] = this.rand.nextBoolean();
    }

  }

  public IndividuoFuncion1(Boolean chromosome[], double precision) {
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

    int tamTotal = this.tamGenes[0] + this.tamGenes[1];
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
    double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
    return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
  }
  
  @Override
  public Individuo<Boolean> copyIndividuo(){
    return new IndividuoFuncion1(this._chromosome, this.precision);
  }

  @Override
  public boolean isMax() {
    return true;
  }


}
