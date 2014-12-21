package cupl.counters;
import cupl.Counter;
import cupl.Next;


public class Clear implements Counter
{
	@Override
	public Next getNext(int current)
	{
		return new Next(0);
	}

	@Override
	public String getName()
	{
		return "clear";
	}	
	
}
