package cupl.counters;
import cupl.Counter;
import cupl.Next;
import cupl.Util;


public class JohnsonDown implements Counter
{
	private int length;
	
	public JohnsonDown(int bits)
	{
		length = bits;
	}
	
	@Override
	public Next getNext(int current)
	{
		// TODO Auto-generated method stub
		boolean[] bits = Util.getBits(current, length);
		if (Util.isValidJohnson(bits))
		{
			int swapPoint = Util.findJohnsonSwapPoint(bits);
			bits[swapPoint] = !bits[swapPoint];
			return new Next(Util.bitsToInt(bits));
		}
		else
		{
			return new Next(0, false);
		}
	}

	@Override
	public String getName()
	{
		return "johndown";
	}	
	
}
