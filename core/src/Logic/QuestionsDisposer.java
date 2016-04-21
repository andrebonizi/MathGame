package Logic;

import java.util.Random;

public class QuestionsDisposer {
	private String answer;
	private Random rand;
	
	public QuestionsDisposer(int playerLevel) {
		rand = new Random();
	}
	
	public Question getQuestion(){
		String oper[] = new String[]{"+","-","/","*" };
		return new Question(rand.nextInt(10),rand.nextInt(10),oper[rand.nextInt(4)]);
	}
}
