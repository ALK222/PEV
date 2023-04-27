package g02.cruces;

import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.Random;


/**
 * Cruce aritmetico para individuos codificados en Double.
 */
public class CruceAritmetico implements Cruces<Double> {

  /**
   * Cruzar.
   *
   * @param i1 primer individuo
   * @param i2 segundo individuo
   * @param prob probabilidad de cruce
   * @return individuos 1 y 2 cruzados
   * @throws Exception the exception
   */
  public ArrayList<Individuo<Double>> cruzar(Individuo<Double> i1, Individuo<Double> i2,
      double prob) throws Exception {

    Random r = new Random();

    if (r.nextDouble() < prob) {
      for (int i = 0; i < i1.getCromosoma().length; i++) {
        double aux1 = 0;
        double aux2 = 0;
        if (r.nextDouble() > 0.5) {
          aux1 = i1.getFenotipo(i) * 0.6;
          aux2 = i1.getFenotipo(i) * 0.4;
          aux1 += i2.getFenotipo(i) * 0.4;
          aux2 += i2.getFenotipo(i) * 0.6;
          i1.getCromosoma()[i] = aux1;
          i2.getCromosoma()[i] = aux2;
        }
      }
    }

    ArrayList<Individuo<Double>> res = new ArrayList<Individuo<Double>>();

    res.add(i1.copyIndividuo());
    res.add(i2.copyIndividuo());

    return res;
  }

}
