package g02.individuals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IndividuoPractica3 extends Individuo<Cromosoma> {
  
  private double K;


  /**
   * Instantiates a new individuo practica 3
   *
   * @param precision precision de la codificacion
   */
  public IndividuoPractica3(double precision) {
    this.chromosome = new Cromosoma(5, 0, 2);

  }

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoPractica3(Cromosoma chromosome) {
    this.chromosome = new Cromosoma(chromosome.getArbol());

  }
  
  public void setK(double k) {
    this.K = k;
  }


  /**
   * Calcula el fitness del individuo.
   *
   * @return fitness del individuo
   */
  public double fitness() {
    
    double fitness = this.getValor();
    int profundidad = this.getCromosoma().getArbol().getProfundidad();
    
    // Factor de penalización: incremento exponencial con la profundidad del árbol
    double penalizacion = Math.exp(profundidad);
    
    // Reducción del fitness según el factor de penalización
    fitness /= penalizacion;
    
    return fitness;
  }

  /**
   * Muta un individuo.
   *
   * @param individuo Individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<Cromosoma> mutar(Individuo<Cromosoma> individuo, double prob, int mut) {
	  if(ThreadLocalRandom.current().nextDouble() < prob) {
		  switch(mut) {
		  case 0:
			  this.mutacionTerminal(individuo);
			  break;
		  case 1:
			  this.mutacionFuncional(individuo);
			  break;
		  }
	  }

    return this;
  }
  
  public Individuo<Cromosoma> mutacionTerminal(Individuo<Cromosoma> individuo){
	  int ale = ThreadLocalRandom.current().nextInt(this.chromosome.getArbol().toArray().size());
	  while(this.chromosome.getArbol().at(ale).getEsRaiz()) {
		  ale = ThreadLocalRandom.current().nextInt(this.chromosome.getArbol().toArray().size());
	  }
	  this.chromosome.getArbol().at(ale).changeValor(Cromosoma.terminales[ThreadLocalRandom.current().nextInt(Cromosoma.terminales.length)]);
	  return this;
  }
  
  public Individuo<Cromosoma> mutacionFuncional(Individuo<Cromosoma> individuo){
	  int ale = ThreadLocalRandom.current().nextInt(this.chromosome.getArbol().toArray().size());
	  while(!this.chromosome.getArbol().at(ale).getEsRaiz()) {
		  ale = ThreadLocalRandom.current().nextInt(this.chromosome.getArbol().toArray().size());
	  }
	  this.chromosome.getArbol().at(ale).changeValor(Cromosoma.funciones[ThreadLocalRandom.current().nextInt(Cromosoma.funciones.length)]);
	  return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public Cromosoma getCromosoma() {
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
    // int start;
    // int end;
    //
    // if (index == 0) {
    // start = 0;
    // end = this.tamGenes[index] - 1;
    // } else {
    // start = this.tamGenes[index - 1];
    // end = this.tamGenes[index] + this.tamGenes[index - 1] - 1;
    // }
    //
    //
    // double res = 0;
    // int power = 0;
    // for (int i = end; i >= start; i--) {
    // if (this.chromosome[i]) {
    // res += Math.pow(2, power);
    // }
    // power++;
    // }
    //
    // return this.min[index]
    // + (res * (this.max[index] - this.min[index]) / (Math.pow(2, this.tamGenes[index]) - 1));
    return 0;
  }

  /**
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
    double diferencia = 0;
    for (int i = 0; i < 101; ++i) {
      double valorX = (-1.0) + ((2.0 / 100.0) * i);
      double valor1 = Math.pow(valorX, 4) + Math.pow(valorX, 3) + Math.pow(valorX, 2) + valorX + 1;
      double valor2 = this.chromosome.updateFitness(valorX, this.chromosome.getArbol());
      diferencia += Math.pow(valor1 - valor2, 2);
    }
    return Math.sqrt(diferencia / 101);
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<Cromosoma> copyIndividuo() {
    return new IndividuoPractica3(this.chromosome);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return false;
  }
  
  public String toString() {
	  return this.chromosome.getArbol().toString();
  }
}
