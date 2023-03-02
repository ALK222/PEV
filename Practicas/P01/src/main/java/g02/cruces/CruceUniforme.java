package g02.cruces;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class CruceUniforme<T> implements Cruces<T> {

  @Override
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob)
      throws Exception {

    Random r = new Random();

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

    res.add(i1);
    res.add(i2);

    return res;
  }


}
