package Logic;
import java.util.Random;

public class Question {
	
	public String options[] = new String[4]; 
	public String question;
	private String correct,operator;
	private int a, b;
	private Random rand = new Random();
	
	public Question(int a, int b, String operator){
		this.a = a; this.b = b; this.operator = operator;
		
		for(int i=0;i<4;i++)
			options[i]= "";
		
		int type = rand.nextInt(2);
		
		if (type==1){
			question = new String("? "+operator+" "+b+" = "+getResult());
			correct = new String(""+a);
		} else {
			question = new String(a+" "+operator+" ? = "+getResult());
			correct = new String(""+b);
		}
		buildOptions();
	}
	
	private int getResult(){
		
		if (operator.equals("+"))
			return a + b;
		if (operator.equals("-"))
			return a - b;
		if (operator.equals("/"))
			return a / b;
			return a * b;
	}
	
	private void buildOptions(){
		options[rand.nextInt(4)] = correct;
		for(int i=0;i<4;i++)
			if(options[i].isEmpty())
				options[i] = new String(""+rand.nextInt(10));
	}
	
	
}
