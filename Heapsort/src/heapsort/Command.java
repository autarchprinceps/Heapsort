package heapsort;

/**
 *
 * @author Patrick Robinson, Nick Herrmannsdörfer, Erwin Stamm
 */
public class Command {
    /**
     *
     */
    public int[] State;
    /**
     *
     */
    public int FirstIndex;
    /**
     *
     */
    public int SecondIndex;
    /**
     *
     */
    public Instruction Type;

    /**
     *
     * @param state
     * @param first
     * @param second
     * @param type
     */
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
