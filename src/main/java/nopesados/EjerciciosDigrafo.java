/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.List;

/**
 *
 * @author HP
 */
public class EjerciciosDigrafo {
    private Digrafo digrafo;

    private DFS dfs;
    private UtilsRecorridos controlMarcados;

    public EjerciciosDigrafo(Digrafo unDigrafo) {
        this.digrafo = unDigrafo;
        controlMarcados = new UtilsRecorridos(digrafo.cantidadDeVertices());
    }

    public boolean hayCiclo() {
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            if (hayCiclo(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(int posVertice) {
        controlMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDeVerticeEnTurno = digrafo.adyacenciasDeVertice(posVertice);
        for (Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            if (controlMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
                return true;
            } else {
                if (hayCiclo(posVerticeAdyacente) == true) {
                    return true;

                }
            }
        }
        controlMarcados.desmarcarVertice(posVertice);
        for (Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            controlMarcados.desmarcarVertice(posVerticeAdyacente);
        }
        return false;
    }

    public boolean esFuertementeConexo() {
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            DFS dfs2 = new DFS(digrafo, i);
            if (!dfs2.hayCaminoATodosLosVertices()) {
                return false;
            }
        }
        return true;
    }

    public boolean esDebilmenteConexo() {
        Grafo grafo = transformar(digrafo);
        EjerciciosGrafo ejecutar = new EjerciciosGrafo(grafo);
        if (ejecutar.esConexo()) {
            return true;
        }
        return false;
    }

    private Grafo transformar(Digrafo digrafo) {
        Grafo grafo = new Grafo();
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            grafo.insertarVertice();
        }
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            List<Integer> L1 = digrafo.listaDeAdyacencias.get(i);
            for (int j = 0; j < L1.size(); j++) {
                grafo.listaDeAdyacencias.get(i).add(L1.get(j));
                grafo.listaDeAdyacencias.get(L1.get(j)).add(i);
            }
        }
        return grafo;
    }

    public int cantIslas() {
        Grafo grafo = transformar(digrafo);
        EjerciciosGrafo ejecutar = new EjerciciosGrafo(grafo);
        int cantIslas = ejecutar.islas();
        return cantIslas;
    }

    public void elementosIslas() {
        Grafo grafo = transformar(digrafo);
        dfs = new DFS(grafo, 0);
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

    public Digrafo invertido(Digrafo digrafo) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Digrafo invertido = new Digrafo(digrafo.cantidadDeVertices());
        for (int OrigenOriginal = 0; OrigenOriginal < digrafo.cantidadDeVertices(); OrigenOriginal++) {
            Iterable<Integer> DestinosOriginales = digrafo.adyacenciasDeVertice(OrigenOriginal);
            for (Integer DestinoOriginal : DestinosOriginales) {
                invertido.insertarArista(DestinoOriginal, OrigenOriginal);
            }
        }

        return invertido;
    }
    public void todosDFS(DFS dfs){
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            if (!dfs.hayCaminoAVertice(i)) {
                 dfs.ejecutarDFS(i);
            }
        }
    }
    public int cantidadCFC() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        int cant = 0;
        DFS dfs1 = new DFS(digrafo, 0);
        todosDFS(dfs1);
        System.out.println(dfs1.getRecorrido());
        Iterable<Integer> elRecorrido = dfs1.getRecorrido();
        Digrafo invertido = invertido(digrafo);
        DFS dfs2 = new DFS(invertido, 0);
        cant++;
        //System.out.println(dfs2.obtenerRecorrido());
        for (int vertice : elRecorrido) {
            if (!dfs2.getControlMarcados().estaVerticeMarcado(vertice)) {
                dfs2.ejecutarDFS(vertice);
               // System.out.println(dfs2.obtenerRecorrido());
                cant++;
            }
        }
        return cant;
    }
}
