package cupl;

public class Next
{	
	private int nextState;
	private boolean valid;
	
	public Next(int nextState, boolean valid)
	{
		this.nextState = nextState;
		this.valid = valid;
	}
	
	public Next(int nextState)
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
