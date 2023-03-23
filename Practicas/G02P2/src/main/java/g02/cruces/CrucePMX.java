package g02.cruces;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CrucePMX implements Cruces<Integer> {

  @Override
  public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
      throws Exception {
    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
    
    if(ThreadLocalRandom.current().nextDouble() < prob)
    {
    	int punto1 = ThreadLocalRandom.current().nextInt(1, i1.getCromosoma().length);
        int punto2 = ThreadLocalRandom.current().nextInt(1, i2.getCromosoma().length);
        
        while(punto2 == punto1)
      	  punto2 = ThreadLocalRandom.current().nextInt(1, i2.getCromosoma().length);
        

        int ini = Math.min(punto1, punto2);
        int fin = Math.max(punto1, punto2);

        
        Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
        
        Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];

        for (int i = ini; i < fin; ++i) {
          cromosoma1[i] = Integer.valueOf(i1.getCromosoma()[i]);
          cromosoma2[i] = Integer.valueOf(i2.getCromosoma()[i]);
        }
        int i = fin;
        int insertions1 = fin;
        
        while(i != ini) {
        	this.intentaInsertar(i1, i2, cromosoma1, ini, fin, i, i2.getCromosoma()[i], false);
        	this.intentaInsertar(i1, i2, cromosoma2, ini, fin, i, i1.getCromosoma()[i], true);
        	
        	++i;
            if(i >= i1.getCromosoma().length)
            {
              i = 0;
            }
        }
        
    }
    
    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    
    return cruzados;
  }
  
  private boolean intentaInsertar(Individuo<Integer> i1, Individuo<Integer> i2, Integer[] cromosoma, int ini, int fin, int pos, int toIns, boolean par) {
	  boolean encontrado = false;
	  int i = ini;
	  while(!encontrado && i < fin) {
		  if (Integer.valueOf(toIns).equals(cromosoma[i]))
	            encontrado = true;
		  else
	          ++i;
	  }
	  if(!encontrado) {
		  cromosoma[pos] = toIns;
	  }
	  else {
		  if(par) {
			  this.intentaInsertar(i1, i2, cromosoma, ini, fin, pos, i1.getCromosoma()[i], par);
		  }
		  else {
			  this.intentaInsertar(i1, i2, cromosoma, ini, fin, pos, i2.getCromosoma()[i], par);
		  }
		  
	  }
	  return true;
  }

}
