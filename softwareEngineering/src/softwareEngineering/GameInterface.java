package softwareEngineering;

public interface GameInterface{
	abstract void startGame();		//초기화한 후 첫 시작
	abstract void waitGame();		//일시중지
	abstract void reStart();		//다시 재시작
	abstract void endGame();		//게임종료
}
