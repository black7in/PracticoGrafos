/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;

/**
 *
 * @author HP
 */
public class EjerciciosGrafo {
    private Grafo grafo;
    private UtilsRecorridos controlMarcados;

    public EjerciciosGrafo(Grafo grafo) {
        this.grafo = grafo;
        controlMarcados = new UtilsRecorridos(grafo.cantidadDeVertices());
    }

    public boolean esConexo() {
        DFS dfs = new DFS(grafo, 0);
        return dfs.hayCaminoATodosLosVertices();
    }

    public int islas() {
        int cantIslas = 1;
        DFS dfs = new DFS(grafo, 0);
        for (int i = 0; i < grafo.listaDeAdyacencias.size(); i++) {
            if (!dfs.getControlMarcados().estaVerticeMarcado(i)) {
                cantIslas++;
                dfs.ejecutarDFS(i);
            }
        }
        return cantIslas;
    }
    
    public boolean hayCiclo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste  {
        Grafo grafoAux = new Grafo(grafo.cantidadDeVertices());
        for(int i = 0; i < grafo.cantidadDeVertices(); i++) {
            
            if(hayCiclo(grafoAux, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(Grafo grafoAux, int posVertice) throws ExcepcionAristaYaExiste {  
        controlMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDeVertice = grafo.listaDeAdyacencias.get(posVertice);
        for (Integer adyacente : adyacentesDeVertice) {
            //no esta marcado
            if(!controlMarcados.estaVerticeMarcado(adyacente)) {
                //no existe adyacencia y posVertice no es el adyacente
                if(!grafoAux.existeAdyacencia(posVertice, adyacente) && posVertice != adyacente) {
                    grafoAux.insertarArista(posVertice, adyacente);
                    hayCiclo(grafoAux, adyacente);
                }
            } 
            //esta marcado
            else {
                if(!grafoAux.existeAdyacencia(posVertice, adyacente) && posVertice != adyacente) {
                    return true;
                }
            }
        }
        return false;
    }
    public void elementosIslas() {
        DFS dfs = new DFS(grafo, 0);
        System.out.println("0" + dfs.getRecorrido());
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if (!dfs.hayCaminoAVertice(i)) {
                dfs.ejecutarDFS(i);
                DFS dfs2 = new DFS(grafo, i);
                String pos = "" + i;
                System.out.println(pos + dfs2.getRecorrido());
            }
        }
    }
}
