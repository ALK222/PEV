package g02.cruces;

import g02.individuals.Arbol;
import g02.individuals.Cromosoma;
import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cruce uniforme de dos individuos.
 *
 * @param <T> puede ser  Boolean o Double
 */
public class CruceP3 implements Cruces<Cromosoma> {

  /**
   * Cruza dos individuos por el metodo uniforme.
   *
   * @param i1 primer individuo
   * @param i2 segundo individuo
   * @param prob probabilidad de cruce
   * @return individuos 1 y 2 cruzados
   * @throws Exception the exception
   */
  @Override
  public ArrayList<Individuo<Cromosoma>> cruzar(Individuo<Cromosoma> i1, Individuo<Cromosoma> i2, double prob)
      throws Exception {
    if (ThreadLocalRandom.current().nextDouble() < prob) {
      Arbol aux = null;
      int arbol1 = 0;
      while(aux == null) {
    	  arbol1 = ThreadLocalRandom.current().nextInt(i1.getCromosoma().getArbol().toArray().size() - 1);
          aux = i1.getCromosoma().getArbol().at(arbol1);
      }
      int arbol2 =  ThreadLocalRandom.current().nextInt(i2.getCromosoma().getArbol().toArray().size() - 1);
      while(i2.getCromosoma().getArbol().at(arbol2) == null) {
    	  arbol2 = ThreadLocalRandom.current().nextInt(i2.getCromosoma().getArbol().toArray().size() - 1);
      }
      
      i1.getCromosoma().getArbol().at(arbol1).insert(i2.getCromosoma().getArbol().at(arbol2), -1);
      i2.getCromosoma().getArbol().at(arbol2).insert(aux, -1);
    }

    ArrayList<Individuo<Cromosoma>> res = new ArrayList<Individuo<Cromosoma>>();

    res.add(i1.copyIndividuo());
    res.add(i2.copyIndividuo());

    return res;
  }


}