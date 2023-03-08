package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;

/**
 * Seleccion por torneo con probabilidad.
 *
 * @param <T> puede ser Boolean o Double
 */
public class TournamentProbabilisticSelection<T> extends Selection<T> {

  /** Probabilidad de seleccionar la mejor opcion. */
  private double prob;

  /** Flag para saber si buscamos maximos o minimos. */
  private boolean isMax;

  /**
   * Instantiates a new tournament probabilistic selection.
   *
   * @param s numero de individuos a seleccionar
   * @param pob poblacion inicial
   * @param prob probabilidad de seleccionar el mejor individuo
   * @param isMax funcion de maximos o minimos
   */
  public TournamentProbabilisticSelection(int s, ArrayList<Individuo<T>> pob, double prob,
      boolean isMax) {
    super(s, pob);
    this.prob = prob;
    this.isMax = isMax;
  }

  /**
   * Selecciona individuos.
   *
   * @return la nueva poblacion
   */
  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    while (pob.size() >= 2) {

      int pos1 = (int) Math.random() * pob.size();
      Individuo<T> i1 = pob.get(pos1);
      pob.remove(pos1);
      int pos2 = (int) Math.random() * pob.size();
      Individuo<T> i2 = pob.get(pos2);
      pob.remove(pos2);

      double prob = Math.random();

      Individuo<T> aux;

      if ((prob < this.prob && isMax) || (prob > this.prob && !isMax)) {
        aux = i1.fitness() > i2.fitness() ? i1.copyIndividuo() : i2.copyIndividuo();
      } else {
        aux = i1.fitness() > i2.fitness() ? i2.copyIndividuo() : i1.copyIndividuo();
      }


      seleccionados.add(aux.copyIndividuo());
      seleccionados.add(aux.copyIndividuo());

    }

    seleccionados.addAll(pob);
    return seleccionados;
  }

}
