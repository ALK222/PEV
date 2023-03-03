package g02.cruces;

import java.util.ArrayList;
import g02.individuals.Individuo;

public interface Cruces<T> {

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
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob) throws Exception;


}
