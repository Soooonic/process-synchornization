import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener{
	protected JPanel panel;
	protected JTextField numberInput, bufferSizeInput, fileName;
	protected JLabel numLabel, bufferLabel, fileLabel, maxNumLabel, eleCountLabel, timeLabel;
	protected JButton start;
	protected int maxnumberInput, bufSize;
	protected String outputFileName;
	
	MyFrame(){
		this.setTitle("Prime Numbers Generator");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		
		numLabel = new JLabel("N");
		numLabel.setBounds(10, 20, 80, 25);
		panel.add(numLabel);
		
		numberInput = new JTextField(10); 
		numberInput.setBounds(100, 20, 165, 25);
		panel.add(numberInput);
		
		bufferLabel = new JLabel("Buffer Size");
		bufferLabel.setBounds(10, 50, 80, 25);
		panel.add(bufferLabel);
		
		bufferSizeInput = new JTextField(10); 
		bufferSizeInput.setBounds(100, 50, 165, 25);
		panel.add(bufferSizeInput);
		
		fileLabel = new JLabel("Output File");
		fileLabel.setBounds(10, 80, 80, 25);
		panel.add(fileLabel);
		
		fileName = new JTextField(10); 
		fileName.setBounds(100, 80, 165, 25);
		panel.add(fileName);
		
		
		start = new JButton("Start");
		start.setBounds(100, 110, 125, 24);
		start.setPreferredSize(new Dimension(100, 22));
		start.addActionListener(this);
		panel.add(start);
		
		maxNumLabel = new JLabel("Max number found");
		maxNumLabel.setBounds(10, 150, 280, 25);
		panel.add(maxNumLabel);
		
		eleCountLabel = new JLabel("# of prime numbers found");
		eleCountLabel.setBounds(10, 180, 280, 25);
		panel.add(eleCountLabel);
		
		timeLabel = new JLabel("Time elapsed");
		timeLabel.setBounds(10, 210, 280, 25);
		panel.add(timeLabel);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			try {
				maxnumberInput = Integer.parseInt(numberInput.getText());
				bufSize = Integer.parseInt(bufferSizeInput.getText());
				outputFileName = fileName.getText();
				Buffer b = new Buffer(bufSize);
				Thread producerThread = new Thread(new Producer(b, maxnumberInput));
				Thread consumerThread = new Thread(new Consumer(this, outputFileName, b));
				producerThread.start();
				consumerThread.start();
			}catch(Exception excp) {}
		}
	}
	
	public void update(int maxN, int count, long time) {
		maxNumLabel.setText("Max number found " + Integer.toString(maxN));
		eleCountLabel.setText("# of prime numbers found " + Integer.toString(count));
		timeLabel.setText("Time elapsed " + Long.toString(time) + " ms");
	}
}