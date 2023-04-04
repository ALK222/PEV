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

    if (ThreadLocalRandom.current().nextDouble() < prob) {
      int punto1 = ThreadLocalRandom.current().nextInt(1, i1.getCromosoma().length);
      int punto2 = ThreadLocalRandom.current().nextInt(1, i2.getCromosoma().length);
      
      while(punto2 == punto1)
    	  punto2 = ThreadLocalRandom.current().nextInt(1, i2.getCromosoma().length);
      

      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);

      
      Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
      
      Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];

      for (int i = ini; i < fin; ++i) {
        cromosoma1[i] = Integer.valueOf(i1.getCromosoma()[i]);
        cromosoma2[i] = Integer.valueOf(i2.getCromosoma()[i]);
      }

      int i = fin;
      int insertions1 = fin;
      int insertions2 = fin;

      while (insertions1 != ini || insertions2 != ini) {
        boolean encontrado1 = false;
        boolean encontrado2 = false;
        int j = 0;
        while(!encontrado1 && j < cromosoma1.length) {
          if (i2.getCromosoma()[i].equals(cromosoma1[j])) {
            encontrado1 = true;
          }
          ++j;
        }
        j = 0;
        while(!encontrado2 && j < cromosoma2.length) {
          if (i1.getCromosoma()[i].equals(cromosoma2[j])) {
            encontrado2 = true;
          }
          ++j;
        }
        if (!encontrado1) {
          cromosoma1[insertions1] = Integer.valueOf(i2.getCromosoma()[i]);
          ++insertions1;
          if(insertions1 >= cromosoma2.length)
        	  insertions1 = 0;
        }
        if (!encontrado2) {
          cromosoma2[insertions2] = Integer.valueOf(i1.getCromosoma()[i]);
          ++insertions2;
          if(insertions2 >= cromosoma2.length)
        	  insertions2 = 0;
        }
        
        ++i;
        if(i >= i1.getCromosoma().length)
        {
          i = 0;
        }
      }
      
      for(int j = 0; j < cromosoma1.length; ++j) {
    	  i1.getCromosoma()[j] = cromosoma1[j];
    	  i2.getCromosoma()[j] = cromosoma2[j];
      }
    }
    
    

    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    return cruzados;
  }

}
