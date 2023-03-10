//edward hall
//ehall18

package sml;

import sml.instruction_types.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;
import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;



/*
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */


public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabelfromcurrentline();

                Instruction instruction = getInstructionfromcurrentline(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }



    /*
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */

    private Instruction getInstructionfromcurrentline(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String result = scan();
        String source = scan();

        String[] myStringArray = {label, result, source};

        InstructionReflector rawInstruction = InstructionReflector.MirrorGetter();



        try {
                return rawInstruction.Reflector(opcode, myStringArray);
            } catch (Exception e){
            System.out.println("Could not create instruction described");
        };

        System.out.print("failed to read instruction from line");
        return null;
    }


    // TODO: add code for all other types of instructions: DONE

    // TODO: Then, replace the switch by using the Reflection API: DONE

    // TODO: Next, use dependency injection to allow this machine class
    //       to work with different sets of opcodes (different CPUs)


    private String getLabelfromcurrentline() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */

    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }

}


//switch code replaced by reflection
        /*
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case JnzInstruction.OP_CODE -> {
                String s = scan();
                String L = scan();
                return new JnzInstruction(label, Register.valueOf(s), L);
            }
            case MulInstruction.OP_CODE -> {
                String s = scan();
                String r = scan();
                return new MulInstruction(label, Register.valueOf(s), Register.valueOf(r));
            }
            case OutInstruction.OP_CODE -> {
                String s = scan();
                return new OutInstruction(label, Register.valueOf(s));
            }
            case MovInstruction.OP_CODE -> {
                String r = scan();
                String x = scan();
                return new MovInstruction(label, Register.valueOf(r), Integer.parseInt(x));
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

                        default -> {
                System.out.println("Unknown instruction: " + opcode);
            }

            */