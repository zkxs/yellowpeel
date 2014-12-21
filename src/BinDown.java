
public class BinDown implements Counter
{
	private int max;
	
	public BinDown(int max)
	{
		this.max = max;
	}

	@Override
	public Next getNext(int current)
	{
		int next = current - 1;
		if (next < 0) return new Next(max);
		else return new Next(next);
	}

	@Override
	public String getName()
	{
		return "bindown";
	}	
	
}
