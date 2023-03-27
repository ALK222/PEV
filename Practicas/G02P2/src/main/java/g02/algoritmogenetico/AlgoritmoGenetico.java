package g02.algoritmogenetico;

import g02.Selections.Selection;
import g02.cruces.Cruces;
import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;
import g02.individuals.IndividuoFuncion2;
import g02.individuals.IndividuoFuncion3;
import g02.individuals.IndividuoFuncion4a;
import g02.individuals.IndividuoFuncion4b;
import g02.individuals.IndividuoPractica2;

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
      Selection<T> selection, Cruces<T> cruce, double elitismo, int mut) {
    tamPoblacion = tam;
    poblacion = new ArrayList<Individuo<T>>();
    maxGeneraciones = max;
    probCruce = probC;
    probMutacion = probM;
    precision = prec;
    this.elitismo = elitismo;
    mutacion = mut;

    mejorGen = new double[maxGeneraciones + 1];
    mediaGen = new double[maxGeneraciones + 1];
    mejorSiempre = new double[maxGeneraciones + 1];

    this.selection = selection;

    this.cruce = cruce;
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
    for (int i = 0; i < tamPoblacion; i++) {
      switch (ind) {
        case 0:
          poblacion.add((Individuo<T>) new IndividuoPractica2(precision));
          break;
        case 1:
          poblacion.add((Individuo<T>) new IndividuoFuncion2(precision, dim));
          break;
        case 2:
          poblacion.add((Individuo<T>) new IndividuoFuncion3(precision));
          break;
        case 3:
          poblacion.add((Individuo<T>) new IndividuoFuncion4a(precision, dim));
          break;
        case 4:
          poblacion.add((Individuo<T>) new IndividuoFuncion4b(precision, dim));
          break;
        default:
          poblacion.add((Individuo<T>) new IndividuoFuncion1(precision));
          break;
      }

    }
    selection.setPob(poblacion);

    isMax = poblacion.get(0).isMax();
    // Evaluar Pob
    evaluate(0);

    for (int i = 0; i < maxGeneraciones; ++i) {
      System.out.println(i);

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
        

        ArrayList<Individuo<T>> aux =
            cruce.cruzar(seleccionados.get(0), seleccionados.get(1), probCruce);

        cruzados.add(aux.get(0).copyIndividuo());
        cruzados.add(aux.get(1).copyIndividuo());

        seleccionados.remove(0);
        seleccionados.remove(1);

      }

      // Metemos el indidivuo que no se haya podido cruzar por imparidad
      for(int j = 0; j < seleccionados.size(); ++j) {
        cruzados.add(seleccionados.get(j).copyIndividuo());
      }

      // Mutacion
      for (int j = 0; j < cruzados.size(); j++) {
        Individuo<T> i1 = cruzados.get(j);
        i1 = i1.mutar(i1, probMutacion, mutacion);
        //i1.corregir();
        newPob.add(i1.copyIndividuo());
      }
      selection.setPob(newPob);
      poblacion = newPob;
      evaluate(i + 1);

    }

    return elMejor.copyIndividuo();
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
