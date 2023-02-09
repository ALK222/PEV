package genetic_algorithm.selections;

import java.util.Random;
import genetic_algorithm.individuals.Individual;

public class RouletteSelection extends Selection {

  // public RouletteSelection(int size, int)
  @Override
  public Individual[] run() {
    Individual newPob[] = new Individual[_pob.length];

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
          newPob[seleccionados] = _pob[i];
          encontrado = true;
        }
        i++;
      }
    }

    return null;
  }

}
