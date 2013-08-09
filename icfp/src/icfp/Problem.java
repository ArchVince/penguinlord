package icfp;

import java.util.ArrayList;

import icfp.Functions.*;

public class Problem {

	private final int MAX_SUBTREE_SIZE = 5;
	private final int MAX_TREE_DEPTH = 5;
	
	private class Result
	{
		int[] indexes;
		long result;
	}
	
	private class Variable
	{
		String name;
		long value;
		int layerAdded;
		
		public Variable(String name, long value, int layerAdded)
		{
			this.name = name;
			this.value = value;
			this.layerAdded = layerAdded;
		}
	}
	
	private int size;
	private String[] operators;
	private int index;
	private ArrayList<Variable> availableVariables;
	private ArrayList<FunctionNode> outerLayer;
	private ArrayList<FunctionNode> innerLayer;
	private ArrayList<Integer> solutions;
	private long[] outerValues;
	private ArrayList<Result[]> innerValues;
	
	
	public Problem(int size, String[] operators)
	{
		this.size = size;
		this.operators = operators;
		availableVariables.add(new Variable("x_0", 0, -1));
		availableVariables.add(new Variable("0", 0, -1));
		availableVariables.add(new Variable("1", 1, -1));
	}
	
	private ArrayList<FunctionNode> buildAllTrees(int maxSize, boolean ending)
	{
		ArrayList<FunctionNode> trees = new ArrayList<FunctionNode>();
		//TODO: actually build the trees
		return trees;
	}
	
	public FunctionNode setInputValues(FunctionNode tree, long... inputs)
	{
		if(inputs.length == 1)
		{
			for(int i = 0; i < tree.endNodes.size(); i++)
			{
				switch(tree.endNodes.get(i).function.getInputCount())
				{
					case 1:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[0]) 
																				 }; break;
					case 2:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[0]),  
																					 new ConstantNode(inputs[0])
																				 }; break;
					case 3:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[0]),  
																					 new ConstantNode(inputs[0]), 
																					 new ConstantNode(inputs[0])
																				 }; break;
					default:
						System.out.println("WARNING: default used on inputCount: " + tree.endNodes.get(i).function.getInputCount());
				}
			}
		}
		else
		{
			int inputCounter = 0;
			for(int i = 0; i < tree.endNodes.size(); i++)
			{
				switch(tree.endNodes.get(i).function.getInputCount())
				{
					case 1:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[inputCounter++]) 
																				 }; break;
					case 2:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[inputCounter++]),  
																					 new ConstantNode(inputs[inputCounter++])
																				 }; break;
					case 3:
						tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																					 new ConstantNode(inputs[inputCounter++]),  
																					 new ConstantNode(inputs[inputCounter++]), 
																					 new ConstantNode(inputs[inputCounter++])
																				 }; break;
					default:
						System.out.println("WARNING: default used on inputCount: " + tree.endNodes.get(i).function.getInputCount());
				}
			}
		}
		return tree;
	}
	
	public String getNextSolution(long[] inputs, long[] outputs)
	{
		if(outerLayer == null)
		{
			outerLayer = buildAllTrees(size % MAX_SUBTREE_SIZE, true);
			innerLayer = buildAllTrees(MAX_SUBTREE_SIZE, false);
		}
		
		boolean foundNew = true;
		while(foundNew)
		{
			findPossibleSolution(inputs[0], outputs[0]);
			foundNew = false;
			while(solutions.size() > 0)
			{
				foundNew = true;
				if(isValid(inputs, outputs, solutions.get(0)))
					return buildProgram(solutions.get(0));
				
				solutions.remove(0);
			}
		}
		return null;
	}
	
	private String buildProgram(int solutionIndex)
	{
		
	}
	
	private boolean isValid(long[] inputs, long[] outputs, int solutionIndex)
	{
		for(int i = 1; i < inputs.length; i++)
		{
			for(int i = 0; i < outerLayer.size(); i++)
				setInputValues(outerLayer.get(i), input);
		}
	}
	
	private void findPossibleSolution(long input, long output)
	{
		if(outerValues == null)
		{
			for(int i = 0; i < outerLayer.size(); i++)
				setInputValues(outerLayer.get(i), input);
			
			outerValues = new long[outerLayer.size()];
					
			if(size <= size % MAX_SUBTREE_SIZE)
			{
				for(int i = 0; i < outerLayer.size(); i++)
				{
					if(outerLayer.get(i).getValue() == output)
						solutions.add((Integer)i);
				}
			}
			else
			{
				for(int i = 0; i < outerLayer.size(); i++)
					outerValues[i] = outerLayer.get(i).getValue();
			}
		}
		if(size <= size % MAX_SUBTREE_SIZE)
		{
			if(solutions.size() == 0)
				throw new IllegalStateException("No solution found");
			return;
		}
		if(innerValues == null)
		{
			innerValues = new ArrayList<Result[]>();
			buildNextResultLayer();
		}
		if(!incrementIndex())
			buildNextResultLayer();
		
		while(solutions.size() > 0 && innerValues.get(innerValues.size() - 1).length > 0)
		{
			if(innerValues.get(innerValues.size() - 1).length == 0)
				throw new IllegalStateException("No solution found");
			
			for(int i = 0; i < innerValues.get(innerValues.size() - 1).length; i++)
			{
				if(innerValues.get(innerValues.size() - 1)[i].result == output)
					solutions.add((Integer)i);
			}
		}
	}
	
	private boolean incrementIndex()
	{
		if(innerValues.size() > 0)
		{
			if(index < innerValues.get(innerValues.size() - 1).length - 1)
			{
				index++;
				return true;
			}
		}
		else if(index < outerLayer.size() - 1)
		{
			index++;
			return true;
		}
		return false;
	}
	
	private void buildNextResultLayer()
	{
		index = -1;
		//TODO: Add new layer
	}
	
	private Function getFunctionFromOp(String op)
	{
		switch(op)
		{
			case "and":
				return new AndFunction();
			case "fold":
			case "tfold":
				return new FoldFunction();
			case "if0":
				return new IfFunction();
			case "not":
				return new NotFunction();
			case "or":
				return new OrFunction();
			case "plus":
				return new PlusFunction();
			case "shl1":
				return new Shl1Function();
			case "shr1":
				return new Shr1Function();
			case "shr4":
				return new Shr4Function();
			case "shr16":
				return new Shr16Function();
			default:
				throw new IllegalArgumentException("Unknown op type");
		}
	}
}
