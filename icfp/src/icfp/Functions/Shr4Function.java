package icfp.Functions;

public class Shr4Function implements Function {
	public long run(long... inputs)
	{
		return inputs[0] >> 4;
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
