package icfp.Functions;

public class Shr16Function implements Function {
	public long run(long... inputs)
	{
		return inputs[0] >>> 16;
	}
	
	public int getInputCount()
	{
		return 1;
	}
	
	public int getSize()
	{
		return 1;
	}
}
