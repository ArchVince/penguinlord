package icfp.Functions;

public class PlusFunction implements Function {
	public long run(long... inputs)
	{
		return inputs[0] + inputs[1];
	}
	
	public int getInputCount()
	{
		return 2;
	}
	
	public int getSize()
	{
		return 1;
	}
}
