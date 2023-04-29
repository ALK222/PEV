package g02.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Arbol {
	private String valor;
	private ArrayList<Arbol> hijos;
	private int numHijos;
	private int numNodos;
	private int max_prof;
	private int profundidad;
	private boolean useIF;
	private boolean esHoja;
	private boolean esRaiz;
	
	// Constructora del �rbol
	public Arbol(String s) {
		
	}
	
	public Arbol(int profundidad2, boolean useIf2) {
		// TODO Auto-generated constructor stub
	}

	// Devuelve el arbol en forma de array
	public ArrayList<String> toArray(){
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
	}
	
	// Insertar un valor en el arbol (nodo simple)
	public Arbol insert(String v, int index){
		Arbol a = new Arbol(v);
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
		return a;
	}
	
	// Insertar un arbol en otro arbol.
	public void insert(Arbol a, int index){
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
	}
	
	// Devuelve nodo index
	public Arbol at(int index){
		return at(this, 0, index);
	}
	
	// Funcion para encontrar el nodo index
	private Arbol at(Arbol a, int pos, int index){
		Arbol s = null;
		if(pos >= index) s = a;
		else if(a.getNumHijos() > 0)
		{
			for(int i = 0; i < a.getNumHijos(); i++)
				if(s == null) s = at(a.getHijos().get(i), pos+i+1, index);
		}
		return s;
	}
	
	// Devuelve el numero de hijos
	private int getNumHijos() {
		return numHijos;
	}
	
	// Devuelve todos los hijos en forma de ArrayList
	public ArrayList<Arbol> getHijos() {
		return hijos;
	}

	// Ayuda para el m�todo toArray()
	private void toArrayAux(ArrayList<String> array, Arbol a){
		array.add(a.valor);
		for(int i = 0; i < a.hijos.size(); i++){
			toArrayAux(array, a.hijos.get(i));
		}
	}
	
	// Inicializaci�n del �rbol
	public int inicializacionCompleta(int p, int nodos){
		int n = nodos;
		int nHijos = 2;
		if(p < max_prof){
			setProfundidad(p);
			int func = 0;
			func = ThreadLocalRandom.current().nextInt(Cromosoma.funciones.length);
			this.valor = Cromosoma.funciones[func];
			this.setEsRaiz(true);
		}
		//TODO acabar funcion
		return 0;
	}

	private void setEsRaiz(boolean b) {
		esRaiz = b;
	}

	private void setProfundidad(int p) {
		profundidad = p;
	}

	public void inicializacionCreciente(int i) {
		// TODO Auto-generated method stub
		
	}
	public String getValor() {
		return this.valor;
	}
	
	public boolean getEsRaiz() {
		return this.esRaiz;
	}
}
