//edward hall
//ehall18

package sml.instruction_types;

import sml.*;

import java.util.Objects;

import static sml.Registers.Register.*;


// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String TargetLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String L) {
        super(label, OP_CODE);
        this.source = source;
        this.TargetLabel = L;
    }

    @Override
    public int execute(Machine m) {
        int zero_check = m.getRegisters().get(source);
        if (zero_check == 0) return NORMAL_PROGRAM_COUNTER_UPDATE;
        int TargetAddress = m.getLabels().getAddress(TargetLabel);
        return TargetAddress;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + TargetLabel;
    }

    @Override
    public int hashCode(){
        return Objects.hash(label,opcode,source,TargetLabel);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (obj instanceof JnzInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.opcode, other.opcode)
                    && Objects.equals(this.TargetLabel, other.TargetLabel)
                    && this.source == other.source;
        }
        return false;

    }

}

