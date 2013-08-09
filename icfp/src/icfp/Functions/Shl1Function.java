package icfp.Functions;

public class Shl1Function implements Function {
	public long run(long... inputs)
	{
		return inputs[0] << 1;
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
