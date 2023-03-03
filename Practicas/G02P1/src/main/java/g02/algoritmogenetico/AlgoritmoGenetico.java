package g02.algoritmogenetico;

import java.util.ArrayList;
import g02.Selections.Selection;
import g02.cruces.Cruces;
import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;
import g02.individuals.IndividuoFuncion2;
import g02.individuals.IndividuoFuncion3;
import g02.individuals.IndividuoFuncion4a;
import g02.individuals.IndividuoFuncion4b;

public class AlgoritmoGenetico<T> {
  private int _tamPoblacion;
  private ArrayList<Individuo<T>> _poblacion;
  private int _maxGeneraciones;
  private double _probCruce;
  private double _probMutacion;
  private Individuo<T> _elMejor;
  private double precision;
  private double _elitismo;
  private double[] _mejorGen;
  private double[] _mediaGen;
  private double[] _mejorSiempre;
  private Selection<T> _selection;
  private Cruces<T> _cruce;
  private boolean isMax;

  public AlgoritmoGenetico(int tam, int max, double probC, double probM, double prec,
      Selection<T> selection, Cruces<T> cruce, double elitismo) {
    _tamPoblacion = tam;
    _poblacion = new ArrayList<Individuo<T>>();
    _maxGeneraciones = max;
    _probCruce = probC;
    _probMutacion = probM;
    precision = prec;
    _elitismo = elitismo;

    _mejorGen = new double[_maxGeneraciones + 1];
    _mediaGen = new double[_maxGeneraciones + 1];
    _mejorSiempre = new double[_maxGeneraciones + 1];

    _selection = selection;

    _cruce = cruce;
  }


  @SuppressWarnings("unchecked")
  public Individuo<T> run(int ind, int dim) throws Exception {
    for (int i = 0; i < _tamPoblacion; i++) {
    	switch(ind) {
    	case 0:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion1(precision));
    		break;
    	case 1:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion2(precision, dim));
    		break;
    	case 2:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion3(precision));
    		break;
    	case 3:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion4a(precision, dim));
    		break;
    	case 4:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion4b(precision, dim));
    		break;
    	default:
    		_poblacion.add((Individuo<T>) new IndividuoFuncion1(precision));
    		break;
    	}
      
    }
    _selection.setPob(_poblacion);
    
    isMax = _poblacion.get(0).isMax();
    // Evaluar Pob
    evaluate(0);
    
    for (int i = 0; i < _maxGeneraciones; ++i) {
      //System.out.println(i);
      
      int directos = (int) Math.round(_poblacion.size() * _elitismo);
      ArrayList<Individuo<T>> newPob = new ArrayList<>();


      for (int j = 0; j < directos; ++j) {
        double max = -Double.MAX_VALUE;
        double min = Double.MAX_VALUE;
        int borrar = -1;
        for (int k = 0; k < _poblacion.size(); ++k) {
          double currFitness = _poblacion.get(k).fitness();
          
          if(isMax)
          {
            if (currFitness > max) {
            max = currFitness;
            borrar = k;
          }
          }
          else {
            if (currFitness < min) {
              min = currFitness;
              borrar = k;
            }
          }
          
        }
        newPob.add(_poblacion.get(borrar).copyIndividuo());
        _poblacion.remove(borrar);
      }
      
      
      // Seleccion
      ArrayList<Individuo<T>> seleccionados = _selection.run();

      

      

      ArrayList<Individuo<T>> cruzados = new ArrayList<>();
      // Cruce
      while(seleccionados.size() > 2) {

        ArrayList<Individuo<T>> aux =
            _cruce.cruzar(seleccionados.get(0), seleccionados.get(1), _probCruce);

        cruzados.add(aux.get(0).copyIndividuo());
        cruzados.add(aux.get(1).copyIndividuo());

        seleccionados.remove(0);
        seleccionados.remove(1);

      }



      cruzados.addAll(seleccionados); // Metemos el indidivuo que no se haya podido cruzar por
                                      // imparidad
      // Mutacion

      for (int j = 0; j < cruzados.size(); j++) {
        Individuo<T> i1 = cruzados.get(j);
        newPob.add(i1.mutar(i1, _probMutacion).copyIndividuo());
      }
      _selection.setPob(newPob);
      _poblacion = newPob;
      evaluate(i + 1);

    }

    return _elMejor;
  }


  private void evaluate(int iter) {
    double auxMedia = 0;
    Individuo<T> auxMejor = null;

    for (int i = 0; i < _tamPoblacion; i++) {
    	if(auxMejor==null)
    		auxMejor = _poblacion.get(i);
    	else if(isMax) {
    		if (_poblacion.get(i).fitness() > auxMejor.fitness()) {
    			auxMejor = _poblacion.get(i);
    		}
    	}
    	else if(!isMax) {
    		if (_poblacion.get(i).fitness() < auxMejor.fitness()) {
    			
    			auxMejor = _poblacion.get(i);
    		}
    	}
    	auxMedia += _poblacion.get(i).fitness();
    }
    

    if(_elMejor == null) {
    	_elMejor = auxMejor;
    }
    else if(isMax && auxMejor.fitness() > _elMejor.fitness())
    	_elMejor = auxMejor;
    else if (!isMax && auxMejor.fitness() < _elMejor.fitness())
    	_elMejor = auxMejor;
    	
    
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
