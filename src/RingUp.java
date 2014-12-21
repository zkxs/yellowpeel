
public class RingUp implements Counter
{
	private int defaultState = 1;
	private int finalState;
	
	public RingUp(int bits)
	{
		finalState = (int)Math.pow(2, bits - 1);
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
				return new Next(current << 1);
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
		return "ringup";
	}
	
	
}
