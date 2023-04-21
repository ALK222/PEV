package g02.individuals;

import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Individuo de calibrado.
 */
public class IndividuoNonograma extends Individuo<boolean[]> {

  private int numColumnas;
  private int numFilas;
  private ArrayList<ArrayList<Integer>> restriccionesColumnas;
  private ArrayList<ArrayList<Integer>> restriccionesFilas;
  private String file;

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param filename archivo del que se sacan las restricciones
   * @throws Exception
   */
  public IndividuoNonograma(String filename) throws Exception {

    this.restriccionesFilas = new ArrayList<ArrayList<Integer>>();
    this.restriccionesColumnas = new ArrayList<ArrayList<Integer>>();

    try {
      file = filename;
      File dataFile = new File(filename);
      Scanner fileReader = new Scanner(dataFile);

      this.numFilas = Integer.valueOf(fileReader.nextLine());

      for (int i = 0; i < numFilas; ++i) {
        String[] res = fileReader.nextLine().split(" ");
        ArrayList<Integer> lineRestriction = new ArrayList<Integer>();
        for (String r : res) {
          lineRestriction.add(Integer.valueOf(r));
        }
        restriccionesFilas.add(lineRestriction);
      }

      this.numColumnas = Integer.valueOf(fileReader.nextLine());

      for (int i = 0; i < numColumnas; ++i) {
        String[] res = fileReader.nextLine().split(" ");
        ArrayList<Integer> lineRestriction = new ArrayList<Integer>();
        for (String r : res) {
          lineRestriction.add(Integer.valueOf(r));
        }
        restriccionesColumnas.add(lineRestriction);
      }
      fileReader.close();
    } catch (Exception e) {
      throw new Exception("No se seleccionó un archivo");
    }


    this.chromosome = new boolean[numFilas][numColumnas];

    for (int i = 0; i < this.numFilas; i++) {
      for (int j = 0; j < this.numColumnas; ++j) {
        chromosome[i][j] = ThreadLocalRandom.current().nextBoolean();
      }
    }

  }

  /**
   * Instantiates a new individuo nonograma.
   *
   * @param chromosome cromosoma dado
   * @param numFilas numero de filas del nonograma
   * @param numColumas numero de columnas del nonograma
   * @param restricciones restricciones de las filas y columnas del nonograma
   */
  public IndividuoNonograma(boolean[][] chromosome, int numFilas, int numColumnas,
      ArrayList<ArrayList<Integer>> restriccionesFilas,
      ArrayList<ArrayList<Integer>> restriccionesColumnas) {
    this.numColumnas = numColumnas;
    this.numFilas = numFilas;

    this.restriccionesFilas = restriccionesFilas;
    this.restriccionesColumnas = restriccionesColumnas;

    this.chromosome = new boolean[numFilas][numColumnas];
    for (int i = 0; i < numFilas; i++) {
      for (int j = 0; j < numColumnas; ++j)
        this.chromosome[i][j] = chromosome[i][j];
    }
  }


  /**
   * Calcula el fitness del individuo.
   *
   * @return fitness del individuo
   */
  public double fitness() {
    return this.getValor();
  }

  /**
   * Muta un individuo.
   *
   * @param individuo Individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<boolean[]> mutar(Individuo<boolean[]> individuo, double prob, int mut) {
    if (ThreadLocalRandom.current().nextDouble() < prob) {
      switch (mut) {
        case 0:
        	for (int i = 0; i < this.numFilas; ++i) {
                for (int j = 0; j < this.numColumnas; ++j) {
                  if (ThreadLocalRandom.current().nextDouble() > 0.5) {
                    this.chromosome[i][j] = !this.chromosome[i][j];
                  }
                }
              }
          break;
        case 1:
        	this.mutaHeuristica(individuo);
          break;
        case 2:
        	this.mutaInversion(individuo);
        	break;
        default:
          for (int i = 0; i < this.numFilas; ++i) {
            for (int j = 0; j < this.numColumnas; ++j) {
              if (ThreadLocalRandom.current().nextDouble() > 0.5) {
                this.chromosome[i][j] = !this.chromosome[i][j];
              }
            }
          }
      }
    }
    return this.copyIndividuo();
  }

  private IndividuoNonograma mutaRotacionHeu(Individuo<boolean[]> individuo) {


    return this;
  }

  private IndividuoNonograma mutaHeuristica(Individuo<boolean[]> individuo) {

    double puntuacionInicial = this.getValor();

    for (int i = 0; i < this.numFilas; ++i) {
      for (int j = 0; j < this.numColumnas; ++j) {
        if (ThreadLocalRandom.current().nextDouble() > 0.5) {
          this.chromosome[i][j] = !this.chromosome[i][j];
          if (this.getValor() < puntuacionInicial) {
            this.chromosome[i][j] = !this.chromosome[i][j];
          }
        }
      }
    }

    return this;
  }

  private IndividuoNonograma mutaInversion(Individuo<boolean[]> individuo) {
	  for(int j = 0; j < individuo.getCromosoma().length; ++j) {
		  if(ThreadLocalRandom.current().nextBoolean()) {
			  int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length); int
			     punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
			     
			     while (punto2 == punto1) punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
			 
			     int ini = Math.min(punto1, punto2); int fin = Math.max(punto1, punto2);

			     Boolean[] aux = new Boolean[fin - ini];
			     int itAux = 0;
			      
			     for (int i = fin; i > ini; --i) { aux[itAux] = individuo.getCromosoma()[j][i]; ++itAux; }
			     
			     itAux = 0; for (int i = ini + 1; i <= fin; ++i) { individuo.getCromosoma()[j][i] = aux[itAux];
			     ++itAux; }
		  }
	  }

    return this;
  }

  private IndividuoNonograma mutaInsercion(Individuo<boolean[]> individuo) {
    /*
     * for (int i = 0; i < nDesplazamientos; ++i) { int punto1 =
     * ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1); int punto2 =
     * ThreadLocalRandom.current().nextInt(0, punto1); Integer aux =
     * Integer.valueOf(individuo.getCromosoma()[punto1]); for (int j = punto1; j > punto2; --j) {
     * individuo.getCromosoma()[j] = individuo.getCromosoma()[j - 1]; }
     * individuo.getCromosoma()[punto2] = Integer.valueOf(aux); }
     */

    return this;
  }

  private Individuo<boolean[]> mutaIntercambio(Individuo<boolean[]> individuo) {
    /*
     * int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1); int
     * punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
     * 
     * Integer aux = Integer.valueOf(individuo.getCromosoma()[punto1]);
     * individuo.getCromosoma()[punto1] = Integer.valueOf(individuo.getCromosoma()[punto2]);
     * individuo.getCromosoma()[punto2] = Integer.valueOf(aux);
     * 
     */return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public boolean[][] getCromosoma() {
    return chromosome;
  }

  /**
   * Gets the fenotipo of a given chromosome.
   *
   * @param index the index of the fenotype
   * @return the fenotype
   */
  // Necesario para calcular el fitness
  @Override
  public double getFenotipo(int index) {
    return 0;
  }

  /**
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
    double aux = 0;

    // filas
    for (int i = 0; i < numFilas; ++i) {
      aux += fitnessArray(restriccionesColumnas.get(i), this.chromosome[i], numColumnas);
    }

    // columnas
    for (int i = 0; i < numColumnas; ++i) {
      boolean[] columna = new boolean[numFilas];
      for (int j = 0; j < numFilas; ++j) {
        columna[j] = chromosome[j][i];
      }
      aux += fitnessArray(restriccionesFilas.get(i), columna, numFilas);
    }

    return aux;
  }

  private int fitnessArray(ArrayList<Integer> restricciones, boolean[] fila, int tam) {
    int fitness = 0;
    int conjuntos = 0;
    int puestas = 0;
    int seguidas = 0;
    int[] conjunto = new int[tam];

    for (int i = 0; i < tam; ++i) { // Comprobamos cada casilla
      if (seguidas > 0 && !fila[i]) { // si hay fichas seguidas y estamos en una casilla sin colocar, añadimos nuevo conjunto
        conjunto[conjuntos] = seguidas;
        conjuntos++;
        seguidas = 0;
      }
      if (fila[i]) { // añadimos una ficha seguida
        seguidas++;
        puestas++;
      }
    }

    if (seguidas > 0) { // añadimos un conjunto si la ultima fila estaba puesta
      conjunto[conjuntos] = seguidas;
      conjuntos++;
    }

    if (restricciones.size() == conjunto.length) {
      fitness += conjuntos * 10;
    } else if (restricciones.size() > conjunto.length) {
      fitness += conjuntos;
    } else {
      fitness -= conjuntos;
    }
    
    int maxPuestas = 0;

    for (int i = 0; i < restricciones.size(); ++i) {
      maxPuestas += restricciones.get(i);
      if (i > conjunto.length) {
        continue;
      }
      if (restricciones.get(i) == conjunto[i]) {
        fitness += conjunto[i] * 2;
      } else if(restricciones.get(i) > conjunto[i]) {
        fitness += 1;
      } else {
        fitness = 0;
      }
    }
    
    if(puestas <= maxPuestas) {
      fitness += puestas;
    } else {
      fitness -= puestas+ 1;
    }
    if(puestas == 0 && restricciones.get(0) == 0) {
      fitness += tam;
    }
    return fitness;
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<boolean[]> copyIndividuo() {
    return new IndividuoNonograma(this.chromosome, this.numFilas, this.numColumnas,
        this.restriccionesFilas, this.restriccionesColumnas);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return true;
  }

  @Override
  public void corregir() {
    this.chromosome[this.numFenotipos - 1] = this.chromosome[0];
  }

  @Override
  public String toString() {
    String aux = "Cromosoma: " + this.getValor();

    return aux;
  }


}
