/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import excepciones.ExcepcionAristaNoExiste;
import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Grafo {
      protected List<List<Integer>> listaDeAdyacencias;
    
    public Grafo(){
        this.listaDeAdyacencias = new LinkedList<>();
    }
    public Grafo(int nroDeVertices)throws ExcepcionNroVerticesInvalido{
        if(nroDeVertices <=0 ){
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        for(int i = 0; i< nroDeVertices;i++){
            this.insertarVertice();
        }
    }
    public void insertarVertice(){
        List<Integer> listaDeAdayacentesDeVerticesAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(listaDeAdayacentesDeVerticesAInsertar);
    }
    public int cantidadDeVertices(){
        return listaDeAdyacencias.size();
    }
    
    public void validarVertice(int posDeVertice) {
        if(posDeVertice<0 || posDeVertice >= this.cantidadDeVertices()){
            throw new IllegalArgumentException("No existe vertice en la posicion" + posDeVertice + "en el Grafo.");
        }
    }
    
    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacentesDelVertice.size();
    }
    
    public boolean existeAdyacencia(int posDeVerticeOrigen, int posDeVerticeDestino){
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posDeVerticeOrigen);
        return adyacentesDelOrigen.contains(posDeVerticeDestino);
    }
    
    public Iterable<Integer> adyacenciasDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> iterableDeAdyacentes = adyacentesDelVertice;
        return iterableDeAdyacentes;
    }
    
    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino)throws ExcepcionAristaYaExiste{
        if(this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if(posDeVerticeOrigen!=posDeVerticeDestino){
              List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(posDeVerticeDestino);
            adyacentesDelDestino.add(posDeVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)throws ExcepcionAristaNoExiste{
        if(!existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen) ;
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino);
        if(posVerticeOrigen!=posVerticeDestino){
            List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            int posicionDelOrigen = adyacentesDelDestino.indexOf(posVerticeOrigen);
            adyacentesDelDestino.remove(posicionDelOrigen);
        }
    }
    
    public int cantidadDeAristas(){
        int cantAristas =0;
        int cantLazos=0;
        for(int i=0; i<this.listaDeAdyacencias.size();i++){
            List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for(Integer posDeAdyacente: adyacentesDeUnVertice){
                if(i==posDeAdyacente){
                    cantLazos++;
                }else{
                    cantAristas++;
                }
            }
        }
        return cantLazos + (cantAristas / 2);
    }
    
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> listaAdyacentesDeUnVertice: this.listaDeAdyacencias) {
            int posicionDeVerticeEnAdy = listaAdyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeEnAdy >= 0) {
                listaAdyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < listaAdyacentesDeUnVertice.size(); i++){
                int posicionAdyacente = listaAdyacentesDeUnVertice.get(i);
                if (posicionAdyacente > posVerticeAEliminar){
                    listaAdyacentesDeUnVertice.set(i,posicionAdyacente - 1);
                }
            }
        }
    }
        
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < listaDeAdyacencias.size(); i++) {
            List<Integer> listaDeAdyacentes = listaDeAdyacencias.get(i);
            s = s + i + "  [ " + listaDeAdyacencias.get(i) + " ]-->[ ";
            for (int j = 0; j < listaDeAdyacentes.size(); j++) {
                if (j == listaDeAdyacentes.size() - 1) {
                    s = s + "" + listaDeAdyacentes.get(j);
                } else {
                    s = s + "" + listaDeAdyacentes.get(j) + " , ";
                }
            }
            s = s + " ]" + '\n';
        }
        return s;
    }
}
