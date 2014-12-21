package cupl;


public class Util
{	
	/**
	 * Counts number of 1 bits in a 32 bit unsigned number.
	 * @param x unsigned 32 bit number whose bits you wish to count.
	 * @return number of 1 bits in x.
	 */
	public static int countBits( int x )
	{
	   // collapsing partial parallel sums method
	   // collapse 32x1 bit counts to 16x2 bit counts, mask 01010101
	   x = (x >>> 1 & 0x55555555) + (x & 0x55555555);
	   // collapse 16x2 bit counts to 8x4 bit counts, mask 00110011
	   x = (x >>> 2 & 0x33333333) + (x & 0x33333333);
	   // collapse 8x4 bit counts to 4x8 bit counts, mask 00001111
	   x = (x >>> 4 & 0x0f0f0f0f) + (x & 0x0f0f0f0f);
	   // collapse 4x8 bit counts to 2x16 bit counts
	   x = (x >>> 8 & 0x00ff00ff) + (x & 0x00ff00ff);
	   // collapse 2x16 bit counts to 1x32 bit count
	   return(x >>> 16) + (x & 0x0000ffff);
	}
	
	
	public static boolean[] getBits(int state, int length)
	{
		boolean[] bits = new boolean[length];

		for (int i = 0; i < length; i++)
		{
			bits[length - 1 - i] = (state & 1) == 1;
			state = state >> 1;
		}
		
		return bits;
	}
	
	public static boolean isValidJohnson(boolean[] bits)
	{
		boolean first = bits[0];
		boolean swapped = false;
		boolean valid = true;
		for (int i = 1; i < bits.length; i++)
		{
			if (!swapped && bits[i] != first)
			{
				swapped = true;
			}
			else if (swapped && bits[i] == first)
			{
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	public static int findJohnsonSwapPoint(boolean[] bits)
	{
		int swapPoint = 0;
		boolean first = bits[0];
		for (int i = 1; i < bits.length; i++)
		{
			if (bits[i] != first) // pos i is different from pos i - 1
			{
				swapPoint = i;
				break;
			}
		}
		
		return swapPoint;
	}
	
	public static int bitsToInt(boolean[] bits)
	{
		int n = 0, l = bits.length;
		for (int i = 0; i < l; ++i) {
		    n = (n << 1) + (bits[i] ? 1 : 0);
		}
		return n;
	}
}
