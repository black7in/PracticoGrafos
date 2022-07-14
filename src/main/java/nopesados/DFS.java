/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DFS {
    private UtilsRecorridos controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    public UtilsRecorridos getControlMarcados() {
        return controlDeMarcados;
    }

    public DFS(Grafo unGrafo, int posVerticePartida){
        this.grafo =unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlDeMarcados = new UtilsRecorridos(this.grafo.cantidadDeVertices()); //ya esta todo desmarcado
        ejecutarDFS(posVerticePartida);
    }

    public void ejecutarDFS(int posDeVerticeDePartida) {
        controlDeMarcados.marcarVertice(posDeVerticeDePartida);
        recorrido.add(posDeVerticeDePartida);
        Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacenciasDeVertice(posDeVerticeDePartida);
        for(Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno){
            if(!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)){
                ejecutarDFS(posVerticeAdyacente);
            }
        }
    }
    
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    
    public boolean hayCaminoAVertice(int posVertice){
        grafo.validarVertice(posVertice);
        return controlDeMarcados.estaVerticeMarcado(posVertice);
    }
    
    public boolean hayCaminoATodosLosVertices(){
        return controlDeMarcados.estanTodosMarcados();
    } 
}
