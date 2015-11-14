/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author radra_000
 */
public class FullPlannerException extends Exception {

    static String message = "Planner is full";

    public FullPlannerException() {
        super(message);
    }

    public FullPlannerException(String message) {
        super(message);
    }

    public FullPlannerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullPlannerException(Throwable cause) {
        super(cause);
    }

    public FullPlannerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
