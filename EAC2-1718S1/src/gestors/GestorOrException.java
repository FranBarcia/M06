/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;

/**
 * Situacio excepcional produida en el sistema de persistencia
 * @author professor
 */
public class GestorOrException extends Exception{

    public GestorOrException(String message) {
        super(message);
    }
    

}
