package g02.cruces;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CrucePMX<T> implements Cruces<T> {

  @Override
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, double prob)
      throws Exception {
    ArrayList<Individuo<T>> cruzados = new ArrayList<Individuo<T>>();
    
    if(ThreadLocalRandom.current().nextDouble() < prob)
    {
      int punto1 = ThreadLocalRandom.current().nextInt(0, i1.getCromosoma().length);
      int punto2 = ThreadLocalRandom.current().nextInt(0, i2.getCromosoma().length);
      
      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);
      
      for(int i = ini; i <= fin; i++) {
        T aux = i1.getCromosoma()[i];
        i1.getCromosoma()[i] = i2.getCromosoma()[i];
        i2.getCromosoma()[i] = aux;
      }
      
      if(ini == 0) {
        i1.getCromosoma()[i1.getCromosoma().length - 1] = i1.getCromosoma()[0];
        i2.getCromosoma()[i2.getCromosoma().length - 1] = i2.getCromosoma()[0];
      }
    }
    
    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    
    return cruzados;
  }

}
