package icfp.Functions;

import java.util.ArrayList;

public interface Function {
	public ArrayList<Function> childFunctions = new ArrayList<Function>();
	public abstract long run(long... inputs);
	public abstract int getInputCount();
	public abstract int getSize();
	public abstract String getProgramSegment(String... inputs);
	public abstract String[] getVariableNames();
}
