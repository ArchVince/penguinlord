package icfp;

import java.util.ArrayList;

import icfp.Functions.Function;

public class FunctionNode {
	public Function function;
	public FunctionNode[] inputFunctions;
	public ArrayList<FunctionNode> endNodes;
	
	public FunctionNode(Function function, FunctionNode... inputFunctions)
	{
		this.function = function;
		this.inputFunctions = inputFunctions;
		for (FunctionNode node : inputFunctions)
			endNodes.addAll(node.endNodes);
	}
	
	public long getValue()
	{
		switch(function.getInputCount())
		{
			case 1:
				return function.run(inputFunctions[0].getValue());
			case 2:
				return function.run(inputFunctions[0].getValue(), inputFunctions[1].getValue());
			case 3:
				return function.run(inputFunctions[0].getValue(), inputFunctions[1].getValue(), inputFunctions[2].getValue());
			default:
				throw new IllegalArgumentException("Invalid number of inputs in function");
		}
	}
	
	public int getTotalSize()
	{
		if(inputFunctions.length == 0)
			return function.getSize();
		
		int total = 0;
		for(int i = 0; i < inputFunctions.length; i++)
			total += inputFunctions[i].getTotalSize() + function.getSize();
		return total;
	}
}
