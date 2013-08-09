package icfp.Functions;

public class Shr1Function implements Function {
	public long run(long... inputs)
	{
		return inputs[0] >>> 1;
	}
	
	public int getInputCount()
	{
		return 1;
	}
	
	public int getSize()
	{
		return 1;
	}
	
	public String getProgramSegment(String... inputs)
	{
		return "(shr1 " + inputs[0] + ")";
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}
