package os;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import CPU.CPU;
import DMA.IOdevice;

public class BIOS extends JFrame {

	
	private JPanel initP;
	private JTextArea dos;
	private Window window;

	private IOdevice IOdevice;
	
	
	public BIOS(CPU cpu) {

		this.setTitle("BIOS");
		this.initP = new JPanel();
		this.initP.setBackground(Color.black);
		this.initP.setSize(this.getWidth(), this.getHeight());
		this.initP.setLayout(new BorderLayout());

		this.dos = new JTextArea();
		this.dos.setSize(this.getWidth(), this.getHeight());
		this.dos.setOpaque(true);
		this.dos.setForeground(Color.white);
		this.dos.setBackground(Color.black);

		this.initP.add(this.dos, BorderLayout.CENTER);
		this.add(initP);

		this.setBackground(Color.black);
		this.setSize(900, 600);
		this.setLocation(150, 30);
		this.setVisible(true);

		try {
			Thread.sleep(500);
			this.dos.append("BIOS \n");
			this.dos.append("------------------------------------------ \n");

			Thread.sleep(1000);
			this.dos.append("Load Operation... \n");
			Thread.sleep(1000);
			this.dos.append("Find OS! \n");
			Thread.sleep(1000);
			this.dos.append("Starting Operation... \n");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}

		this.dispose();
		this.window = new Window(cpu,IOdevice);
	}
	
	
}
