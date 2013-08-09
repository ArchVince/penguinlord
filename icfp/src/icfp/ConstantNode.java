package icfp;

public class ConstantNode extends FunctionNode {

	long constant;
	
	public ConstantNode(long constant)
	{
		super(null, null);
	}
	
	@Override
	public long getValue()
	{
		return constant;
	}
}
