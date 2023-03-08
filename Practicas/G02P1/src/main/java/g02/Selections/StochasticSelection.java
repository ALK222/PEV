package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;
import java.util.Random;

/**
 * Seleccion estocastica de una poblacion.
 *
 * @param <T> puede ser Boolean o Double
 */
public class StochasticSelection<T> extends Selection<T> {

  /** Separacion entre los elementos escogidos. */
  public double sep;

  /**
   * Instantiates a new stochastic selection.
   *
   * @param s numero de individuos a seleccionar
   * @param pob polbacion inicial
   */
  public StochasticSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);

    sep = (double) 1 / s;
  }

  /**
   * Selecciona los individuos.
   *
   * @return la nueva poblacion
   */
  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> newPob = new ArrayList<>();

    double[] probs = new double[pob.size()];

    double fitnessTotal = 0;


    for (int i = 0; i < pob.size(); ++i) { // Generamos el fitness de cada individuo
      double aux = pob.get(i).fitness();
      fitnessTotal += aux;
      probs[i] = aux;
    }

    probs[0] = probs[0] / fitnessTotal;
    for (int i = 1; i < pob.size(); ++i) {
      probs[i] = (probs[i] / fitnessTotal) + probs[i - 1];
    }



    Random rand = new Random();
    double startingValue = sep * rand.nextDouble();
    startingValue -= sep; // correccion para primera iteracion del bucle

    int seleccionados = 0;

    while (seleccionados < seleccionar) {

      startingValue += sep;

      boolean encontrado = false;
      int i = 0;
      double probAcumulada = 0;
      while (!encontrado && i < pob.size()) {
        probAcumulada += probs[i];
        if (probAcumulada > startingValue) {
          newPob.add(pob.get(i));
          encontrado = true;
          seleccionados++;
        }
        i++;
      }
    }
    return newPob;

  }

}
