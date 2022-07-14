/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author HP
 */
public class BFS {
    private UtilsRecorridos controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    
    public BFS(Grafo unGrafo, int unaPosDeVerticeDePartida){
        this.grafo = unGrafo;
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido = new ArrayList<>();
        controlDeMarcados = new UtilsRecorridos(this.grafo.cantidadDeVertices()); //ya esta todo desmarcado
        ejecutarBFS(unaPosDeVerticeDePartida);
    }

    private void ejecutarBFS(int podeDeVerticeDePartida) {
        Queue<Integer> cola= new LinkedList<>();
        cola.offer(podeDeVerticeDePartida);
        controlDeMarcados.marcarVertice(podeDeVerticeDePartida);
        do{
            int posVerticeEnTurno = cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacenciasDeVertice(posVerticeEnTurno);
            for(Integer posVerticeAdyacente: adyacentesDeVerticeEnTurno){
                if(!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)){
                    cola.offer(posVerticeAdyacente);
                    controlDeMarcados.marcarVertice(posVerticeAdyacente);
                }
            }
        } while (!cola.isEmpty());
    }
    
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    
    public boolean hayCaminoAVertice(int posDeVerticeDestino){
        grafo.validarVertice(posDeVerticeDestino);
        return controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }
   
    public boolean hayCaminoATodosLosVertices(){
        return this.controlDeMarcados.estanTodosMarcados();
    }
}
