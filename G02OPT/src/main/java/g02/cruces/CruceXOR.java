package g02.cruces;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import g02.individuals.Individuo;

public class CruceXOR implements Cruces<Boolean[]> {
	
	 @Override
	  public ArrayList<Individuo<Boolean[]>> cruzar(Individuo<Boolean[]> i1, Individuo<Boolean[]> i2, double prob)
	      throws Exception {
		 ArrayList<Individuo<Boolean[]>> res = new ArrayList<Individuo<Boolean[]>>();
		 
		 if (ThreadLocalRandom.current().nextDouble() < prob) {
			 // Toma el padre con menor fitness y cambia su cromosoma al XOR entre ambos
			 if(i1.fitness() > i2.fitness()) {
				 for(int i = 0; i < i1.getCromosoma().length; ++i) {
					 for(int j = 0; j < i1.getCromosoma()[i].length; ++j) {
						 i2.getCromosoma()[i][j] = i1.getCromosoma()[i][j] ^ i2.getCromosoma()[i][j];
					 }
				 }
			 }
			 else {
				 for(int i = 0; i < i1.getCromosoma().length; ++i) {
					 for(int j = 0; j < i1.getCromosoma()[i].length; ++j) {
						 i1.getCromosoma()[i][j] = i1.getCromosoma()[i][j] ^ i2.getCromosoma()[i][j];
					 }
				 }
			 }
		 }
		
		 // El padre con mayor fitness va a seguir
		 res.add(i1.copyIndividuo());
		 res.add(i2.copyIndividuo());
			 
			 
		 return res;
	  }

}
