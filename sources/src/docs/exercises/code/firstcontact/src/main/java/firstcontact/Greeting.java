/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcontact;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class Greeting {

    final String subject;
    Greeting( String sub ) {
        subject=sub;
    }

    String greet() {
        return "Hello "+ subject;
    }
    
}
