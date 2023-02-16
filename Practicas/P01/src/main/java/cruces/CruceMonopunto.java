package cruces;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class CruceMonopunto implements Cruces {

  @Override
  public <T> ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2, int punto, double prob) throws Exception {
    if (punto > i1.getCromosoma().length) {
      throw new Exception();
    }
    
    Random r = new Random();
    
    if(r.nextDouble() > prob)
    {
      for (int i = punto; i < i1.getCromosoma().length; i++) {
        T aux = i1.getCromosoma()[i];
        i1.getCromosoma()[i] = i2.getCromosoma()[i];
        i2.getCromosoma()[i] = aux;
      }
    }
    
    ArrayList<Individuo<T>> res = new ArrayList<Individuo<T>>();
    
    res.add(i1);
    res.add(i2);

    return res;
  }



}
