package g02.cruces;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import g02.individuals.Individuo;

public class CruceColFil implements Cruces<boolean[]> {
	
	 @Override
	  public ArrayList<Individuo<boolean[]>> cruzar(Individuo<boolean[]> i1, Individuo<boolean[]> i2, double prob)
	      throws Exception {
		 ArrayList<Individuo<boolean[]>> res = new ArrayList<Individuo<boolean[]>>();
		 
		 if (ThreadLocalRandom.current().nextDouble() < prob) {
			 boolean[][] cromosoma1 = new boolean[i1.getCromosoma().length][i1.getCromosoma()[0].length];
			 boolean[][] cromosoma2 = new boolean[i1.getCromosoma().length][i1.getCromosoma()[0].length];
			 
			 // Mejores Filas
			 for(int i = 0; i < cromosoma1.length; ++i) {
				 if(i1.fitnessFila(i) <= i2.fitnessFila(i)) {
					 for(int j = 0; j < cromosoma1[i].length; ++j) {
						 cromosoma1[i][j] = i2.getCromosoma()[i][j];
					 }
				 }
				 else {
					 for(int j = 0; j < cromosoma1[i].length; ++j) {
						 cromosoma1[i][j] = i1.getCromosoma()[i][j];
					 }
				 }
			 }
			 
			 // Mejores columnas
			 for(int i = 0; i < cromosoma2.length; ++i) {
				 if(i1.fitnessColumna(i) <= i2.fitnessColumna(i)) {
					 for(int j = 0; j < cromosoma2[i].length; ++j) {
						 cromosoma2[j][i] = i2.getCromosoma()[j][i];
					 }
				 }
				 else {
					 for(int j = 0; j < cromosoma2[i].length; ++j) {
						 cromosoma2[j][i] = i1.getCromosoma()[j][i];
					 }
				 }
			 }
			 
			 // Copar los nuevos individuos
			 for(int i = 0; i < cromosoma1.length; ++i) {
				 for(int j = 0; j < cromosoma1[i].length; ++j) {
					 i1.getCromosoma()[i][j] = cromosoma1[i][j];
					 i2.getCromosoma()[i][j] = cromosoma2[i][j];
				 }
			 }
			 
		 }
		
		 
		 res.add(i1.copyIndividuo());
		 res.add(i2.copyIndividuo());
			 
			 
		 return res;
	  }

}
