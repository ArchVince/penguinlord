package icfp.Functions;

import icfp.VariableBank;

public class FoldFunction implements Function {
	
	String[] variables = new String[2];
	
	public long run(long... inputs)
	{
		long output = inputs[1];
		for(int i = 0; i < 64; i += 8)
			output = childFunctions.get(0).run((inputs[0] >>> i) | 255L, output);
		return output;
	}
	
	public int getInputCount()
	{
		return 2;
	}
	
	public int getSize()
	{
		return 2;
	}
	
	public String getProgramSegment(String... inputs)
	{
		variables[0] = VariableBank.getVariableName();
		variables[1] = VariableBank.getVariableName();
		return "(fold " + inputs[0] + " " + inputs[1] + "(lambda (" + variables[0] + " " + variables[1] + ") " + inputs[2] + "))";
	}
	
	public String[] getVariableNames()
	{
		return variables;
	}
}
