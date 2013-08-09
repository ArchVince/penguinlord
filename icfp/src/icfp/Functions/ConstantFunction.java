package icfp.Functions;

public class ConstantFunction implements Function {
	public long run(long... inputs)
	{
		throw new UnsupportedOperationException("Constants should only be called from the node");
	}
	
	public int getInputCount()
	{
		return 0;
	}
	
	public int getSize()
	{
		return 1;
	}
	
	public String getProgramSegment(String... inputs)
	{
		throw new UnsupportedOperationException("Constants should only be called from the node");
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}
