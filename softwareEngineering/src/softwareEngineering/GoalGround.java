package softwareEngineering;

import java.awt.Color;

public class GoalGround extends Ground{

	public GoalGround(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getLabel().setSize(50, 1000);
		this.getLabel().setBackground(Color.GREEN);
		this.getLabel().setOpaque(true);
		this.score = false;
		gi.setImage("");
	}

	@Override
	public void setImage() {
		gi.setImage("");
	}

	@Override
	public void resetImage() {
		gi.setImage("");
	}

}
