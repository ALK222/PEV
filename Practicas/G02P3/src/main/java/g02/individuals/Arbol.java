package g02.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Arbol {
  private String valor;
  private ArrayList<Arbol> hijos;
  private int numHijos;
  private int numNodos;
  private int max_prof = 5;
  private int min_prof = 2;
  private int profundidad;
  private boolean esHoja;
  private boolean esRaiz;

  // Constructora del �rbol
  public Arbol(String s) {

  }

  public Arbol(int profundidad2, int nodos) {
    this.profundidad = profundidad2;
    this.numNodos = nodos;

  }

  public Arbol(Arbol arbol) {
    this.valor = arbol.valor;
    this.hijos = new ArrayList<Arbol>();

    if (arbol.hijos != null && !arbol.hijos.isEmpty()) {
      for (Arbol a : arbol.hijos) {
        if (a != null) {
          this.hijos.add(new Arbol(a));
        }
      }
    }

    this.profundidad = arbol.profundidad;
    this.esHoja = arbol.esHoja;
    this.esRaiz = arbol.esRaiz;
    this.numNodos = arbol.numNodos;
  }

  public int getProfundidad() {
    return this.profundidad;
  }

  // Devuelve el arbol en forma de array
  public ArrayList<String> toArray() {
    ArrayList<String> array = new ArrayList<String>();
    toArrayAux(array, this);
    return array;
  }

  // Insertar un valor en el arbol (nodo simple)
  public Arbol insert(String v, int index) {
    Arbol a = new Arbol(v);
    if (index == -1) {
      hijos.add(a);
      numHijos = hijos.size();
    } else
      hijos.set(index, a);
    return a;
  }

  // Insertar un arbol en otro arbol.
  public void insert(Arbol a, int index) {
    if (index == -1) {
      hijos.add(a);
      numHijos = hijos.size();
    } else if (index > hijos.size()) {
      hijos.add(index, a);
    } else {
      hijos.set(index, a);
    }
  }

  // Devuelve nodo index
  public Arbol at(int index) {
    return at(this, 0, index);
  }

  // Funcion para encontrar el nodo index
  private Arbol at(Arbol a, int pos, int index) {
    Arbol s = null;
    if (pos >= index)
      s = a;
    else if (a.getNumHijos() > 0) {
      for (int i = 0; i < a.getNumHijos(); i++)
        if (s == null)
          s = at(a.getHijos().get(i), pos + i + 1, index);
    }
    return s;
  }

  // Devuelve el numero de hijos
  private int getNumHijos() {
    return hijos.size();
  }

  // Devuelve todos los hijos en forma de ArrayList
  public ArrayList<Arbol> getHijos() {
    return hijos;
  }

  public void getFunciones(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
    for (int i = 0; i < hijos.size(); ++i) {
      if (hijos.get(i).getEsRaiz()) {
        nodos.add(hijos.get(i));
        getFunciones(hijos.get(i).hijos, nodos);
      }
    }
  }

  public void getTerminales(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
    for (int i = 0; i < hijos.size(); ++i) {
      if (!hijos.get(i).getEsRaiz()) {
        nodos.add(hijos.get(i));
        // getFunciones(hijos.get(i).hijos, nodos);
      } else {
        getTerminales(hijos.get(i).hijos, nodos);
      }
    }
  }

  public int getNodos(Arbol a, int n) {
    if (a.esHoja)
      return n;
    if (a.valor.equals("add") || a.valor.equals("sub") || a.valor.equals("mul")) {
      n = getNodos(a.hijos.get(0), n + 1);
      n = getNodos(a.hijos.get(1), n + 1);
    }
    return n;
  }

  public void setNumNodos(int nodos) {
    this.numNodos = nodos;
  }

  // Ayuda para el m�todo toArray()
  private void toArrayAux(ArrayList<String> array, Arbol a) {
    array.add(a.valor);
    for (int i = 0; i < a.hijos.size(); i++) {
      toArrayAux(array, a.hijos.get(i));
    }
  }

  // Inicializaci�n del �rbol
  public int inicializacionCompleta(int p, int nodos) {
    if (p < max_prof) {
      numNodos++;
      setProfundidad(p);
      int func = 0;
      func = ThreadLocalRandom.current().nextInt(Cromosoma.funciones.length);
      this.valor = Cromosoma.funciones[func];
      this.setEsRaiz(true);
      for (int i = 0; i < nodos; i++) {
        numNodos++;
        Arbol hijo1 = new Arbol(p + 1, nodos);
        hijo1.inicializacionCompleta(p + 1, nodos);
        hijos.add(hijo1);
      }
    } else {
      numNodos++;
      setProfundidad(p);
      int t = ThreadLocalRandom.current().nextInt(Cromosoma.terminales.length);
      this.valor = Cromosoma.terminales[t];
      this.setEsRaiz(false);
    }
    return 0;
  }

  private void setEsRaiz(boolean b) {
    esRaiz = b;
  }

  private void setProfundidad(int p) {
    profundidad = p;
  }

  public void inicializacionCreciente(int p, int nodos) {
    if (p < max_prof) {
      setProfundidad(p);
      // Si se llega a la profundidad mínima se empiezan a crear hojas
      if (min_prof < p) {
        int ale = ThreadLocalRandom.current()
            .nextInt(Cromosoma.funciones.length + Cromosoma.terminales.length);
        // Se toma un valor de las funciones
        if (ale < Cromosoma.funciones.length) {
          numNodos++;
          this.setEsRaiz(true);
          this.valor = Cromosoma.funciones[ale];
          this.hijos = new ArrayList<Arbol>();
          for (int i = 0; i < nodos; i++) {
            numNodos++;
            Arbol hijo1 = new Arbol(p + 1, nodos);
            hijo1.inicializacionCreciente(p + 1, nodos);
            hijos.add(hijo1);
          }
        }
        // Se toma un valor de los terminales
        else {
          numNodos++;
          this.setEsRaiz(false);
          this.valor = Cromosoma.terminales[ale - Cromosoma.funciones.length];
        }
      }
      // Si no se llega a la profundidad minima no se pueden crear hojas
      else {
        numNodos++;
        int func = 0;
        func = ThreadLocalRandom.current().nextInt(Cromosoma.funciones.length);
        this.valor = Cromosoma.funciones[func];
        this.setEsRaiz(true);
        this.hijos = new ArrayList<Arbol>();
        for (int i = 0; i < nodos; i++) {
          numNodos++;
          Arbol hijo1 = new Arbol(p + 1, nodos);
          hijo1.inicializacionCreciente(p + 1, nodos);
          hijos.add(hijo1);
        }
      }
    } else {
      numNodos++;
      setProfundidad(p);
      int t = ThreadLocalRandom.current().nextInt(Cromosoma.terminales.length);
      this.valor = Cromosoma.terminales[t];
      this.setEsRaiz(false);
    }
  }

  public String getValor() {
    return this.valor;
  }

  public boolean getEsRaiz() {
    return this.esRaiz;
  }

  public String toString() {
    String s = this.valor;
    if (this.hijos != null) {
      for (Arbol a : this.hijos) {
        s += "\n ";
        s += a.toString();
      }
    }
    return s;
  }

}
