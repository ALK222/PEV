package g02.algoritmogenetico;

import java.util.ArrayList;
import g02.Selections.Selection;
import g02.cruces.Cruces;
import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;

public class AlgoritmoGenetico<T> {
  private int _tamPoblacion;
  private ArrayList<Individuo<T>> _poblacion;
  private int _maxGeneraciones;
  private double _probCruce;
  private double _probMutacion;
  private int _tamTorneo;
  private Individuo<T> _elMejor;
  private int _posMejor;
  private double precision;
  private double[] _mejorGen;
  private double[] _mediaGen;
  private double[] _mejorSiempre;
  private Selection<T> _selection;
  private Cruces<T> _cruce;

  public AlgoritmoGenetico(int tam, int max, double probC, double probM, int tamT, double prec,
      Selection<T> selection,Cruces<T> cruce) {
    _tamPoblacion = tam;
    _poblacion = new ArrayList<Individuo<T>>();
    _maxGeneraciones = max;
    _probCruce = probC;
    _probMutacion = probM;
    _tamTorneo = tamT;
    precision = prec;

    _mejorGen = new double[_maxGeneraciones + 1];
    _mediaGen = new double[_maxGeneraciones + 1];
    _mejorSiempre = new double[_maxGeneraciones + 1];

    _selection = selection;
    
    _cruce = cruce;
  }


  public Individuo<T> run() throws Exception {
    for (int i = 0; i < _tamPoblacion; i++) {
      _poblacion.add((Individuo<T>) new IndividuoFuncion1(precision));
    }
    _selection.setPob(_poblacion);
    // Evaluar Pob
    evaluate(0);
    
    for (int i = 0; i < _maxGeneraciones; ++i) {
      System.out.println(i);
      // Seleccion
      ArrayList<Individuo<T>> seleccionados = _selection.run();

      // Cruce
      for (int j = 0; j < seleccionados.size() - 2; j += 2) {
        ArrayList<Individuo<T>> cruzados = _cruce.cruzar(seleccionados.get(j), seleccionados.get(j + 1), _probCruce);
        seleccionados.remove(j);
        seleccionados.remove(j+1);
        seleccionados.addAll(cruzados);
      }
      // Mutacion

      for (int j = 0; j < _tamPoblacion; j += 2) {
        Individuo<T> i1= seleccionados.get(j);
        seleccionados.remove(j);
        seleccionados.add(i1.mutar(i1, _probMutacion));
      }

      evaluate(i + 1);
      _selection.setPob(seleccionados);
      _poblacion = seleccionados;
    }

    return _elMejor;
  }


  private void evaluate(int iter) {
    double auxMedia = 0;
    Individuo<T> auxMejor = null;

    for (int i = 0; i < _tamPoblacion; i++) {
    	if(auxMejor==null)
    		auxMejor = _poblacion.get(i);
    	else if (_poblacion.get(i).fitness() > auxMejor.fitness()) {
    		auxMejor = _poblacion.get(i);
    	}
    	auxMedia += _poblacion.get(i).fitness();
    }
    

    if(_elMejor == null || auxMejor.fitness() > _elMejor.fitness()) {
    	_elMejor = auxMejor;
    }
    	
    
    _mejorGen[iter] = auxMejor.fitness();
    _mejorSiempre[iter] = _elMejor.fitness();
    _mediaGen[iter] = auxMedia / _tamPoblacion;

  }

  // Getters para hacer las gráficas

  public double[] getMejores() {
    return _mejorGen;
  }

  public double[] getMedias() {
    return _mediaGen;
  }
  
  public double[] getMejorSiempre() {
	  return _mejorSiempre;
  }

}
