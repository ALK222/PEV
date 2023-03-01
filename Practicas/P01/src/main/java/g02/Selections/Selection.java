package g02.Selections;

import java.util.ArrayList;
import g02.individuals.Individuo;

/**
 * Abstract class to extend all types of selection
 */
public abstract class Selection<T> {
  
  protected ArrayList<Individuo<T>> _pob;
  protected double[] probs;
  protected int _seleccionar;
  
  public Selection(int s, ArrayList<Individuo<T>> pob)
  {
    _seleccionar = s;
    _pob = pob;
  }
  
  public abstract ArrayList<Individuo<T>> run();
  
  public void setPob(ArrayList<Individuo<T>> pob) {
    _pob = pob;
  }

}
