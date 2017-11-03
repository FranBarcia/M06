/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

/**
 * Excepcio produida en la gestio de la persistencia
 * @author professor
 */
public class GestorJpaException extends Exception{
    /**
     * Construeix una excepcio relacionada amb la gestio de la persistencia
     * @param message missatge de l'excepcio
     */
    public GestorJpaException(String message) {
        super(message);
    }
}
