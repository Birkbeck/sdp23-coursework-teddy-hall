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

public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int reg_content = m.getRegisters().get(source);
        System.out.println(reg_content);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public int hashCode(){
        return Objects.hash(label,opcode,source);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (obj instanceof OutInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.opcode, other.opcode)
                    && this.source == other.source;
        }
        return false;
    }

}
