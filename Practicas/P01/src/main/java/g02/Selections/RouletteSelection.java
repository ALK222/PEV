package g02.Selections;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class RouletteSelection<T> extends Selection<T> {

 
  public RouletteSelection(int s, Individuo<T>[] pob) {
    super(s, pob);
  }

  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> newPob = new ArrayList<Individuo<T>>();

    double probs[] = new double[_pob.length];

    int fitnessTotal = 0;

    for (int i = 0; i < _pob.length; ++i) { // Generamos el fitness de cada individuo
      double aux = _pob[i].fitness();
      fitnessTotal += aux;
      probs[i] = aux;
    }

    for (int i = 0; i < _pob.length; ++i) {
      probs[i] = probs[i] / fitnessTotal;
    }

    int seleccionados = 0;

    while (seleccionados < _seleccionar) {
      Random r = new Random();
      double randomValue = r.nextDouble();

      boolean encontrado = false;
      int i = 0;
      while(!encontrado)
      {
        if(probs[i] < randomValue)
        {
          newPob.add(_pob[i]);
          encontrado = true;
        }
        i++;
      }
    }

    return newPob;
  }

}
