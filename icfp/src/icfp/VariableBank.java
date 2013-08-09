package icfp;

import java.util.ArrayList;

public class VariableBank {
	
	public static int variableNameCount = 1;
	public static ArrayList<Variable> variables = new ArrayList<Variable>();

	public static String getVariableName()
	{
		return "x_" + (variableNameCount++);
	}
}
