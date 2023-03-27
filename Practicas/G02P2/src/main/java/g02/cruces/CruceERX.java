package g02.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import g02.individuals.Individuo;

public class CruceERX implements Cruces<Integer> {
	
	public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
		      throws Exception {
		    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
		    
		    if(ThreadLocalRandom.current().nextDouble() < prob)
		    {
		    	
		    	Map<Integer,HashSet<Integer>> adyacencia = new HashMap<Integer, HashSet<Integer>>();
		    	
		    	for(int i = 0; i < i1.getCromosoma().length; ++i) {
		    		if(i == 0) {
		    			adyacencia.put(i1.getCromosoma()[i], new HashSet<Integer>(Arrays.asList(i1.getCromosoma()[i+1])));
		    		}
		    		else if(i == i1.getCromosoma().length - 1) {
		    			adyacencia.put(i1.getCromosoma()[i], new HashSet<Integer>(Arrays.asList(i1.getCromosoma()[i-1])));
		    		}
		    		else {
		    			adyacencia.put(i1.getCromosoma()[i], new HashSet<Integer>(Arrays.asList(i1.getCromosoma()[i+1],i1.getCromosoma()[i-1])));
		    		}
		    		
		    	}
		    	
		    	for(int i = 0; i < i2.getCromosoma().length; ++i) {
		    		if(i == 0) {
		    			adyacencia.get(i2.getCromosoma()[i]).add(i2.getCromosoma()[i+1]);
		    		}
		    		else if(i == i2.getCromosoma().length - 1) {
		    			adyacencia.get(i2.getCromosoma()[i]).add(i2.getCromosoma()[i-1]);
		    		}
		    		else {
		    			adyacencia.get(i2.getCromosoma()[i]).add(i2.getCromosoma()[i+1]);
	    				adyacencia.get(i2.getCromosoma()[i]).add(i2.getCromosoma()[i-1]);
		    		}
		    		
		    	}
		    	
		    	ArrayList<Integer> cromosoma1 = new ArrayList<Integer>(i1.getCromosoma().length);
		    	
		    	for(int i = 0; i < i1.getCromosoma().length; ++i) {
		    		if(i == 0) {
		    			cromosoma1.add(i2.getCromosoma()[i]);
		    		}
		    		else {
		    			ArrayList<Integer> posibles = new ArrayList<Integer>();
		    			for(int j = 0; j < adyacencia.get(cromosoma1.get(i - 1)).size(); ++j) {
		    				if(!cromosoma1.contains(adyacencia.get(cromosoma1.get(i - 1)).toArray()[j])) {
		    					posibles.add((Integer)adyacencia.get(cromosoma1.get(i - 1)).toArray()[j]);
		    				}
		    			}
		    			int minSize = Integer.MAX_VALUE;
		    			ArrayList<Integer> newPosibles = new ArrayList<Integer>();
		    			for(int j = 0; j < posibles.size(); ++j) {
		    				if(adyacencia.get(posibles.get(j)).size() < minSize) {
		    					newPosibles.clear();
		    					newPosibles.add(posibles.get(j));
		    				}
		    				else if(adyacencia.get(posibles.get(j)).size() == minSize) {
		    					newPosibles.add(posibles.get(j));
		    				}
		    			}
		    			if(!newPosibles.isEmpty()) {
		    				cromosoma1.add(newPosibles.get(ThreadLocalRandom.current().nextInt(0,newPosibles.size())));
		    			}
		    			else {
		    				for(int j = 0; j < i2.getCromosoma().length; ++j) {
		    					if(!cromosoma1.contains(i2.getCromosoma()[j])) {
		    						newPosibles.add(i2.getCromosoma()[j]);
		    					}
		    				}
		    				cromosoma1.add(newPosibles.get(ThreadLocalRandom.current().nextInt(0,newPosibles.size())));
		    			}
		    		}
		    	}
		    	
		    	ArrayList<Integer> cromosoma2 = new ArrayList<Integer>(i1.getCromosoma().length);
		    	
		    	for(int i = 0; i < i1.getCromosoma().length; ++i) {
		    		if(i == 0) {
		    			cromosoma2.add(i1.getCromosoma()[i]);
		    		}
		    		else {
		    			ArrayList<Integer> posibles = new ArrayList<Integer>();
		    			for(int j = 0; j < adyacencia.get(cromosoma2.get(i - 1)).size(); ++j) {
		    				if(!cromosoma2.contains(adyacencia.get(cromosoma2.get(i - 1)).toArray()[j])) {
		    					posibles.add((Integer)adyacencia.get(cromosoma2.get(i - 1)).toArray()[j]);
		    				}
		    			}
		    			int minSize = Integer.MAX_VALUE;
		    			ArrayList<Integer> newPosibles = new ArrayList<Integer>();
		    			for(int j = 0; j < posibles.size(); ++j) {
		    				if(adyacencia.get(posibles.get(j)).size() < minSize) {
		    					newPosibles.clear();
		    					newPosibles.add(posibles.get(j));
		    				}
		    				else if(adyacencia.get(posibles.get(j)).size() == minSize) {
		    					newPosibles.add(posibles.get(j));
		    				}
		    			}
		    			if(!newPosibles.isEmpty()) {
		    				cromosoma2.add(newPosibles.get(ThreadLocalRandom.current().nextInt(0,newPosibles.size())));
		    			}
		    			else {
		    				for(int j = 0; j < i1.getCromosoma().length; ++j) {
		    					if(!cromosoma2.contains(i1.getCromosoma()[j])) {
		    						newPosibles.add(i1.getCromosoma()[j]);
		    					}
		    				}
		    				cromosoma2.add(newPosibles.get(ThreadLocalRandom.current().nextInt(0,newPosibles.size())));
		    			}
		    		}
		    	}
		    	
		    	for(int j = 0; j < cromosoma1.size(); ++j) {
		      	  i1.getCromosoma()[j] = cromosoma1.get(j);
		      	  i2.getCromosoma()[j] = cromosoma2.get(j);
		        }
		    	
		    	
		    }
		    
		    cruzados.add(i1.copyIndividuo());
		    cruzados.add(i2.copyIndividuo());
		    
		    return cruzados;
	}
	
}
