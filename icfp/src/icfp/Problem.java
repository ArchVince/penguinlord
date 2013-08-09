package icfp;

import java.util.ArrayList;

import icfp.Functions.*;

public class Problem {

	private final int MAX_SUBTREE_SIZE = 5;
	private final int MAX_TREE_DEPTH = 5;
	
	private class Result
	{
		ArrayList<ArrayList<Integer>> indexes;
		long result;
	}
	
	private int size;
	private String[] operators;
	private int index;
	private ArrayList<FunctionNode> outerLayer;
	private ArrayList<FunctionNode> innerLayer;
	private ArrayList<Integer> solutions;
	private long[] outerValues;
	private ArrayList<Result[]> innerValues;
	
	
	public Problem(int size, String[] operators)
	{
		this.size = size;
		this.operators = operators;
		VariableBank.variables.add(new Variable("x_0", 0, -1));
		VariableBank.variables.add(new Variable("0", 0, -1));
		VariableBank.variables.add(new Variable("1", 1, -1));
	}
	
	private ArrayList<FunctionNode> buildAllTrees(int maxSize, boolean ending)
	{
		ArrayList<FunctionNode> trees = new ArrayList<FunctionNode>();
		//TODO: actually build the trees
		return trees;
	}
	
	public FunctionNode setInputValues(FunctionNode tree, long input, String name)
	{
		for(int i = 0; i < tree.endNodes.size(); i++)
		{
			switch(tree.endNodes.get(i).function.getInputCount())
			{
				case 1:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(input, name) 
																			 }; break;
				case 2:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(input, name),  
																				 new ConstantNode(input, name)
																			 }; break;
				case 3:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(input, name),  
																				 new ConstantNode(input, name), 
																				 new ConstantNode(input, name)
																			 }; break;
				default:
					System.out.println("WARNING: default used on inputCount: " + tree.endNodes.get(i).function.getInputCount());
			}
		}
		return tree;
	}
	
	public FunctionNode setInputValues(FunctionNode tree, long[] inputs, String[] programs)
	{
		int inputCounter = 0;
		for(int i = 0; i < tree.endNodes.size(); i++)
		{
			switch(tree.endNodes.get(i).function.getInputCount())
			{
				case 1:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++]) 
																			 }; break;
				case 2:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++]),  
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++])
																			 }; break;
				case 3:
					tree.endNodes.get(i).inputFunctions = new FunctionNode[] { 
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++]),  
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++]), 
																				 new ConstantNode(inputs[inputCounter], programs[inputCounter++])
																			 }; break;
				default:
					System.out.println("WARNING: default used on inputCount: " + tree.endNodes.get(i).function.getInputCount());
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
		Result solution = innerValues.get(innerValues.size() - 1)[solutionIndex];
		
		String[] args = new String[solution.indexes.get(solution.indexes.size() - 1).size()];
		for(int i = 0; i < args.length; i++)
		{
			setInputValues(outerLayer.get(solution.indexes.get(solution.indexes.size() - 1).get(i)), 0, "x_0");
			args[i] = outerLayer.get(solution.indexes.get(solution.indexes.size() - 1).get(i)).getProgram();
		}
		for(int i = solution.indexes.size() - 2; i >= 0; i--)
		{
			String[] nextArgs = new String[solution.indexes.get(i).size()];
			int argsIndex = 0;
			for(int h = 0; h < solution.indexes.get(i).size(); h++)
			{
				switch(innerLayer.get(solution.indexes.get(i).get(h)).function.getInputCount())
				{
					case 1: 
						setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), 0, args[argsIndex++]); break;
					case 2: 
						setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), new long[2], new String[] { args[argsIndex++], args[argsIndex++] }); break;
					case 3: 
						setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), new long[3], new String[] { args[argsIndex++], args[argsIndex++], args[argsIndex++] }); break;
					default:
						System.out.println("WARNING: default used on isValid: " + innerLayer.get(solution.indexes.get(i).get(h)).function.getInputCount());
				}
				if(argsIndex != args.length)
					System.out.println("WARNING: Not all args used");
				
				nextArgs[h] = innerLayer.get(solution.indexes.get(i).get(h)).getProgram();
			}
			args = nextArgs;
		}
		if(args.length != 1)
			System.out.println("WARNING: Multiple results");
		
		return args[0];
	}
	
	private boolean isValid(long[] inputs, long[] outputs, int solutionIndex)
	{
		Result solution = innerValues.get(innerValues.size() - 1)[solutionIndex];
		for(int j = 0; j < inputs.length; j++)
		{
			long[] args = new long[solution.indexes.get(solution.indexes.size() - 1).size()];
			for(int i = 0; i < args.length; i++)
			{
				setInputValues(outerLayer.get(solution.indexes.get(solution.indexes.size() - 1).get(i)), inputs[j], "x_0");
				args[i] = outerLayer.get(solution.indexes.get(solution.indexes.size() - 1).get(i)).getValue();
			}
			for(int i = solution.indexes.size() - 2; i >= 0; i--)
			{
				long[] nextArgs = new long[solution.indexes.get(i).size()];
				int argsIndex = 0;
				for(int h = 0; h < solution.indexes.get(i).size(); h++)
				{
					switch(innerLayer.get(solution.indexes.get(i).get(h)).function.getInputCount())
					{
						case 1: 
							setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), args[argsIndex++], ""); break;
						case 2: 
							setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), new long[] { args[argsIndex++], args[argsIndex++] }, new String[] { "", "" }); break;
						case 3: 
							setInputValues(innerLayer.get(solution.indexes.get(i).get(h)), new long[] { args[argsIndex++], args[argsIndex++], args[argsIndex++] }, new String[] { "", "", "" }); break;
						default:
							System.out.println("WARNING: default used on isValid: " + innerLayer.get(solution.indexes.get(i).get(h)).function.getInputCount());
					}
					if(argsIndex != args.length)
						System.out.println("WARNING: Not all args used");
					
					nextArgs[h] = innerLayer.get(solution.indexes.get(i).get(h)).getValue();
				}
				args = nextArgs;
			}
			if(args.length != 1)
				System.out.println("WARNING: Multiple results");
			
			if(args[0] != outputs[j])
				return false;
		}
		return true;
	}
	
	private void findPossibleSolution(long input, long output)
	{
		if(outerValues == null)
		{
			for(int i = 0; i < outerLayer.size(); i++)
				setInputValues(outerLayer.get(i), input, "x_0");
			
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
