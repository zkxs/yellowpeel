import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Main
{	
	public static void main(String[] args)
	{
		String filename = "GENERATED.PLD";
		int numberOfBits = 6;
		int numberOfStates = (int)Math.pow(2, numberOfBits);
		
		Counter[] counters = {
			new BinUp(numberOfStates - 1),
			new BinDown(numberOfStates - 1),
			new RingUp(6),
			new RingDown(6),
			new JohnsonUp(6),
			new JohnsonDown(6)
		};
		
		try
		{
		
			File file = new File(filename);
			PrintStream out = new PrintStream(file);
			
			// print the table of state names
			{
				out.println("/* define counter states */");
				String bitFormatSting = "%" + numberOfBits + "s";
				for (int stateNumber = 0; stateNumber < numberOfStates; stateNumber++)
				{
					out.printf("$define %-3s 'b'%s\n", "S" + stateNumber, String.format(bitFormatSting, Integer.toBinaryString(stateNumber)).replace(' ', '0'));
				}
				out.println();
			}
			
			// print each state and its successors
			{
				out.println("Sequenced count { /* free running counter */");
				for (int stateNumber = 0; stateNumber < numberOfStates; stateNumber++)
				{
					out.printf("\tPRESENT S%d\n", stateNumber);
					for (Counter c : counters)
					{
						Next next = c.getNext(stateNumber);
						out.printf("\t\tif %-15s next %-5s", c.getName(), "S" + next.getNextState() + ";");
						if (!next.isValid()) out.print(" /* invalid */");
						out.println();
					}
				}
				out.println("}");
			}
			
			System.out.println("Done.");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
