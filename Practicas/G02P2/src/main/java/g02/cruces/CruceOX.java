package g02.cruces;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CruceOX<T> implements Cruces<T> {

  @Override
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob)
      throws Exception {
    ArrayList<Individuo<T>> cruzados = new ArrayList<Individuo<T>>();

    if (ThreadLocalRandom.current().nextDouble() > prob) {
      int punto1 = ThreadLocalRandom.current().nextInt(0, i1.getCromosoma().length);
      int punto2 = ThreadLocalRandom.current().nextInt(0, i2.getCromosoma().length);

      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);

      @SuppressWarnings("unchecked")
      T[] cromosoma1 =
          (T[]) Array.newInstance(i1.getCromosoma().getClass().getComponentType(), i1.getCromosoma().length);
      @SuppressWarnings("unchecked")
      T[] cromosoma2 =
          (T[]) Array.newInstance(i1.getCromosoma().getClass().getComponentType(), i1.getCromosoma().length);

      for (int i = ini; i <= fin; ++i) {
        cromosoma1[i] = i1.getCromosoma()[i];
        cromosoma2[i] = i2.getCromosoma()[i];
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
          cromosoma1[i] = i2.getCromosoma()[i];
        }
        if (!encontrado2) {
          cromosoma2[i] = i1.getCromosoma()[i];
        }
        
        ++i;
        if(i > i1.getCromosoma().length)
        {
          i = 0;
          vueltaCompleta = true;
        }
      }



      if (ini == 0) {
        i1.getCromosoma()[i1.getCromosoma().length - 1] = i1.getCromosoma()[0];
        i2.getCromosoma()[i2.getCromosoma().length - 1] = i2.getCromosoma()[0];
      }
    }

    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    return cruzados;
  }

}
