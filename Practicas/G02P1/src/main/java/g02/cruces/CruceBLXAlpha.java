package g02.cruces;

import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cruce BLX-alpha para individuos codificados en Double.
 */
public class CruceBLXAlpha implements Cruces<Double> {

  /**
   * Cruzar.
   *
   * @param i1 primer individuo
   * @param i2 segundo individuo
   * @param prob probabilidad de cruce
   * @return individuos 1 y 2 cruzados
   * @throws Exception the exception
   */
  @Override
  public ArrayList<Individuo<Double>> cruzar(Individuo<Double> i1, Individuo<Double> i2,
      double prob) throws Exception {
    ArrayList<Individuo<Double>> res = new ArrayList<>();

    Random rand = new Random();

    if (rand.nextDouble() > prob) {
      for (int i = 0; i < i1.getCromosoma().length; ++i) {
        double x1 = i1.getFenotipo(i);
        double x2 = i2.getFenotipo(i);

        double max = Math.max(x2, x1);
        double min = Math.min(x1, x2);

        double I = max - min;

        max += I * Math.random();
        min -= I * Math.random();

        double h1 = ((Math.random() * (max - min)) + min);
        double h2 = ((Math.random() * (max - min)) + min);

        i1.getCromosoma()[i] = h1;
        i2.getCromosoma()[i] = h2;
      }
    }

    res.add(i1.copyIndividuo());
    res.add(i2.copyIndividuo());
    return res;
  }

}
