package g02.algoritmogenetico;

import g02.individuals.Individuo;

public class AlgoritmoGenetico {
  private int _tamPoblacion;
  private Individuo[] _poblacion;
  private int _maxGeneraciones;
  private double _probCruce;
  private double _probMutacion;
  private int _tamTorneo;
  private Individuo _elMejor;
  private int _posMejor;


  public Individuo run() {
    // TODO
    // iniciar pob
    // Evaluar Pob
    for (int i = 0; i < _maxGeneraciones; ++i) {
      // seleccion
      // Cruce
      // Mutacion
    }
    
    return _elMejor;
  }

}
