/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nopesados;

import excepciones.ExcepcionAristaNoExiste;
import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HP
 */
public class Digrafo extends Grafo{
    public Digrafo(){
        super(); //que llame al constructor del padre
    }
    public Digrafo(int nroDeVertices)throws ExcepcionNroVerticesInvalido{
        super(nroDeVertices);
    }

    @Override
    public void eliminarArista(int posDeVerticeOrigen, int posDeVerticeDestino) throws ExcepcionAristaNoExiste {
    if(!existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posDeVerticeOrigen) ;
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posDeVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino);
    }

    @Override
    public int cantidadDeAristas() {
        int c=0;
        for(int i=0; i<listaDeAdyacencias.size();i++){
            int tamaño = listaDeAdyacencias.get(i).size();
            c= c+tamaño;
        }
        return c;
    }

    @Override
    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino) throws ExcepcionAristaYaExiste {
    if(this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("Metodo no soportado en grafos dirigidos");
    }
    //hay que implementarlp
    public int gradoDeEntradaDeVertice(int posDeVertice) {
        validarVertice(posDeVertice); 
        int entradasDeVertice =0;
        for(List<Integer> adyacentesDeUnVertice: super.listaDeAdyacencias){
            for(Integer posAdyacente: adyacentesDeUnVertice){
                if(posAdyacente == posDeVertice){
                    entradasDeVertice++;
                }
            }
        }
        return entradasDeVertice;
    }
    public int gradoDeSalidaDeVertice(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice);
    }
}
