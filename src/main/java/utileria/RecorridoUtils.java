/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utileria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class RecorridoUtils {
    private List<Boolean> marcados;
    
    public RecorridoUtils(int nroDeVertices){
        marcados = new ArrayList<>();
        for(int i=0; i< nroDeVertices; i++){
            this.marcados.add(Boolean.FALSE);
        }
    }
    
    public void desmarcarTodos(){
        for(int i=0; i < this.marcados.size(); i++){
            this.marcados.set(i, Boolean.FALSE);
        }
    }
    
    public boolean estanTodosMarcados(){
        for(Boolean marcado: this.marcados){
            if(!marcado){
                return false;
            }
        }
        return true;
    }
    
    public boolean estaVerticeMarcado(int posDeVertice){
        return marcados.get(posDeVertice);
    }
    
    public void marcarVertice(int posDeVertice){
        //pre: La posicion es valida
        marcados.set(posDeVertice, Boolean.TRUE);
    }
}
