/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.segundopractico.Excepciones;

/**
 *
 * @author HP
 */
public class ExcepcionAristaNoExiste extends Exception {

    public ExcepcionAristaNoExiste() {
        super("Arista NO existe");
    }

    public ExcepcionAristaNoExiste(String string) {
        super(string);
    }
}
