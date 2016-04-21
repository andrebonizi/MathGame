package Logic;

public class QuestionsDisposer {
	private String answer;
	
	public QuestionsDisposer(int playerLevel) {
		
	}
	
	public Question getQuestion(){
		return new Question(rand.nextInt(10),rand.nextInt(10));
	}
}
