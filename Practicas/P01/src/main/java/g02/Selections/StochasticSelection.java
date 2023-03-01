package g02.Selections;

import java.util.ArrayList;
import java.util.Random;
import g02.individuals.Individuo;

public class StochasticSelection<T> extends Selection<T> {

  public double _sep;

  public StochasticSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);

    _sep = (double) 1 / s;
  }

  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> newPob = new ArrayList<>();

    double probs[] = new double[_pob.size()];
    
    double fitnessTotal = 0;


    for (int i = 0; i < _pob.size(); ++i) { // Generamos el fitness de cada individuo
      double aux = _pob.get(i).fitness();
      fitnessTotal += aux;
      probs[i] = aux;
    }
    
    probs[0] = probs[0] / fitnessTotal;
    for (int i = 1; i < _pob.size(); ++i) {
      probs[i] = (probs[i] / fitnessTotal) + probs[i - 1];
    }



    Random rand = new Random();
    double startingValue = _sep * rand.nextDouble();
    startingValue -= _sep; // correccion para primera iteracion del bucle

    int seleccionados = 0;

    while (seleccionados < _seleccionar) {

      startingValue += _sep;

      boolean encontrado = false;
      int i = 0;
      double probAcumulada = 0;
      while (!encontrado && i < _pob.size()) {
        probAcumulada += probs[i];
        if (probAcumulada > startingValue) {
          newPob.add(_pob.get(i));
          encontrado = true;
          seleccionados++;
        }
        i++;
      }
    }
    return newPob;

  }

}
