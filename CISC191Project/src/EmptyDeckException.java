/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: EmptyDeckException is an exception that is thrown when an attempt is made to draw a card from an empty deck.
 * 
 */

public class EmptyDeckException extends Exception {
    public EmptyDeckException(String message) { 
        super(message); 
    }
}
