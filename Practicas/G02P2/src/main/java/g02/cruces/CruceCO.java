package g02.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import g02.individuals.Individuo;

public class CruceCO implements Cruces<Integer> {
	
	@Override
	public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
		      throws Exception {
		    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
		    
		    if(ThreadLocalRandom.current().nextDouble() < prob)
		    {
		    	try {
		    		ArrayList<Integer> codificados = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			    	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27));
			    	ArrayList<Integer> cromosoma1 = new ArrayList<Integer>();
			    	int end = codificados.size();
			    	for(int i = 0; i < end; ++i) {
			    		cromosoma1.add(codificados.indexOf(i1.getCromosoma()[i]));
			    		codificados.remove(codificados.indexOf(i1.getCromosoma()[i]));
			    	}
			    	
			    	codificados = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			    	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27));
			    	ArrayList<Integer> cromosoma2 = new ArrayList<Integer>();
			    	for(int i = 0; i < end; ++i) {
			    		cromosoma2.add(codificados.indexOf(i2.getCromosoma()[i]));
			    		codificados.remove(codificados.indexOf(i2.getCromosoma()[i]));
			    	}
			    	int punto = ThreadLocalRandom.current().nextInt(cromosoma1.size() - 1) + 1;
			    	for (int i = punto; i < cromosoma1.size(); i++) {
			            int aux = cromosoma1.get(i);
			            cromosoma1.toArray()[i] = cromosoma2.get(i);
			            cromosoma2.toArray()[i] = aux;
			          }
			    	
			    	codificados = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			    	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27));
			    	for(int i = 0; i < end; ++i) {
			    		i1.getCromosoma()[i] = codificados.get(cromosoma1.get(i));
			    		codificados.remove(i1.getCromosoma()[i]);
			    	}
			    	
			    	codificados = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			    	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27));
			    	for(int i = 0; i < end; ++i) {
			    		i2.getCromosoma()[i] = codificados.get(cromosoma2.get(i));
			    		codificados.remove(i2.getCromosoma()[i]);
			    	}
		    	}catch(Exception e) {
		    		System.out.println(e);
		    	}
		    }
		    
		    
		    cruzados.add(i1.copyIndividuo());
		    cruzados.add(i2.copyIndividuo());
		    
		    return cruzados;
	}

}
