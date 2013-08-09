package icfp.Functions;

public class NotFunction implements Function {
	public long run(long... inputs)
	{
		return ~inputs[0];
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
		return "(not " + inputs[0] + ")";
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}