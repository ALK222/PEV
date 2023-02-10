package g02.Selections;

import g02.individuals.Individual;

/**
 * Abstract class to extend all types of selection
 */
public abstract class Selection {
  
  Individual _pob[];
  int _seleccionar;
  
  public abstract Individual[] run();

}
