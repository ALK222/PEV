package g02.cruces;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CruceOXOP implements Cruces<Integer> {

  @Override
  public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2,
      double prob) throws Exception {

    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
    if (ThreadLocalRandom.current().nextDouble() < prob) {

      int posCambiadas = (int) Math.round(i1.getCromosoma().length * 0.3); // cambiamos un 30% de
                                                                           // las posiciones
      int[] posiciones = new int[posCambiadas];

      for (int i = 0; i < posCambiadas; ++i) {
        posiciones[i] = ThreadLocalRandom.current().nextInt(i1.getCromosoma().length);
      }

      Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];

      Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];

      for (int i = 0; i < posCambiadas; ++i) {
        cromosoma1[posiciones[i]] = i2.getCromosoma()[posiciones[i]];
        cromosoma2[posiciones[i]] = i1.getCromosoma()[posiciones[i]];
      }

      for (int i = 0; i < i1.getCromosoma().length; ++i) {
        boolean encontrado1 = false;
        boolean encontrado2 = false;
        int j = 0;
        while (!encontrado1 && j < cromosoma1.length) {
          if (i2.getCromosoma()[i].equals(cromosoma1[j])) {
            encontrado1 = true;
          }
          ++j;
        }
        j = 0;
        while (!encontrado2 && j < cromosoma2.length) {
          if (i1.getCromosoma()[i].equals(cromosoma2[j])) {
            encontrado2 = true;
          }
          ++j;
        }
        if (!encontrado1) {
          cromosoma1[i] = Integer.valueOf(i2.getCromosoma()[i]);
        }
        if (!encontrado2) {
          cromosoma2[i] = Integer.valueOf(i1.getCromosoma()[i]);
        }

      }
      for (int j = 0; j < cromosoma1.length; ++j) {
        i1.getCromosoma()[j] = cromosoma1[j];
        i2.getCromosoma()[j] = cromosoma2[j];
      }
    }



    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    return cruzados;


  }

}
