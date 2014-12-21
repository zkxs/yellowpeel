
public class Next
{	
	private int nextState;
	private boolean valid;
	
	Next(int nextState, boolean valid)
	{
		this.nextState = nextState;
		this.valid = valid;
	}
	
	Next(int nextState)
	{
		this(nextState, true);
	}
	
	public int getNextState()
	{
		return nextState;
	}
	
	public boolean isValid()
	{
		return valid;
	}
}
