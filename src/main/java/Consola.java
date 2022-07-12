import bo.edu.uagrm.ficct.segundopractico.Excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.segundopractico.Excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.segundopractico.nopesados.AW;
import bo.edu.uagrm.ficct.segundopractico.nopesados.Digrafo;
import bo.edu.uagrm.ficct.segundopractico.nopesados.EjerciciosDigrafo;
import bo.edu.uagrm.ficct.segundopractico.nopesados.EjerciciosGrafo;
import bo.edu.uagrm.ficct.segundopractico.nopesados.Grafo;
import bo.edu.uagrm.ficct.segundopractico.nopesados.MatrizDeCaminos;

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
    }
}
