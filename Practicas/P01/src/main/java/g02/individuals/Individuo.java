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

  /**
   * Cruza dos individuos.
   *
   * @param i1 Individuo 1
   * @param i2 Individuo 2
   * @param prob Probabilidad de cruce
   * @param punto Punto de cruce
   * @return Ambos individuos tras el cruce
   * @throws Exception 
   */
  public abstract Individuo<T>[] cruzarMonopunto(Individuo<T> i1, Individuo<T> i2, double prob, int punto) throws Exception;
  
  public abstract double getFenotipo(int index);

  public abstract double getValor();
  
  public int tamGen(double valorError, double min, double max) {
    return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
}
}
