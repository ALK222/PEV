package g02.algoritmogenetico;

import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;

public class AlgoritmoGenetico {
  private int _tamPoblacion;
  private Individuo[] _poblacion;
  private int _maxGeneraciones;
  private double _probCruce;
  private double _probMutacion;
  private int _tamTorneo;
  private Individuo _elMejor;
  private int _posMejor;
  private double precision;
  private double[] _mejorGen;
  private double[] _mediaGen;
  
  public AlgoritmoGenetico(int tam, int max, double probC, double probM, int tamT, double prec) {
	  _tamPoblacion = tam;
	  _poblacion = new Individuo[_tamPoblacion];
	  _maxGeneraciones = max;
	  _probCruce = probC;
	  _probMutacion = probM;
	  _tamTorneo = tamT;
	  precision = prec;  
	  
	  _mejorGen = new double[_maxGeneraciones + 1];
	  _mediaGen = new double[_maxGeneraciones + 1];
  }


  public Individuo run() {
    // TODO
    // iniciar pob
	for(int i = 0; i < _tamPoblacion; i++) {
		_poblacion[i] = new IndividuoFuncion1(precision);
	}
    // Evaluar Pob
	evaluate(0);
    for (int i = 0; i < _maxGeneraciones; ++i) {
      // seleccion
      // Cruce
      // Mutacion
    	
    	evaluate(i+1);
    }
    
    return _elMejor;
  }
  
  
  private void evaluate(int iter) {
	  _mejorGen[iter] = -1;
	  double auxMedia = 0;
	  
	  for(int i = 0; i < _tamPoblacion; i++) {
		  double fit = _poblacion[i].fitness();
		  if(fit > _mejorGen[iter]) {
			  _mejorGen[iter] = fit;
			  if(_elMejor == null || fit > _elMejor.fitness()) _elMejor = _poblacion[i];
		  }
		  auxMedia += fit;
	  }
	  
	  _mediaGen[iter] = auxMedia / _tamPoblacion;
	  
  }
  
  // Getters para hacer las gráficas
  
  public double[] getMejores() { return _mejorGen; }
  
  public double[] getMedias() { return _mediaGen; }

}
