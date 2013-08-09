package icfp.Functions;

public class IfFunction implements Function {
	public long run(long... inputs)
	{
		return ((inputs[0] | 1L) == 1L ? inputs[1] : inputs[2]);
	}
	
	public int getInputCount()
	{
		return 3;
	}
	
	public int getSize()
	{
		return 1;
	}
}
