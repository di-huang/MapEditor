package unit_testing.editor;

/**
 * 
 * @author dihuang
 *
 */
public interface Editable{
	
	public boolean isChosen();
	
	public void chosen();
	
	public void finished();
	
	public boolean isWithin(int x_mouse, int y_mouse);
	
}
