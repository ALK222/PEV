package genetic_algorithm.selections;

import genetic_algorithm.individuals.Individual;

/**
 * Abstract class to extend all types of selection
 */
public abstract class Selection {
  
  Individual _pob[];
  int _seleccionar;
  
  public abstract Individual[] run();

}
