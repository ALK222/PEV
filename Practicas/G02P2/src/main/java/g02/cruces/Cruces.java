package g02.cruces;

import g02.individuals.Individuo;
import java.util.ArrayList;


/**
 * Interfaz para implementar el cruce de individuos.
 *
 * @param <T> puede ser Boolean o Double
 */
public interface Cruces<T> {

  /**
   * Cruza dos individuos.
   *
   * @param i1 primer individuo
   * @param i2 segundo individuo
   * @param prob probabilidad de cruce
   * @return individuos 1 y 2 cruzados
   * @throws Exception the exception
   */
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob) throws Exception;


}
