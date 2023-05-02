package g02.cruces;

import g02.individuals.Arbol;
import g02.individuals.Cromosoma;
import g02.individuals.Individuo;
import g02.individuals.IndividuoPractica3;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cruce uniforme de dos individuos.
 *
 * @param <T> puede ser Boolean o Double
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
  public ArrayList<Individuo<Cromosoma>> cruzar(Individuo<Cromosoma> i1, Individuo<Cromosoma> i2,
      double prob) throws Exception {

    ArrayList<Individuo<Cromosoma>> res = new ArrayList<Individuo<Cromosoma>>();

    if (ThreadLocalRandom.current().nextDouble() < prob) {

      ArrayList<Arbol> nodos1 = new ArrayList<Arbol>();
      ArrayList<Arbol> nodos2 = new ArrayList<Arbol>();

      nodos1 = obtieneNodos(new Arbol(i1.getCromosoma().getArbol()));
      nodos2 = obtieneNodos(new Arbol(i2.getCromosoma().getArbol()));

      int punto1 = ThreadLocalRandom.current().nextInt(nodos1.size() - 1) + 1;
      int punto2 = ThreadLocalRandom.current().nextInt(nodos2.size() - 1) + 1;

      Arbol aux1 = new Arbol(nodos1.get(punto1));
      Arbol aux2 = new Arbol(nodos2.get(punto2));

      corte(i1, aux2, punto1, aux1.getEsRaiz());
      corte(i2, aux1, punto2, aux2.getEsRaiz());
      int nuevosNodos1 = i1.getCromosoma().getArbol().getNodos(i1.getCromosoma().getArbol(), 0);
      int nuevosNodos2 = i1.getCromosoma().getArbol().getNodos(i2.getCromosoma().getArbol(), 0);

      i1.getCromosoma().getArbol().setNumNodos(nuevosNodos1);
      i2.getCromosoma().getArbol().setNumNodos(nuevosNodos2);

    }

    res.add(i1);
    res.add(i2);

    return res;
  }

  public void corte(Individuo<Cromosoma> i, Arbol aux, int punto, boolean esRaiz) {
    i.getCromosoma().getArbol().at(punto).substitute(aux);
  }

  public ArrayList<Arbol> obtieneNodos(Arbol arbol) {
    ArrayList<Arbol> aux = new ArrayList<Arbol>();
    arbol.getFunciones(arbol.getHijos(), aux);

    if (aux.size() == 0) {
      arbol.getTerminales(arbol.getHijos(), aux);
    }
    return aux;
  }
}
