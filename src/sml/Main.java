//edward hall
//ehall18

package sml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
	/**
	 * Initialises the system and executes the program.
	 *
	 * @param args name of the file containing the program text.
	 */
	public static void main(String... args) {

		if (args.length != 1) {
			System.err.println("Incorrect number of arguments - Machine <file> - required");
			System.exit(-1);
		}


		Translator t = new Translator(args[0]);
		Machine m = new Machine(new Registers());
	try{
		t.readAndTranslate(m.getLabels(), m.getProgram());
	} catch (IOException e) {
		System.out.println("File not found.");
		e.printStackTrace();
	}
	System.out.println("Here is the program; it has " + m.getProgram().size() + " instructions.");
	System.out.println(m);

	System.out.println("Beginning program execution.");
	m.execute();
	System.out.println("Ending program execution.");

	System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");

	}
}
