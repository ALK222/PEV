package g02.cruces;

import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cruce uniforme de dos individuos.
 *
 * @param <T> puede ser  Boolean o Double
 */
public class CruceUniforme<T> implements Cruces<T> {

  /**
   * Cruza dos individuos por el metodo uniforme.
   *
   * @param i1 primer individuo
   * @param i2 segundo individuo
   * @param prob probabilidad de cruce
   * @return individuos 1 y 2 cruzados
   * @throws Exception the exception
   */
  @Override
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob)
      throws Exception {

    Random r = new Random(System.currentTimeMillis());

    if (r.nextDouble() < prob) {
      for (int i = 0; i < i1.getCromosoma().length; i++) {
        if (r.nextDouble() > 0.5) {
          T aux = i1.getCromosoma()[i];
          i1.getCromosoma()[i] = i2.getCromosoma()[i];
          i2.getCromosoma()[i] = aux;
        }
      }
    }

    ArrayList<Individuo<T>> res = new ArrayList<Individuo<T>>();

    res.add(i1.copyIndividuo());
    res.add(i2.copyIndividuo());

    return res;
  }


}
