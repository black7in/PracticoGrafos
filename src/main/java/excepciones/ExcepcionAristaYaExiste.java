/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author HP
 */
public class ExcepcionAristaYaExiste extends Exception {

    public ExcepcionAristaYaExiste() {
        super("Arista ya existe en su Grafo.");
    }

    public ExcepcionAristaYaExiste(String string) {
        super(string);
    }
    
}