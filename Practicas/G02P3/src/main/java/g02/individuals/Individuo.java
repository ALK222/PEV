package g02.individuals;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * Individuo.
 *
 * @param <T> the generic type
 */
public abstract class Individuo<T> {

  /** Cromosoma del individuo. */
  protected T chromosome;

  /** Valor máximo de cada gen. */
  protected double[] max;
  
  /**Valor minimo de cada gen. */
  protected double[] min;
  
  /** Tamaño de cada gen. */
  protected int[] tamGenes;
  
  /** Tamaño total del cromosoma. */
  protected int tamTotal;
  
  /** Precision de la codificacion. */
  protected double precision;
  
  /** Numero total de fenotipos. */
  protected int numFenotipos;

  /** The rand. */
  protected Random rand = new Random();

  /**
   * Constructor vacio.
   */
  public Individuo() {

  }


  
  /**
   * Instantiates a new individuo.
   *
   * @param chromosome cromosoma dado
   */
  public Individuo(T chromosome) {
    this.chromosome = chromosome;
  }

  
  /**
   * Calcula el fitness de un cromosoma.
   *
   * @return fitness de dicho cromosoma
   */
  public abstract double fitness();


  /**
   * Muta el cromosoma de un individuo dado.
   *
   * @param individuo individuo a mutar
   * @param prob probabilidad de mutar
   * @return individuo mutado
   */
  public abstract Individuo<T> mutar(Individuo<T> individuo, double prob, int mut);

  /**
   * Gets the cromosoma.
   *
   * @return the cromosoma
   */
  public abstract T getCromosoma();

  /**
   * Gets the fenotipo.
   *
   * @param index the index
   * @return the fenotipo
   */
  public abstract double getFenotipo(int index);

  /**
   * Gets the valor.
   *
   * @return the valor
   */
  public abstract double getValor();

  /**
   * Tam gen.
   *
   * @param valorError precision
   * @param min the min
   * @param max the max
   * @return codificacion de un fenotipo
   */
  public int tamGen(double valorError, double min, double max) {
    return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
  }

  /**
   * Copy individuo.
   *
   * @return nuevo individuo con el mismo cromosoma y precision
   */
  public abstract Individuo<T> copyIndividuo();

  /**
   * Compare to.
   *
   * @param i2 otro individuo
   * @return 0 si son iguales, -1 si i1 tiene un fitness mayor que i2, 1 en caso contrario
   */
  public int compareTo(Individuo<T> i2) {
    if (this.fitness() > i2.fitness()) {
      return -1;
    }
    if (this.fitness() < i2.fitness()) {
      return 1;
    }
    return 0;
  }

  /**
   * Comprueba si la funcion es de maximos o de minimos.
   *
   * @return true sie s de maximizar
   */
  public abstract boolean isMax();

  /**
   * To string.
   *
   * @return the string
   */
  public String toString() {
    String aux = "Cromosoma: ";

    for (int i = 0; i < numFenotipos; i++) {
      aux += this.getFenotipo(i) + " ";
    }
    aux += "\n";

    aux += "fitness: " + this.fitness();

    return aux;
  }
  
  public void corregir() {
    return;
  }

}
