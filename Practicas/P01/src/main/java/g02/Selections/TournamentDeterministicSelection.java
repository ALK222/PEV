package g02.Selections;

import java.util.ArrayList;
import g02.individuals.Individuo;

public class TournamentDeterministicSelection<T> extends Selection<T> {

  public TournamentDeterministicSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);
  }

  @Override
  public ArrayList<Individuo<T>> run() {
    ArrayList<Individuo<T>> seleccionados = new ArrayList<>();

    while (_pob.size() >= 2) {

      int pos1 = (int) Math.random() * _pob.size();
      Individuo<T> i1 = _pob.get(pos1);
      _pob.remove(pos1);
      int pos2 = (int) Math.random() * _pob.size();
      Individuo<T> i2 = _pob.get(pos2);
      _pob.remove(pos2);
      
      Individuo<T> aux;
      aux = i1.fitness() > i2.fitness() ? i1.copyIndividuo() : i2.copyIndividuo();
      
      seleccionados.add(aux.copyIndividuo());
      seleccionados.add(aux.copyIndividuo());
      

    }
    
    seleccionados.addAll(_pob);
    return seleccionados;
  }

}
