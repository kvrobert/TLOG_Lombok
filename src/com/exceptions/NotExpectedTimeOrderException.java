/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exceptions;

/**
 *
 * @author rkissvincze
 */
public class NotExpectedTimeOrderException extends Exception {

    public NotExpectedTimeOrderException() {
        
        super();
    }
    
    public NotExpectedTimeOrderException(String message) {
        
        super(message);
    }
    
}
