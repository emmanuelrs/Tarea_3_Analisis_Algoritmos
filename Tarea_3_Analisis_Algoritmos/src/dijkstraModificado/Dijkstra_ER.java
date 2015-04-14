package dijkstraModificado;

/**
 * Tarea 3 Análisis de Algoritmos.
 * 
 * @author Emmanuel Rosales Salas
 * Carnet: 2013108931
 *
 */
import java.util.*;

public class Dijkstra_ER {
	 
	// Numero de vertices en el grafo
		
	static final int V  = 9;
	public static ArrayList<Integer> arreglo = new ArrayList<Integer>();
	static int nodoBuscar = 0;
	public static Hashtable <Integer, Integer> nodos = new Hashtable<Integer, Integer>();


	// Funcion utilitaria para encontrar el vertice con la distancia minima, 
	// a partir del conjunto de los vertices todavia no incluidos en el 
	// camino mas corto

	private static int minDistance(int[] dist, boolean[] verticeYaProcesado){
	   
		// Initialize min value
		
	   int min = Integer.MAX_VALUE; // Inicializa todo en infinito.
	   int min_index = 0; 
	 
	   for (int v = 0; v < V; v++){
	     if (verticeYaProcesado[v] == false && dist[v] <= min) {
	         min = dist[v];
	         min_index = v;
	      }
	   }   
	   return min_index;
	}
	 
	// Funcion utilitaria para imprimir el arreglo de distancias calculadas

	// Se comenta la función de imprimir la solución
	// Porque solo se debe imprimir el camino más corto.

	/*
	private static void printSolution(int[] dist, int n){
	   System.out.println("Distancia del vertice desde el origen\n");
	   for (int i = 0; i < V; i++)
	      System.out.println(i + " \t\t " + dist[i]);
	}
	 */

	private static void dijkstra(int[][] grafo, int src){
	     int[] dist = new int[V];     
	     
	     // dist[i] guarda la distancia mas corta desde src hasta el vertice i
	 
	     boolean[] verticeYaProcesado = new boolean[V]; 
	     
	     //Este arreglo tiene true si el vertice i ya fue procesado
	 
	     // Initialize all distances as INFINITE and stpSet[] as false
	     
	     for (int i = 0; i < V; i++) {
	        dist[i] = Integer.MAX_VALUE;
	        verticeYaProcesado[i] = false;
	     }
	     
	     // La distancia del vertice origen hacia el mismo es siempre 0
	     
	     dist[src] = 0;
	 
	     //Encuentra el camino mas corto para todos los vertices
	     
	     for (int count = 0; count < V-1; count++){

	       //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
	       //En la primera iteracion siempre se devuelve src
	    	 
	       int u = minDistance(dist, verticeYaProcesado);
	       
	       // Se marca como ya procesado
	       
	       verticeYaProcesado[u] = true;
	 
	       // Update dist value of the adjacent vertices of the picked vertex.
	      
	       for (int v = 0; v < V; v++){

	         //Se actualiza la dist[v] solo si no esta en verticeYaProcesado, hay un
	         //arco desde u a v y el peso total del camino desde src hasta v a traves de u es 
	         // mas pequeno que el valor actual de dist[v]
	    	   
	    	 // u --> min_index  
	    	   
	         if (!verticeYaProcesado[v] && grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u]+grafo[u][v] < dist[v]){
	            dist[v] = dist[u] + grafo[u][v];
	            
	             nodos.put(v,u); // Hash Table donde se guardan los nodos con su antecesor de menor costo
	         	}
	         
	         }
	       
	     }
	   
	     nodoBuscar = 8; // Se le asigna a nodoBuscar el nodo que se quiere la ruta más corta
	     				// En este caso es el 8 ya que es el último nodo, es decir, indica el nodo a buscar

	     
	     while(nodoBuscar != 0){
	    	    int nodo_final = nodos.get(nodoBuscar); //--> nodo_final va hacer el antecesor del final
	    	    									   
	    	    arreglo.add(nodoBuscar);      // --> se agrega a la lista el nodo por donde pasa la ruta más corta
	    	    nodoBuscar = nodo_final;     // ---> Se actualiza el nodoBuscar para buscar el antecesor del antecesor del nodo final
	    
	     }
	     arreglo.add(0);                    // --> Se agrega el nodo de partida 0
	     Collections.reverse(arreglo);      // --> Se invierte la lista para mostrar el camino desde el nodo inicial al final
	     
	     System.out.println("");
	     System.out.println("La Ruta más corta para llegar es --> " + arreglo);
	     
	     //printSolution(dist, V);
	}

	// driver program to test above function

	public static void main(String[] args){
	   
		/* Let us create the example graph discussed above */
	   
		 int[][] graph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
	                      {4, 0, 8, 0, 0, 0, 0, 11, 0},
	                      {0, 8, 0, 7, 0, 4, 0, 0, 2},
	                      {0, 0, 7, 0, 9, 14, 0, 0, 0},
	                      {0, 0, 0, 9, 0, 10, 0, 0, 0},
	                      {0, 0, 4, 0, 10, 0, 2, 0, 0},
	                      {0, 0, 0, 14, 0, 2, 0, 1, 6},
	                      {8, 11, 0, 0, 0, 0, 1, 0, 7},
	                      {0, 0, 2, 0, 0, 0, 6, 7, 0}};
	 
	    dijkstra(graph, 0);
	   
	}
}
	


