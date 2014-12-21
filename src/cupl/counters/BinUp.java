package cupl.counters;
import cupl.Counter;
import cupl.Next;


public class BinUp implements Counter
{
	private int max;
	
	public BinUp(int max)
	{
		this.max = max;
	}

	@Override
	public Next getNext(int current)
	{
		int next = current + 1;
		if (next > max) return new Next(0);
		else return new Next(next);
	}

	@Override
	public String getName()
	{
		return "binup";
	}	
	
}
