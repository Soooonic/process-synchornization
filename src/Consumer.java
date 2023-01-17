import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class Consumer implements Runnable{
	protected int current = 0;
	protected int numbersConsumed = 0;
	protected long startTime;
	protected Buffer b;
	protected FileWriter writer;
	protected MyFrame frame;
	
	Consumer(MyFrame frame, String fileName, Buffer b){
		this.startTime = System.currentTimeMillis();
		this.b = b;
		this.frame = frame;
		try {
			writer = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(!b.isReachedEnd()) {
			current = b.consume();
			numbersConsumed += 1;
			writeToFile();
			updateUI();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeToFile() {
		try {
			writer.append(", \"" + Integer.toString(current) + "\" ");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void updateUI() {
		this.frame.update(current, numbersConsumed, (System.currentTimeMillis() - startTime));
	}
}