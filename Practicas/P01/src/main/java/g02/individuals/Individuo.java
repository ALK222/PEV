package g02.individuals;


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
   * @return Ambos individuos tras el cruce
   */
  public abstract Individuo<T>[] cruzar(Individuo<T> i1, Individuo<T> i2, double prob);
  
  
  public int tamGen(double ValorError, double min, double max)
  {
    return (int) (Math.log10((max-min)/precision) + 1 / Math.log10(2));
  }
 }
