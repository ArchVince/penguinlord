package icfp.Functions;

public class OrFunction implements Function {
	public long run(long... inputs)
	{
		return inputs[0] | inputs[1];
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
		return "(or " + inputs[0] + " " + inputs[1] + ")";
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}
