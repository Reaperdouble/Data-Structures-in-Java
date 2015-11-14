/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class NotADirectoryException extends Exception{
    static String message = "This is not a directory";


    public NotADirectoryException() {
        super(message);
    }

    public NotADirectoryException(String message) {
        super(message);
    }
    
    
}

