//edward hall
//ehall18

package sml.instruction_types;
import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

import static sml.Registers.Register.*;


// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int source;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int x) {
        super(label, OP_CODE);
        this.result = result;
        this.source = x;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(this.result, this.source);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public int hashCode(){
        return Objects.hash(label,opcode,source,result);
    }

    @Override
    public boolean equals(Object obj){
        if (getClass() != obj.getClass()){
            return false;
        }

        if (obj instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.opcode, other.opcode)
                    && Objects.equals(this.result, other.result)
                    && this.source == other.source;
        }
        return false;



    }
}
