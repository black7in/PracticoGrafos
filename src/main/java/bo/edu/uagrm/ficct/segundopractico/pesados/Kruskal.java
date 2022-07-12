/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.segundopractico.pesados;

import bo.edu.uagrm.ficct.segundopractico.Excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.segundopractico.Excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.segundopractico.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.LinkedList;

/**
 *
 * @author HP
 */
public class Kruskal {
     private GrafoPesado grafo;

    public Kruskal(GrafoPesado grafo) {
        this.grafo = grafo;
    }
    
     public GrafoPesado ProcesarKruskal() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste
    {
        GrafoPesado aux = new GrafoPesado(grafo.cantidadDeVertices());
        AristasOrdenadas ejecutar = new AristasOrdenadas(grafo);
        LinkedList<Arista> a = (LinkedList<Arista>) ejecutar.aristasDelGrafo();
        for(int i=0; i< a.size(); i++){
            aux.insertarArista(a.get(i).verticeOrigen, a.get(i).verticeDestino, a.get(i).peso);
            
            if(aux.hayCiclo()){
                System.out.println("hay ciclo al insertar" + a.get(i).verticeOrigen +a.get(i).verticeDestino);
                aux.eliminarArista(a.get(i).verticeOrigen, a.get(i).verticeDestino);
            }
            aux.controlMarcados.desmarcarTodos();
            
        }
        return aux;
    }
}
