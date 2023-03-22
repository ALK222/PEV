package g02.cruces;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CruceOX implements Cruces<Integer> {

  @Override
  public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
      throws Exception {
    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();

    if (ThreadLocalRandom.current().nextDouble() > prob) {
      int punto1 = ThreadLocalRandom.current().nextInt(1, i1.getCromosoma().length);
      int punto2 = ThreadLocalRandom.current().nextInt(1, i2.getCromosoma().length);

      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);

      
      Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
      
      Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];

      for (int i = ini; i <= fin; ++i) {
        cromosoma1[i] = Integer.valueOf(i1.getCromosoma()[i]);
        cromosoma2[i] = Integer.valueOf(i2.getCromosoma()[i]);
      }

      boolean vueltaCompleta = false;

      int i = fin;

      while (vueltaCompleta && i == ini) {
        boolean encontrado1 = false;
        boolean encontrado2 = false;
        for (int j = 0; j < i2.getCromosoma().length; ++j) {
          if (i2.getCromosoma()[i].equals(cromosoma1[i])) {
            encontrado1 = true;
          }
        }
        for (int j = 0; j < i1.getCromosoma().length; ++j) {
          if (i1.getCromosoma()[i].equals(cromosoma2[i])) {
            encontrado2 = true;
          }
        }
        if (!encontrado1) {
          cromosoma1[i] = Integer.valueOf(i2.getCromosoma()[i]);
        }
        if (!encontrado2) {
          cromosoma2[i] = Integer.valueOf(i1.getCromosoma()[i]);
        }
        
        ++i;
        if(i > i1.getCromosoma().length)
        {
          i = 0;
          vueltaCompleta = true;
        }
      }

    }

    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    return cruzados;
  }

}
