/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heapsort;


/**
 *
 * @author autarch
 */
public enum Instruction {
    ComparisonParent,
    ComparisonFlat,
    Swap,
    Finish,
    Init,
    End;

    @Override
    public String toString() {
        switch(this) {
            case ComparisonParent:
                return "ComparisonParent";
            case ComparisonFlat:
                return "ComparisonFlat";
            case Swap:
                return "Swap";
            case Finish:
                return "Finish";
            case Init:
                return "Init";
            case End:
                return "End";
            default:
                return "*@#";
        }
    }
}
