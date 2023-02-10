package g02.Selections;

import g02.individuals.Individuo;

/**
 * Abstract class to extend all types of selection
 */
public abstract class Selection {
  
  Individuo _pob[];
  int _seleccionar;
  
  public abstract Individuo[] run();

}
