package g02.cruces;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class CruceMonopunto<T> implements Cruces<T> {

  private int _punto;
  
  
  //TODO Cambiado el parámetro punto de la contructora por uno aleatorio, revisar
  public CruceMonopunto() {
	  
  }

  @Override
  public ArrayList<Individuo<T>> cruzar(Individuo<T> i1, Individuo<T> i2,
      double prob)throws Exception {
//    if (_punto > i1.getCromosoma().length) {
//      throw new Exception();
//    }

    Random r = new Random();
    
    _punto = r.nextInt(i1.getCromosoma().length - 1) + 1;

    if (r.nextDouble() < prob) {
      for (int i = _punto; i < i1.getCromosoma().length; i++) {
        T aux = i1.getCromosoma()[i];
        i1.getCromosoma()[i] = i2.getCromosoma()[i];
        i2.getCromosoma()[i] = aux;
      }
    }

    ArrayList<Individuo<T>> res = new ArrayList<Individuo<T>>();

    res.add(i1.copyIndividuo());
    res.add(i2.copyIndividuo());

    return res;
  }



}
