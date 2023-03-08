package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;

/**
 * Seleccion por restos de una poblacion, cuando obtiene el maximo por restos utiliza una seleccion
 * por ruleta para el resto.
 *
 * @param <T> Puede ser Boolean o Double.
 */
public class RestosSelection<T> extends Selection<T> {

  /**
   * Instantiates a new restos selection.
   *
   * @param s tamaño de seleccion.
   * @param pob población a seleccionar.
   */
  public RestosSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);

  }

  /**
   * Selecciona individuos.
   *
   * @return la nueva población.
   */
  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    final int tamOriginal = pob.size();
    int sel = 0;

    int fitnessTotal = 0;
    double[] probs = new double[pob.size()];

    for (int i = 0; i < pob.size(); ++i) {
      probs[i] = pob.get(i).fitness();
      fitnessTotal += probs[i];
    }

    for (int i = 0; i < pob.size(); ++i) {
      probs[i] = probs[i] / fitnessTotal;
    }

    for (int i = 0; i < pob.size(); ++i) {
      int vecesSeleccionado = (int) Math.round(probs[i] * seleccionar);

      if (vecesSeleccionado > 0) {
        sel++;
        seleccionados.add(pob.get(i).copyIndividuo());
        pob.remove(i);
      }
    }

    Selection<T> aux = new RouletteSelection<T>(tamOriginal - sel, pob);

    ArrayList<Individuo<T>> ultimos = aux.run();

    for (Individuo<T> i : ultimos) {
      seleccionados.add(i.copyIndividuo());
    }

    return seleccionados;
  }

}
