/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heapsort;

/**
 *
 * @author autarch
 */
public class Command {
    public int[] State;
    public int FirstIndex;
    public int SecondIndex;
    public Instruction Type;
    
    public Command(int [] state, int first, int second, Instruction type) {
        State = state.clone();
        FirstIndex = first;
        SecondIndex = second;
        Type = type;
    }
    
    @Override
    public String toString() {
        return /*Heapsort.toString(State) + "\n" +*/ "" + FirstIndex + " " + SecondIndex + " " + Type.toString() + "\n";
    }
}
