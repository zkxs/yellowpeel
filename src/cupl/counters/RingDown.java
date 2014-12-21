package cupl.counters;
import cupl.Counter;
import cupl.Next;
import cupl.Util;


public class RingDown implements Counter
{
	private int defaultState;
	private int finalState = 1;
	
	public RingDown(int bits)
	{
		defaultState = (int)Math.pow(2, bits - 1);
	}
	
	@Override
	public Next getNext(int current)
	{
		if (Util.countBits(current) == 1) // then valid
		{
			if (current == finalState) // if final state
			{
				return new Next(defaultState);
			}
			else
			{
				return new Next(current >>> 1);
			}
				
		}
		else // invalid
		{
			return new Next(defaultState, false);
		}
	}
	
	@Override
	public String getName()
	{
		return "ringdown";
	}
	
}
