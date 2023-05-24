package g02.algoritmogenetico;

import g02.Selections.Selection;
import g02.cruces.Cruces;
import g02.individuals.Cromosoma;
import g02.individuals.Individuo;
import g02.individuals.IndividuoPractica3;

import java.util.ArrayList;



/**
 * Controlador de un algoritmo genetico.
 *
 * @param <T> puede ser Boolean o Double
 */
public class AlgoritmoGenetico<T> {

  /** Tamaño de la poblacion a evolucionar. */
  private int tamPoblacion;

  /** Poblacion. */
  private ArrayList<Individuo<T>> poblacion;

  /** Numero de generaciones. */
  private int maxGeneraciones;

  /** Probabilidad de cruce. */
  private double probCruce;

  /** Probabilidad de mutacion. */
  private double probMutacion;

  /** Mejor individuo. */
  private Individuo<T> elMejor;

  /** Precision de la codificacion de los individuos. */
  private double precision;

  /** Porcentaje de individuos de cada generacion que no mutan. */
  private double elitismo;

  /** Mejor fitness de cada generacion. */
  private double[] mejorGen;

  /** MEdia de fitness de cada generacion. */
  private double[] mediaGen;

  /** Mejor absoluto en cada generacion. */
  private double[] mejorSiempre;

  /** Metodo de seleccion. */
  private Selection<T> selection;

  /** Metodo de cruce. */
  private Cruces<T> cruce;

  /** The is max. */
  private boolean isMax;

  private int mutacion;

  private int typeArbol;

  private boolean controlBloating;
  private boolean regenPob;
  private double[] esperados;

  /**
   * Instantiates a new algoritmo genetico.
   *
   * @param tam tamaño de la poblacion
   * @param max numero de generaciones
   * @param probC probabilidad de cruce
   * @param probM probabilidad de mutacion
   * @param prec precision de la codificacion
   * @param selection metodo de seleccion
   * @param cruce metodo de cruce
   * @param elitismo porcentaje de elitismo
   */
  public AlgoritmoGenetico(int tam, int max, double probC, double probM, double prec,
      Selection<T> selection, Cruces<T> cruce, double elitismo, int mut, boolean controlBloating, boolean regen) {
    
    IndividuoPractica3.setK(0);
    tamPoblacion = tam;
    poblacion = new ArrayList<Individuo<T>>();
    maxGeneraciones = max;
    probCruce = probC;
    probMutacion = probM;
    precision = prec;
    this.elitismo = elitismo;
    mutacion = mut;
    this.controlBloating = controlBloating;
    this.regenPob = regen;

    mejorGen = new double[maxGeneraciones + 1];
    mediaGen = new double[maxGeneraciones + 1];
    mejorSiempre = new double[maxGeneraciones + 1];

    this.selection = selection;

    this.cruce = cruce;
    
    this.esperados = new double[101];
    for (int i = 0; i < 101; ++i) {
    	double valorX = (-1.0) + ((2.0 / 100.0) * i);
    	this.esperados[i] = Math.pow(valorX, 4) + Math.pow(valorX, 3) + Math.pow(valorX, 2) + valorX + 1;
      }
    
  }

  public void calcularK() {
    double[] fitnessArray = new double[this.tamPoblacion];
    double[] tamArray = new double[this.tamPoblacion];

    for (int i = 0; i < this.tamPoblacion; ++i) {
      fitnessArray[i] = this.poblacion.get(i).getValor();
      tamArray[i] = ((Cromosoma) this.poblacion.get(i).getCromosoma()).getTam();
    }

    double c = covariance(tamArray, fitnessArray);
    double v = variance(tamArray);

    if (c == 0 || v == 0) {
      IndividuoPractica3.setK(0);
    } else {
      IndividuoPractica3.setK(c / v);
    }
  }


  /**
   * Ejecuta todas las generaciones.
   *
   * @param ind Numero de problema
   * @param dim dimensiones para los problemas que la tengan
   * @return el mejor individuo tras todas las generaciones
   * @throws Excepcion si hay algun problema en alguna de las generaciones
   */
  @SuppressWarnings("unchecked")
  public Individuo<T> run(int ind, int dim) throws Exception {
    this.typeArbol = ind;
    
    for (int i = 0; i < tamPoblacion; i++) {
      poblacion.add((Individuo<T>) new IndividuoPractica3(precision, ind, this.esperados));
    }
    selection.setPob(poblacion);

    isMax = poblacion.get(0).isMax();
    // Evaluar Pob
    evaluate(0);
    if (controlBloating) {
      calcularK();
    }

    for (int i = 0; i < maxGeneraciones; ++i) {



      // ELITISMO

      int directos = (int) Math.round(poblacion.size() * elitismo);
      ArrayList<Individuo<T>> newPob = new ArrayList<>();


      if (isMax) {
        this.poblacion.sort((o1, o2) -> (o1.compareTo(o2)));
      } else {
        this.poblacion.sort((o1, o2) -> (o2.compareTo(o1)));
      }

      for (int j = 0; j < directos; ++j) {
        newPob.add(this.poblacion.get(j).copyIndividuo());
        this.poblacion.remove(j);
      }

      // Seleccion
      ArrayList<Individuo<T>> seleccionados = selection.run();
      ArrayList<Individuo<T>> cruzados = new ArrayList<>();

      // Cruce

      while (seleccionados.size() > 2) {

        Individuo<T> p1 = seleccionados.get(0);
        Individuo<T> p2 = seleccionados.get(1);
        seleccionados.remove(0);
        seleccionados.remove(1);

        ArrayList<Individuo<T>> aux = cruce.cruzar(p1, p2, probCruce);


        if (aux != null) {
          cruzados.add(aux.get(0).copyIndividuo());
          cruzados.add(aux.get(1).copyIndividuo());
        }


      }

      // Metemos el indidivuo que no se haya podido cruzar por imparidad
      for (int j = 0; j < seleccionados.size(); ++j) {
        if (seleccionados.get(j) == null) {
          continue;
        }
        cruzados.add(seleccionados.get(j).copyIndividuo());
      }

      // Mutacion
      for (int j = 0; j < cruzados.size(); j++) {
        Individuo<T> i1 = cruzados.get(j);
        i1 = i1.mutar(i1, probMutacion, mutacion);
        newPob.add(i1.copyIndividuo());
      }

      selection.setPob(newPob);
      poblacion = newPob;

      evaluate(i + 1);

      if(this.regenPob)
    	  regenerarPob(i, newPob);
      
      
      if (controlBloating) {
        calcularK();
      }
    }

    return elMejor.copyIndividuo();

  }

  public static double variance(double[] values) {
    double mean = mean(values);
    double sum = 0.0;
    for (int i = 0; i < values.length; i++) {
      sum += Math.pow(values[i] - mean, 2);
    }
    double variance = sum / (values.length - 1);
    return variance;
  }

  public static double covariance(double[] x, double[] y) {
    double meanX = mean(x);
    double meanY = mean(y);
    double sum = 0.0;
    for (int i = 0; i < x.length; i++) {
      double auxX = (x[i] - meanX);
      double auxY = (y[i] - meanY);

      sum += auxX * auxY;
    }
    double covariance = sum / (x.length - 1);
    return covariance;
  }

  public static double mean(double[] values) {
    double sum = 0.0;
    for (int i = 0; i < values.length; i++) {
      sum += values[i];
    }
    double mean = sum / values.length;
    return mean;
  }

  @SuppressWarnings("unchecked")
  private void regenerarPob(int i, ArrayList<Individuo<T>> newPob) {

    if (mediaGen[i] >= 0.7 * mejorGen[i] || (i > 0 && mediaGen[i] >= 0.9 * mediaGen[i - 1])) {


      // Regeneramos poblacion si la media converge al maximo

      int nuevos = tamPoblacion - (int) Math.round(tamPoblacion * this.elitismo);

      ArrayList<Individuo<T>> regenerados = new ArrayList<Individuo<T>>();

      for (int j = 0; j < nuevos; j++) {
        regenerados.add((Individuo<T>) new IndividuoPractica3(precision, this.typeArbol, this.esperados));


      }

      if (isMax) {
        newPob.sort((o1, o2) -> (o1.compareTo(o2)));
      } else {
        newPob.sort((o1, o2) -> (o2.compareTo(o1)));
      }


      for (int j = 0; j < (int) Math.round(tamPoblacion * this.elitismo); ++j) {
        regenerados.add((Individuo<T>) newPob.get(j));
      }


      this.poblacion = newPob;
      this.selection.setPob(regenerados);
    }

  }


  /**
   * Evalua la evolucion tras cada generacion.
   *
   * @param iter numero de la generacion a evaluar
   */
  private void evaluate(int iter) {
    double auxMedia = 0;
    Individuo<T> auxMejor = null;

    for (int i = 0; i < tamPoblacion; i++) {
      if (auxMejor == null) {
        auxMejor = poblacion.get(i);
      } else if (isMax) {
        if (poblacion.get(i).fitness() > auxMejor.fitness()) {
          auxMejor = poblacion.get(i).copyIndividuo();
        }
      } else if (!isMax) {
        if (poblacion.get(i).fitness() < auxMejor.fitness()) {

          auxMejor = poblacion.get(i).copyIndividuo();
        }
      }
      auxMedia += poblacion.get(i).fitness();
    }


    if (elMejor == null) {
      elMejor = auxMejor;
    } else if (isMax && auxMejor.fitness() > elMejor.fitness()) {
      elMejor = auxMejor;
    } else if (!isMax && auxMejor.fitness() < elMejor.fitness()) {
      elMejor = auxMejor;
    }


    mejorGen[iter] = auxMejor.fitness();
    mejorSiempre[iter] = elMejor.fitness();
    mediaGen[iter] = auxMedia / tamPoblacion;

  }

  // Getters para hacer las gráficas

  /**
   * Gets the mejores.
   *
   * @return the mejores
   */
  public double[] getMejores() {
    return mejorGen;
  }

  /**
   * Gets the medias.
   *
   * @return the medias
   */
  public double[] getMedias() {
    return mediaGen;
  }

  /**
   * Gets the mejor siempre.
   *
   * @return the mejor siempre
   */
  public double[] getMejorSiempre() {
    return mejorSiempre;
  }

}
