package icfp.Functions;

public class IfFunction implements Function {
	public long run(long... inputs)
	{
		return (inputs[0] == 0L ? inputs[1] : inputs[2]);
	}
	
	public int getInputCount()
	{
		return 3;
	}
	
	public int getSize()
	{
		return 1;
	}
	
	public String getProgramSegment(String... inputs)
	{
		return "(if0 " + inputs[0] + " " + inputs[1] + " " + inputs[2] + ")";
	}
	
	public String[] getVariableNames()
	{
		return new String[0];
	}
}
