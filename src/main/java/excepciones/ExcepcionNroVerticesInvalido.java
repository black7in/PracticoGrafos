/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author HP
 */
public class ExcepcionNroVerticesInvalido extends Exception {
     public ExcepcionNroVerticesInvalido() {
        super("Nro de vertices para el grafo debe ser mayor a cero.");
    }

    public ExcepcionNroVerticesInvalido(String string) {
        super(string);
    } 
}