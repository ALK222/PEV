package g02.cruces;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import g02.individuals.Individuo;

public class CruceCX implements Cruces<Integer> {

  @Override
  public ArrayList<Individuo<Integer>> cruzar(Individuo<Integer> i1, Individuo<Integer> i2, double prob)
      throws Exception {
    ArrayList<Individuo<Integer>> cruzados = new ArrayList<Individuo<Integer>>();
    
    if(ThreadLocalRandom.current().nextDouble() < prob)
    {
        Integer[] cromosoma1 = new Integer[i1.getCromosoma().length];
        
        Integer[] cromosoma2 = new Integer[i1.getCromosoma().length];
        
        Integer[] aux = new Integer[i1.getCromosoma().length];
        
        boolean finCiclo = false;
        int pos = 0;
        
        this.intentaInsertar(i1, i2, cromosoma1, 0, false);
        this.intentaInsertar(i1, i2, cromosoma2, 0, true);
        
        for(int j = 0; j < cromosoma1.length; ++j) {
        	aux[j] = i1.getCromosoma()[j];
        	if(cromosoma1[j] != null)
        		i1.getCromosoma()[j] = cromosoma1[j];
        	else {
        		i1.getCromosoma()[j] = i2.getCromosoma()[j];
        	}
        	
        	if(cromosoma2[j] != null)
        		i2.getCromosoma()[j] = cromosoma2[j];
        	else {
        		i2.getCromosoma()[j] = aux[j];
        	}
          }
        
    }
    
    cruzados.add(i1.copyIndividuo());
    cruzados.add(i2.copyIndividuo());
    
    return cruzados;
  }
  
  private boolean intentaInsertar(Individuo<Integer> i1, Individuo<Integer> i2, Integer[] cromosoma, int pos, boolean par) {
	  if(!par) {
		  if(cromosoma[pos] != i1.getCromosoma()[pos]) {
			  cromosoma[pos] = i1.getCromosoma()[pos];
		  }
		  else
			  return false;
	  }
	  else {
		  if(cromosoma[pos] != i2.getCromosoma()[pos]) {
			  cromosoma[pos] = i2.getCromosoma()[pos];
		  }
		  else
			  return false;
	  }
	  
	  if(!par) {
		  boolean encontrado = false;
		  int i = 0;
		  while(!encontrado && i < cromosoma.length) {
			  if (i2.getCromosoma()[pos].equals(i1.getCromosoma()[i]))
		            encontrado = true;
			  else
		          ++i;
		  }
		  if(encontrado) {
			  this.intentaInsertar(i1, i2, cromosoma, i, par);
		  }
	  }
	  else {
		  boolean encontrado = false;
		  int i = 0;
		  while(!encontrado && i < cromosoma.length) {
			  if (i1.getCromosoma()[pos].equals(i2.getCromosoma()[i]))
		            encontrado = true;
			  else
		          ++i;
		  }
		  if(encontrado) {
			  this.intentaInsertar(i1, i2, cromosoma, i, par);
		  }
	  }
	  
	  return true;
  }

}
