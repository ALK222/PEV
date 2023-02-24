package g02.algoritmogenetico;

import java.util.ArrayList;
import g02.Selections.Selection;
import g02.cruces.Cruces;
import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;

public class AlgoritmoGenetico<T> {
  private int _tamPoblacion;
  private Individuo[] _poblacion;
  private int _maxGeneraciones;
  private double _probCruce;
  private double _probMutacion;
  private int _tamTorneo;
  private Individuo<T> _elMejor;
  private int _posMejor;
  private double precision;
  private double[] _mejorGen;
  private double[] _mediaGen;
  private Selection<T> _selection;
  private Cruces<T> _cruce;

  public AlgoritmoGenetico(int tam, int max, double probC, double probM, int tamT, double prec,
      Selection<T> selection,Cruces<T> cruce) {
    _tamPoblacion = tam;
    _poblacion = new Individuo<?>[_tamPoblacion];
    _maxGeneraciones = max;
    _probCruce = probC;
    _probMutacion = probM;
    _tamTorneo = tamT;
    precision = prec;

    _mejorGen = new double[_maxGeneraciones + 1];
    _mediaGen = new double[_maxGeneraciones + 1];

    _selection = selection;
    
    _cruce = cruce;
  }


  public Individuo<T> run() {
    for (int i = 0; i < _tamPoblacion; i++) {
      _poblacion[i] = new IndividuoFuncion1(precision);
    }
    // Evaluar Pob
    evaluate(0);
    for (int i = 0; i < _maxGeneraciones; ++i) {
      // Seleccion
      ArrayList<Individuo<T>> seleccionados = _selection.run();

      // Cruce
      for (int j = 0; j < _tamPoblacion - 1; j += 2) {
        ArrayList<Individuo<T>> cruzados = _cruce.cruzar(seleccionados.get(i), seleccionados.get(i + 1), _probCruce);
      }
      // Mutacion

      for (int j = 0; j < _tamPoblacion; j += 2) {
        _poblacion[j] = _poblacion[j].mutar(_poblacion[j], _probMutacion);
      }

      evaluate(i + 1);
    }

    return _elMejor;
  }


  private void evaluate(int iter) {
    _mejorGen[iter] = -1;
    double auxMedia = 0;

    for (int i = 0; i < _tamPoblacion; i++) {
      double fit = _poblacion[i].fitness();
      if (fit > _mejorGen[iter]) {
        _mejorGen[iter] = fit;
        if (_elMejor == null || fit > _elMejor.fitness())
          _elMejor = _poblacion[i];
      }
      auxMedia += fit;
    }

    _mediaGen[iter] = auxMedia / _tamPoblacion;

  }

  // Getters para hacer las gráficas

  public double[] getMejores() {
    return _mejorGen;
  }

  public double[] getMedias() {
    return _mediaGen;
  }

}
