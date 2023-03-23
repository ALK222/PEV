package g02.cruces;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CruceOXPP implements Cruces<Integer> {

  @Override
  public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
      throws Exception {
    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();

    if (ThreadLocalRandom.current().nextDouble() < prob) {
    	
    	Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
        
        Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];
        
    	int puntos = i1.getCromosoma().length / 3;
    	int maxPunto = 0;
    	for(int i = 0; i < puntos; ++i) {
    		int punto = ThreadLocalRandom.current().nextInt(1, i1.getCromosoma().length - 1);
    		if(punto > maxPunto) maxPunto = punto;
    		cromosoma1[punto] = Integer.valueOf(i1.getCromosoma()[punto]);
            cromosoma2[punto] = Integer.valueOf(i2.getCromosoma()[punto]);
    	}
    	
    	int it1 = maxPunto + 1;
    	int it2 = maxPunto + 1;
    	int insertions1 = maxPunto + 1;
    	int insertions2 = maxPunto + 1;
    	
    	while(insertions1 != maxPunto || insertions2 != maxPunto) {
    		if(!(cromosoma1[insertions1]!= null)) {
    			boolean encontrado1 = false;
    			 int j = 0;
     	        while(!encontrado1 && j < cromosoma1.length) {
     	          if (i2.getCromosoma()[it1].equals(cromosoma1[j])) {
     	            encontrado1 = true;
     	          }
     	          ++j;
     	        }
     	       if (!encontrado1) {
     	          cromosoma1[insertions1] = Integer.valueOf(i2.getCromosoma()[it1]);
     	          ++insertions1;
     	          if(insertions1 >= cromosoma2.length)
     	        	  insertions1 = 0;
     	        }
     	      ++it1;
    	        if(it1 >= i1.getCromosoma().length)
    	        {
    	          it1 = 0;
    	        }
    		}
    		
    		if(!(cromosoma2[insertions2] != null)) {
    			boolean encontrado2 = false;
    			int j = 0;
    			while(!encontrado2 && j < cromosoma2.length) {
      	          if (i1.getCromosoma()[it2].equals(cromosoma2[j])) {
      	            encontrado2 = true;
      	          }
      	          ++j;
      	        }
      	       
      	        if (!encontrado2) {
      	          cromosoma2[insertions2] = Integer.valueOf(i1.getCromosoma()[it2]);
      	          ++insertions2;
      	          if(insertions2 >= cromosoma2.length)
      	        	  insertions2 = 0;
      	        }
      	      ++it2;
    	        if(it2 >= i1.getCromosoma().length)
    	        {
    	          it2 = 0;
    	        }
    		}
    		
    		if(cromosoma1[insertions1]!= null){
    			if(insertions1 != maxPunto) {
    				++insertions1;
    			}
    		}
    		if(cromosoma2[insertions2]!= null){
    			if(insertions2 != maxPunto) {
    				++insertions2;
    			}
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
