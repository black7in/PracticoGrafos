import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import nopesados.AW;
import nopesados.Digrafo;
import nopesados.EjerciciosDigrafo;
import nopesados.EjerciciosGrafo;
import nopesados.Grafo;
import nopesados.MatrizDeCaminos;

/**
 *
 * @author HP
 */
public class Consola {

    public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Digrafo digrafo = new Digrafo(9);
        digrafo.insertarArista(0, 1);
        digrafo.insertarArista(1, 2);
        digrafo.insertarArista(2, 0);
        digrafo.insertarArista(2, 3);
        digrafo.insertarArista(3, 4);
        digrafo.insertarArista(4, 7);
        digrafo.insertarArista(4, 5);
        digrafo.insertarArista(5, 6);
        digrafo.insertarArista(6, 7);
        digrafo.insertarArista(6, 4);
        EjerciciosDigrafo ejecutar2 = new EjerciciosDigrafo(digrafo);
        System.out.println(digrafo);
        System.out.println("Ejercicio 3: HAY CICLO EN EL DIGRAFO?");
        System.out.println(ejecutar2.hayCiclo());
        System.out.println("Ejercicio 4: HAY CICLO EN EL DIGRAFO? USANDO MATRIZ DE CAMINOS");
        MatrizDeCaminos matriz = new MatrizDeCaminos(digrafo);
        System.out.println(matriz.hayCiclo());
        System.out.println("Ejercicio 5: RETORNAR LOS ELEMENTOS DE LAS ISLAS DE UN DIGRAFO");
        ejecutar2.elementosIslas();
        System.out.println("Ejercicio 6: Encontrar la matriz de caminos");
        matriz.showMatriz(matriz.matrizDeCaminos());
        System.out.println("Ejercicio 7: Metodo digrafo para encontrar si es debilmente conexo");
        System.out.println(ejecutar2.esDebilmenteConexo());
        System.out.println("Ejercicio 8: METODO digrafo para encontrar si es fuertemente conexo");
        System.out.println(ejecutar2.esFuertementeConexo());
        Grafo grafo= new Grafo(8);
        grafo.insertarArista(0, 1);
        grafo.insertarArista(0, 2);
        grafo.insertarArista(1, 2);
        grafo.insertarArista(1, 4);
        grafo.insertarArista(2, 3);
        grafo.insertarArista(5, 6);
        grafo.insertarArista(6, 7);
        grafo.insertarArista(7, 5);
        EjerciciosGrafo ejecutar = new EjerciciosGrafo(grafo);
        System.out.println("Ejercicio 9: VERIFICAR SI HAY CICLOS EN UN GRAFO");
        System.out.println(ejecutar.hayCiclo());
        System.out.println("Ejercicio 10: OBTENER LOS ELEMENTOS DE LAS ISLAS DE UN GRAFO");
        ejecutar.elementosIslas();
        System.out.println("Ejercicio 11: NRO DE ISLAS EN EL DIGRAFO");
        System.out.println(ejecutar2.cantIslas());
        System.out.println("Ejercicio 12: IMPLEMENTAR EL ALGORITMO DE WARSHALL");
        AW ejecutarWarshall = new AW(digrafo);
        ejecutarWarshall.showMatriz(ejecutarWarshall.algoritmoWharsall());
        System.out.println("Ejercicio 14: CUANTAS COMPONENTES FUERTEMENTE CONEXAS EXISTEN");
        System.out.println(ejecutar2.cantidadCFC());

        /*DigrafoPesado digrafo = new DigrafoPesado(4);
        digrafo.insertarArista(0, 1, 50);
        digrafo.insertarArista(0, 2, 30);
        digrafo.insertarArista(1, 3, 10);
        digrafo.insertarArista(2, 1, 1);
        System.out.println(digrafo);
        System.out.println("Ejercicio 13 Algoritmo FLOYD WARSHALL que muestre costos y caminos de un vertice a todos los demas");
       FW ejecutar = new FW(digrafo);
        System.out.println("Ejercicio 15 ");
        System.out.println(ejecutar.mostrarCostosYCaminos(2));
       Dijkstra dijkstra = new Dijkstra(digrafo);
      //  System.out.println(dijkstra.getCostoMinimo(0, 1));
      //  dijkstra.imprimirCamino();
       dijkstra.caminosYCostosDesdeUnVertice(0);
       
       GrafoPesado grafo = new GrafoPesado(6);
        grafo.insertarArista(0, 1, 3);
        grafo.insertarArista(0, 2, 2);
        grafo.insertarArista(1, 3, 5);
        grafo.insertarArista(2, 3, 1);
        grafo.insertarArista(3, 4, 8);
        grafo.insertarArista(4, 5, 9);
        
        System.out.println("Kruskal");
       Kruskal kruskal = new Kruskal(grafo);
       System.out.println(kruskal.ProcesarKruskal());
       
        System.out.println("PRIM");
       Prim arbol = new Prim(grafo);
        GrafoPesado resultado = arbol.arbol();
        System.out.println(resultado);

        */
    }
}
