package softwareEngineering;

public class StartGround extends Ground {
	
	public StartGround(int x, int y, int width, int height) {
		super(x, y, width, height);
		gi.setImage("start");
		this.score = false;
	}

	@Override
	public void setImage() {
		gi.setImage("start");
	}

	@Override
	public void resetImage() {
		gi.setImage("start");
	}

}
