package anup.shastri.producer;

import java.util.List;

public class Producer implements Runnable{

	
	List<Integer> questionList = null;
	private int questionNo;
	final int LIMIT = 5;
	
	
	
	public Producer(List<Integer> questionList) {
		this.questionList = questionList;
	}

	public void readQuestions(int questionNo) throws InterruptedException {
		synchronized (questionList) {
			while (questionList.size() == LIMIT) {
				System.out.println("Wait we are at the limit...");
				questionList.wait();
			}
		}
		synchronized (questionList) {
			System.out.println("New questions please..."+questionNo);
			questionList.add(questionNo);
			Thread.sleep(100);
			questionList.notify();
		}
	}
	
	

	@Override
	public void run() {
		
		while(true) {
			try {
				readQuestions(questionNo++);
			} catch (InterruptedException e) {
			}
			
		}
		

		
	}

}
