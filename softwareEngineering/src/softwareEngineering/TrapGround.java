package softwareEngineering;

public class TrapGround extends Ground{
	private int cur_Trap;
	
	public TrapGround(int x, int y, int width, int height) {
		super(x, y, width, height);
		cur_Trap = Ground.FALL;
		gi.setImage("trap");
		this.score = false;
	}

	@Override
	public int activeTrap() {
		return cur_Trap;
	}
	
	
}
