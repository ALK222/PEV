package g02.Selections;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class RouletteSelection<T> extends Selection<T> {
	
	double fitnessTotal;

 
  public RouletteSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);
  }

  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> newPob = new ArrayList<Individuo<T>>();

    probs = new double[_pob.size()];

    this.fitnessTotal = 0;
    double auxMax = -600;

    for (int i = 0; i < _pob.size(); ++i) { // Generamos el fitness de cada individuo
      double aux = _pob.get(i).fitness();
      if(aux > auxMax)
    	  auxMax = aux;

      probs[i] = aux;
    }
    
    this.corrigeMinimizar(auxMax);
    
    

    for (int i = 0; i < _pob.size(); ++i) {
    	if(i==0)
    		probs[i] = probs[i] / this.fitnessTotal;
    	else
    		probs[i] = probs[i-1] + (probs[i] / this.fitnessTotal);
    }

    int seleccionados = 0;

    while (seleccionados < _seleccionar) {
      Random r = new Random();
      double randomValue = r.nextDouble();

      boolean encontrado = false;
      int i = 0;
      while(!encontrado && i < _pob.size())
      {
        if(probs[i] > randomValue)
        {
          
          newPob.add(_pob.get(i).copyIndividuo());
          encontrado = true;
          seleccionados++;
        }
        i++;
      }
    }

    return newPob;
  }
  
  public void corrigeMinimizar(double max) {
	  for(int i = 0; i < probs.length; i++) {
		  probs[i] = (1.05 * max) - probs[i];
		  this.fitnessTotal += probs[i];
	  }
  }

}
