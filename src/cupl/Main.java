package cupl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import cupl.counters.BinDown;
import cupl.counters.BinUp;
import cupl.counters.Clear;
import cupl.counters.JohnsonDown;
import cupl.counters.JohnsonUp;
import cupl.counters.RingDown;
import cupl.counters.RingUp;


public class Main
{	
	public static void main(String[] args)
	{
		String filename = "COUNT63.PLD";
		int numberOfBits = 6;
		int numberOfStates = (int)Math.pow(2, numberOfBits);
		
		Counter[] counters = {
			new BinUp(numberOfStates - 1),
			new BinDown(numberOfStates - 1),
			new RingUp(6),
			new RingDown(6),
			new JohnsonUp(6),
			new JohnsonDown(6),
			new Clear()
		};
		
		try
		{
			File file = new File(filename);
			PrintStream out = new PrintStream(file);
			
			// write the header
			{
				File headerFile = new File("HEADER.PLD");
				Scanner header = new Scanner(headerFile);
				while (header.hasNextLine())
				{
					out.println(header.nextLine());
				}
				header.close();
				out.println();
			}
			
			// write the table of state names
			{
				out.println("/* define counter states */");
				String bitFormatSting = "%" + numberOfBits + "s";
				for (int stateNumber = 0; stateNumber < numberOfStates; stateNumber++)
				{
					if ( stateNumber != 0 && stateNumber%16==0 ) out.println();
					out.printf("$define %-3s 'b'%s\n", "S" + String.format("%02d", stateNumber), String.format(bitFormatSting, Integer.toBinaryString(stateNumber)).replace(' ', '0'));
				}
				out.println();
			}
			
			// write each state and its successors
			{
				out.println("Sequenced count { /* free running counter */");
				for (int stateNumber = 0; stateNumber < numberOfStates; stateNumber++)
				{
					out.printf("    PRESENT S%s\n", String.format("%02d", stateNumber));
					for (Counter c : counters)
					{
						Next next = c.getNext(stateNumber);
						out.printf("        if %-15s next %-5s", c.getName(), "S" + String.format("%02d", next.getNextState()) + ";");
						if (!next.isValid()) out.print(" /* invalid */");
						out.println();
					}
					if (stateNumber < numberOfStates - 1) out.println();
				}
				out.println("}\n");
			}
			
			// write the header
			{
				File footerFile = new File("FOOTER.PLD");
				Scanner footer = new Scanner(footerFile);
				while (footer.hasNextLine())
				{
					out.println(footer.nextLine());
				}
				footer.close();
				out.println();
			}
			
			out.close();
			System.out.println("Done.");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
