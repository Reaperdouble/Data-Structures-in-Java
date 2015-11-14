/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

public class CourseException extends Exception{
    String message = "Default message";
    
    void setMessage(String m){
        this.message = m;
    }
    
}
