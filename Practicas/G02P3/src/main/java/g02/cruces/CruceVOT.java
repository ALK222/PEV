package g02.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import g02.individuals.Individuo;

public class CruceVOT implements Cruces<Integer> {
	
	@Override
	public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
		      throws Exception {
		    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
		    
		    if(ThreadLocalRandom.current().nextDouble() < prob)
		    {
		    	Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
		        
		    	Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];
		        
		        int last = 0;
		        int insertions1 = 0;
		        int insertions2 = 0;
		        boolean encontrado = false;
		        for(int i = 0; i < i1.getCromosoma().length; ++i) {
		        	if(i1.getCromosoma()[i] == i2.getCromosoma()[i]) {
		        		encontrado = true;
		        		cromosoma1[i] = i1.getCromosoma()[i];
		        		++insertions1;
		        		cromosoma2[i] = i1.getCromosoma()[i];
		        		++insertions2;
		        		last = i;
		        }
		        
		        
		        int end = last;
		        ++last;
		        if(last == i1.getCromosoma().length)
	        		last = 0;
		        
		        while(last != end) {
		        	if(!encontrado) {
		        		--last;
		        		encontrado = true;
		        	}
		        	if(!Arrays.asList(cromosoma1).contains(i1.getCromosoma()[last]) && !Arrays.asList(cromosoma1).contains(i2.getCromosoma()[last])
		        			&& !Arrays.asList(cromosoma2).contains(i1.getCromosoma()[last]) && !Arrays.asList(cromosoma2).contains(i2.getCromosoma()[last])) {
		        		if(ThreadLocalRandom.current().nextDouble() < 0.5) {
		        			cromosoma1[last] = i1.getCromosoma()[last];
			        		cromosoma2[last] = i2.getCromosoma()[last];
			        		++insertions1;
			        		++insertions2;
		        		}
		        		else {
		        			cromosoma1[last] = i2.getCromosoma()[last];
			        		cromosoma2[last] = i1.getCromosoma()[last];
			        		++insertions1;
			        		++insertions2;
		        		}
		        	}
		        	else if(!Arrays.asList(cromosoma1).contains(i1.getCromosoma()[last]) && !Arrays.asList(cromosoma2).contains(i2.getCromosoma()[last])) {
		        		cromosoma1[last] = i1.getCromosoma()[last];
		        		cromosoma2[last] = i2.getCromosoma()[last];
		        		++insertions1;
		        		++insertions2;
		        	}
		        	else if(!Arrays.asList(cromosoma1).contains(i2.getCromosoma()[last]) && !Arrays.asList(cromosoma2).contains(i1.getCromosoma()[last])) {
		        		cromosoma1[last] = i2.getCromosoma()[last];
		        		cromosoma2[last] = i1.getCromosoma()[last];
		        		++insertions1;
		        		++insertions2;
		        	}
		        	
		        	++last;
		        	if(last == i1.getCromosoma().length) {
		        		last = 0;
		        	}
		        }
		        
		        int x = 0;
		        int y = 0;
		        while(insertions1 < cromosoma1.length) {
		        	if(cromosoma1[x] != null) ++x;
		        	else {
		        		while(Arrays.asList(cromosoma1).contains(i2.getCromosoma()[y])) {
		        			++y;
		        		}
		        		cromosoma1[x] = i2.getCromosoma()[y];
		        		++insertions1;
		        		++x;
		        	}
		        }
		        
		        x = 0;
		        y = 0;
		        while(insertions2 < cromosoma2.length) {
		        	if(cromosoma2[x] != null) ++x;
		        	else {
		        		while(Arrays.asList(cromosoma2).contains(i1.getCromosoma()[y])) {
		        			++y;
		        		}
		        		cromosoma2[x] = i1.getCromosoma()[y];
		        		++insertions2;
		        		++x;
		        	}
		        }
		        
		        for(int j = 0; j < cromosoma1.length; ++j) {
		      	  i1.getCromosoma()[j] = cromosoma1[j];
		      	  i2.getCromosoma()[j] = cromosoma2[j];
		        }
		       }
		        	
		        
		    	
		    }
		    
		    cruzados.add(i1.copyIndividuo());
		    cruzados.add(i2.copyIndividuo());
		    
		    return cruzados;
	}

}
