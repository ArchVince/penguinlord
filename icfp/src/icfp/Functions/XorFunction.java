package icfp.Functions;

public class XorFunction implements Function {
	public long run(long... inputs)
	{
		return inputs[0] ^ inputs[1];
	}
	
	public int getInputCount()
	{
		return 2;
	}
	
	public int getSize()
	{
		return 1;
	}
	
	public String getProgramSegment(String... inputs)
	{
		return "(xor " + inputs[0] + " " + inputs[1] + ")";
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}
