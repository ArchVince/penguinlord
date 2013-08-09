package icfp.Functions;

public class FoldFunction implements Function {
		
	public long run(long... inputs)
	{
		long output = inputs[1];
		for(int i = 0; i < 64; i += 8)
			output = childFunctions.get(0).run((inputs[0] >> i) | 255L, output);
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
}
