package os;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import CPU.CPU;
import DMA.IOdevice;
import Loader.Loader;
import Manager.MemoryManager;
import Manager.ProcessManager;
import Manager.UXManager;

public class Window extends JFrame {
	private JPanel mainP;
	private BackgroundP centerP;
	private JPanel bottomP;
	private ImageIcon image;

	private JButton startkey;
	private JButton Process1;
	private JButton Process2;
	private JButton Process3;
	private JButton Process4;
	private JButton Process5;
	private JButton Process6;

	private UXManager uxManager;
	private Loader loader;
	private ProcessManager processManager;
	private MemoryManager memoryManager;

	private CPU cpu;
	private IOdevice IOdevice;
	
	private ExecutePanel EXP;
	private ActionHandler handler;

	
	public Window(CPU cpu, IOdevice IOdevice) {

		this.cpu = cpu;
		this.uxManager = new UXManager();
		this.loader = new Loader();
		this.processManager = new ProcessManager();
		this.memoryManager = new MemoryManager();
		this.IOdevice = IOdevice;
		this.associate();

		this.image = new ImageIcon("image/image.png");
		this.setIconImage(image.getImage());
		this.setTitle("Windows");
		this.mainP = new JPanel();
		this.mainP.setLayout(new BorderLayout());
		this.setSize(this.getWidth(), this.getHeight());

		this.centerP = new BackgroundP(new ImageIcon("image/img.jpg").getImage());
		this.centerP.setLayout(new FlowLayout(FlowLayout.LEADING));

		this.handler = new ActionHandler();

		this.Process1 = new JButton(new ImageIcon("image/p1.png"));
		this.Process1.setContentAreaFilled(false);
		this.Process1.setActionCommand("p1");
		this.Process1.addActionListener(handler);
		this.Process2 = new JButton(new ImageIcon("image/p2.png"));
		this.Process2.setContentAreaFilled(false);
		this.Process2.setActionCommand("p2");
		this.Process2.addActionListener(handler);
		this.Process3 = new JButton(new ImageIcon("image/p3.png"));
		this.Process3.setContentAreaFilled(false);
		this.Process3.setActionCommand("p3");
		this.Process3.addActionListener(handler);
		this.Process4 = new JButton(new ImageIcon("image/p4.png"));
		this.Process4.setContentAreaFilled(false);
		this.Process4.setActionCommand("p4");
		this.Process4.addActionListener(handler);
		this.Process5 = new JButton(new ImageIcon("image/p5.png"));
		this.Process5.setContentAreaFilled(false);
		this.Process5.setActionCommand("p34"); // 3과4
		this.Process5.addActionListener(handler);
		this.Process6 = new JButton(new ImageIcon("image/p6.png"));
		this.Process6.setContentAreaFilled(false);
		this.Process6.setActionCommand("p51"); // 5와1
		this.Process6.addActionListener(handler);

		this.centerP.add(Process1);
		this.centerP.add(Process2);
		this.centerP.add(Process3);
		this.centerP.add(Process4);
		this.centerP.add(Process5);
		this.centerP.add(Process6);

		this.bottomP = new JPanel();
		this.bottomP.setBackground(Color.DARK_GRAY);
		this.bottomP.setLayout(new BorderLayout());

		this.startkey = new JButton("종료");
		this.startkey.setBackground(Color.DARK_GRAY);
		this.startkey.setBorderPainted(false);
		this.startkey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		this.EXP = new ExecutePanel();
		EXP.setSize(250, this.mainP.getHeight());
		this.mainP.add(EXP, BorderLayout.EAST);

		this.bottomP.add(this.startkey, BorderLayout.WEST);
		this.mainP.add(this.centerP);
		this.mainP.add(this.bottomP, BorderLayout.SOUTH);
		this.add(mainP);

		this.setSize(900, 700);
		this.setLocation(420, 0);
		this.setVisible(true);

		this.processManager.associate2(EXP);
	}

	public void associate() {
		this.uxManager.associate(this.loader, this.processManager);
		this.processManager.associate(this.memoryManager, this.cpu,this.IOdevice);
	}

	public void run(String Pname) {
		this.uxManager.run(Pname);
	}

	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			run(e.getActionCommand());

		}

	}
}