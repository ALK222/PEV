package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.Random;


/**
 * Seleccion de ruleta.
 *
 * @param <T> puede ser Boolean o Double.
 */
public class RouletteSelection<T> extends Selection<T> {

  /**
   * Instantiates a new roulette selection.
   *
   * @param s tamaño de la poblacion.
   * @param pob poblacion de la que seleccionar.
   */
  public RouletteSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);
  }

  /**
   * Seleccion de individuos.
   *
   * @return nueva población con los individuos seleccionados.
   */
  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> newPob = new ArrayList<Individuo<T>>();

    probs = new double[pob.size()];

    fitnessTotal = 0;
    double auxMax = -Double.MAX_VALUE;
    double auxMin = Double.MAX_VALUE;

    for (int i = 0; i < pob.size(); ++i) { // Generamos el fitness de cada individuo
      double aux = pob.get(i).fitness();
      if (aux > auxMax) {
        auxMax = aux;
      } else if (aux < auxMin) {
        auxMin = aux;
      }

      probs[i] = aux;
    }

    if (pob.get(0).isMax()) {
      this.corrigeMaximizar(auxMin);
    } else {
      this.corrigeMinimizar(auxMax);
    }



    for (int i = 0; i < pob.size(); ++i) {
      if (i == 0) {
        probs[i] = probs[i] / fitnessTotal;
      } else {
        probs[i] = probs[i - 1] + (probs[i] / fitnessTotal);
      }
    }

    int seleccionados = 0;

    while (seleccionados < seleccionar) {
      Random r = new Random();
      double randomValue = r.nextDouble();

      boolean encontrado = false;
      int i = 0;
      while (!encontrado && i < pob.size()) {
        if (probs[i] > randomValue) {

          newPob.add(pob.get(i).copyIndividuo());
          encontrado = true;
          seleccionados++;
        }
        i++;
      }
    }

    return newPob;
  }

}
