package g02.selections;

import g02.individuals.Individuo;
import java.util.ArrayList;


/**
 * Seleccion por torneo determinista.
 *
 * @param <T> puede ser Boolean o Double
 */
public class TournamentDeterministicSelection<T> extends Selection<T> {

  /** Flag para saber si buscamos maximos o minimos. */
  private boolean isMax;

  /**
   * Instantiates a new tournament deterministic selection.
   *
   * @param s numero de individuos a seleccionar
   * @param pob poblacion inicial
   * @param isMax funcion de maximos o de minimos
   */
  public TournamentDeterministicSelection(int s, ArrayList<Individuo<T>> pob, boolean isMax) {
    super(s, pob);
    this.isMax = isMax;
  }

  /**
   * Selecciona los nuevos individuos.
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

      Individuo<T> aux;
      if (isMax) {
        aux = i1.fitness() > i2.fitness() ? i1.copyIndividuo() : i2.copyIndividuo();
      } else {
        aux = i1.fitness() < i2.fitness() ? i1.copyIndividuo() : i2.copyIndividuo();
      }


      seleccionados.add(aux);
      seleccionados.add(aux);


    }

    seleccionados.addAll(pob);
    return seleccionados;
  }

}
