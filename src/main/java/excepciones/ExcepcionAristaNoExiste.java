/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author HP
 */
public class ExcepcionAristaNoExiste extends Exception {

    public ExcepcionAristaNoExiste() {
        super("Arista NO existe en su Grafo.");
    }

    public ExcepcionAristaNoExiste(String string) {
        super(string);
    }
}
