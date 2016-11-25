package softwareEngineering;

import javax.swing.JFrame;

public class HardPlay extends NormalPlay {

	HardPlay(JFrame frame, BackGroundCanvas main_Pane) {
		super(frame, main_Pane);
		gm.getMadeClass().setTrapLevel(50);
		gm.setHard(true);
		pm.setHard(true);
		this.level = "Hard";
	}

}
