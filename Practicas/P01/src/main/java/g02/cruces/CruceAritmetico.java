package g02.cruces;

import java.util.ArrayList;
import java.util.Random;

import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion4b;

public class CruceAritmetico<Double> implements Cruces<Double>{
	
	public ArrayList<Individuo<Double>> cruzar(Individuo<Double> i1, Individuo<Double> i2, double prob)
		      throws Exception {

		    Random r = new Random();

		    if (r.nextDouble() < prob) {
		      for (int i = 0; i < i1.getCromosoma().length; i++) {
		        if (r.nextDouble() > 0.5) {
		          i1.getCromosoma()[i] = aux;
		          i2.getCromosoma()[i] = aux;
		        }
		      }
		    }

		    ArrayList<Individuo<Double>> res = new ArrayList<Individuo<Double>>();

		    res.add(i1);
		    res.add(i2);

		    return res;
		  }

}
