package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;


/**
 * Seleccion por truncamiento.
 *
 * @param <T> puede ser Boolean o Double
 */
public class TruncateSelection<T> extends Selection<T> {

  /** Ratio de truncamiento. */
  private double truncRate;

  /**
   * Instantiates a new truncate selection.
   *
   * @param s individuos a seleccionar
   * @param pob poblacion inicial
   * @param truncRate ratio de truncamiento
   */
  public TruncateSelection(int s, ArrayList<Individuo<T>> pob, double truncRate) {
    super(s, pob);
    this.truncRate = truncRate;
  }

  /**
   * Selecciona individuos.
   *
   * @return la nueva poblacion
   */
  @Override
  public ArrayList<Individuo<T>> run() {
	if(_pob.get(0).isMax()) {
		_pob.sort((o1, o2) -> (o1.compareTo(o2)));
	}
	else {
		_pob.sort((o2, o1) -> (o1.compareTo(o2)));
	}
    
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    int numSeleccionados = (int) Math.round(truncRate * pob.size());

    int vecesReproducido = (int) Math.round(1 / truncRate);


    for (int i = 0; i < numSeleccionados; ++i) {
      for (int j = 0; j < vecesReproducido; ++j) {
        seleccionados.add(pob.get(i).copyIndividuo());
      }
    }

    return seleccionados;
  }

}
