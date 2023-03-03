package g02.Selections;

import java.util.ArrayList;
import g02.individuals.Individuo;

public class TruncateSelection<T> extends Selection<T> {

  private double _truncRate;

  public TruncateSelection(int s, ArrayList<Individuo<T>> pob, double truncRate) {
    super(s, pob);
    _truncRate = truncRate;
  }

  @Override
  public ArrayList<Individuo<T>> run() {

    _pob.sort((o1, o2) -> (o1.compareTo(o2)));
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    int numSeleccionados = (int) Math.round(_truncRate * _pob.size());

    int vecesReproducido = (int) Math.round(1 / _truncRate);


    for (int i = 0; i < numSeleccionados; ++i) {
      for (int j = 0; j < vecesReproducido; ++j) {
        seleccionados.add(_pob.get(i).copyIndividuo());
      }
    }

    return seleccionados;
  }



}
