package g02.individuals;

import java.util.Random;

/**
 * Individuo.
 *
 * @param <T> the generic type
 */
public abstract class Individuo<T> {

  /** Cromosoma del individuo. */
  protected T _chromosome[];

  protected double max[];
  protected double min[];
  protected int tamGenes[];
  protected int tamTotal;
  protected double precision;
  protected int numFenotipos;

  protected Random rand = new Random();

  /**
   * Constructor vacio
   */
  public Individuo() {

  }


  /**
   * Instancia un nuevo individuo.
   * 
   * @param chromosome chromosoma del individuo
   */
  public Individuo(T chromosome[]) {
    _chromosome = chromosome;
  }

  /**
   * Calculo del fitness de un individuo basado en una función.
   * 
   * @return Puntuación de fitness
   */
  public abstract double fitness();


  /**
   * Muta el cromosoma de un individuo dado.
   *
   * @param individuo individuo a mutar
   * @param prob probabilidad de mutar
   * @return individuo mutado
   */
  public abstract Individuo<T> mutar(Individuo<T> individuo, double prob);

  public abstract T[] getCromosoma();

  public abstract double getFenotipo(int index);

  public abstract double getValor();

  public int tamGen(double valorError, double min, double max) {
    return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
  }
  
  public abstract Individuo<T> copyIndividuo();
  
  public int compareTo(Individuo<T> i2) {
    if(this.fitness() > i2.fitness()) return -1;
    if(this.fitness() < i2.fitness()) return 1;
    return 0;
  }
  
  public abstract boolean isMax();
  
  public String toString() {
    String aux = "Cromosoma: ";
    
    for(int i = 0; i < numFenotipos; i++) {
      aux += this.getFenotipo(i) + " ";
    }
    aux += "\n";
    
    aux += "fitness: " + this.fitness();
    
    return aux;
  }
  
}
