package icfp;

public class ConstantNode extends FunctionNode {

	long constant;
	String name;
	
	public ConstantNode(long constant, String name)
	{
		super(null, null);
		this.constant = constant;
		this.name = name;
	}
	
	@Override
	public long getValue()
	{
		return constant;
	}
	
	@Override
	public String getProgram()
	{
		return name;
	}
	
	@Override
	public int getTotalSize()
	{
		return 1;
	}
}
