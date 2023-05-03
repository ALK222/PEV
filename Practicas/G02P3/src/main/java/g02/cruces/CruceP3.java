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

	private int nHijos;
	
	public CruceP3(int n) {
		this.nHijos = n;
	}
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
    	double sumaFitness = i1.fitness() + i2.fitness();

    	// Solo se queda con los hijos que mejoren el fitness
    	ArrayList<Arbol> nodos1 = new ArrayList<Arbol>();
	      ArrayList<Arbol> nodos2 = new ArrayList<Arbol>();

	      nodos1 = obtieneNodos(new Arbol(i1.getCromosoma().getArbol()));
	      nodos2 = obtieneNodos(new Arbol(i2.getCromosoma().getArbol()));

	      int punto1 = ThreadLocalRandom.current().nextInt(nodos1.size() - 1) + 1;
	      int punto2 = ThreadLocalRandom.current().nextInt(nodos2.size() - 1) + 1;
	      
	      ArrayList<Individuo<Cromosoma>> hijos = new ArrayList<Individuo<Cromosoma>>();
	      for(int i = 0; i < nHijos; ++i) {
	    	  Arbol nuevo1 = new Arbol(i1.getCromosoma().getArbol());
	    	  Arbol nuevo2 = new Arbol(i2.getCromosoma().getArbol());
	    	  
	    	  Arbol aux1 = new Arbol(nodos1.get(punto1));
		      Arbol aux2 = new Arbol(nodos2.get(punto2));

		      corte(nuevo1, aux2, punto1, aux1.getEsRaiz());
		      corte(nuevo2, aux1, punto2, aux2.getEsRaiz());
		      int nuevosNodos1 = nuevo1.getNodos(nuevo1, 0);
		      int nuevosNodos2 = nuevo2.getNodos(nuevo2, 0);

		      nuevo1.setNumNodos(nuevosNodos1);
		      nuevo2.setNumNodos(nuevosNodos2);
		      
		      IndividuoPractica3 a = new IndividuoPractica3(new Cromosoma(nuevo1));
		      IndividuoPractica3 b = new IndividuoPractica3(new Cromosoma(nuevo2));
		      hijos.add(a);
		      hijos.add(b);
	      }
	      
	      hijos.sort((o1, o2) -> (o2.compareTo(o1)));
	      res.add(hijos.get(0));
	      res.add(hijos.get(1));

	      return res;

    }

    res.add(i1);
    res.add(i2);

    return res;
  }

  public void corte(Arbol i, Arbol aux, int punto, boolean esRaiz) {
    i.at(punto).substitute(aux);
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
