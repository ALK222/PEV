package g02.Selections;

import java.util.ArrayList;
import g02.individuals.Individuo;

public class RestosSelection<T> extends Selection<T> {

  public RestosSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);

  }

  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    int tamOriginal = _pob.size();
    int sel = 0;

    int fitnessTotal = 0;
    double probs[] = new double[_pob.size()];

    for (int i = 0; i < _pob.size(); ++i) {
      probs[i] = _pob.get(i).fitness();
      fitnessTotal += probs[i];
    }

    for (int i = 0; i < _pob.size(); ++i) {
      probs[i] = probs[i] / fitnessTotal;
    }

    for (int i = 0; i < _pob.size(); ++i) {
      int vecesSeleccionado = (int) Math.round(probs[i] * _seleccionar);

      if(vecesSeleccionado > 0) {
        sel++;
        seleccionados.add(_pob.get(i).copyIndividuo());
        _pob.remove(i);
      }
    }

    Selection<T> aux = new RouletteSelection<T>(tamOriginal - sel, _pob);

    ArrayList<Individuo<T>> ultimos = aux.run();

    for (Individuo<T> i : ultimos) {
      seleccionados.add(i.copyIndividuo());
    }

    return seleccionados;
  }

}
