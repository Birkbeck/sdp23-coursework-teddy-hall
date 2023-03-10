//edward hall
//ehall18

package sml;

import sml.instruction_types.AddInstruction;

import java.lang.reflect.Constructor;
import java.util.*;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;


public class InstructionReflector {
    private static InstructionReflector mirror;

    private InstructionReflector() {
    }

    public static InstructionReflector MirrorGetter() {
        if (mirror == null)
        {
            mirror = new InstructionReflector();
            return mirror;
        }
        else
            return mirror;
    }

    public Instruction Reflector(String opcode, String[] args) throws ClassNotFoundException {
        String InstructionCall = "sml.instruction_types."+opcode.substring(0,1).toUpperCase()+opcode.substring(1,3)+"Instruction";
        Constructor<?>[] constructors;

        try {
            constructors = Class.forName(InstructionCall).getDeclaredConstructors();
        } catch (ClassNotFoundException e) {
            throw e;
        }

        Class<?>[] expected_parameter_types = constructors[0].getParameterTypes();
        ArrayList<Object> parameters = new ArrayList<>();
        for (int i =0; i < constructors[0].getParameterCount(); i++) {
            if (expected_parameter_types[i].getName().equals("int")) {
                parameters.add(Integer.parseInt(args[i]));
            }
            else if (expected_parameter_types[i].getName().equals("sml.RegisterName")) {
                parameters.add(Registers.Register.valueOf(args[i]));
            }
            else if (expected_parameter_types[i].getName().equals("java.lang.String")) {
                parameters.add(args[i]);
            }
            else {
                throw new IllegalArgumentException("There is a problem with the way the arguments are written");
            }
        }

        try {
            Instruction return_instruction = (Instruction) constructors[0].newInstance(parameters.toArray());
            return return_instruction;
        }catch(Exception e){System.out.println("instance failed to construct");}
        System.out.println("reflector failed");
        return null;
    }
}