/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: InvalidPlayException is an exception that is thrown when a player attempts to make an invalid play in the Card Clash game.
 * 
 */

public class InvalidPlayException extends Exception {
    public InvalidPlayException(String message) {
        super(message);
    }
}