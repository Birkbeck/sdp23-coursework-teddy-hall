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

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
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
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass()) {
			return false;
		}

		if (obj instanceof AddInstruction other) {
			return Objects.equals(this.label, other.label)
					&& Objects.equals(this.opcode, other.opcode)
					&& Objects.equals(this.result, other.result)
					&& this.source == other.source;
		}
		return false;

	}

}


