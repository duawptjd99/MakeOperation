package os;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ExecutePanel extends JPanel {

	private JPanel MP;
	private JTextArea cmd;

	public ExecutePanel() {

		this.MP = new JPanel();
		this.MP.setSize(this.getWidth(), this.getHeight());
		this.MP.setLayout(new BorderLayout());
		this.MP.setBackground(Color.black);
		this.setBackground(Color.black);

		this.cmd = new JTextArea();
		this.cmd.setSize(this.getWidth(), this.getHeight());
		this.cmd.setOpaque(true);
		this.cmd.setForeground(Color.white);
		this.cmd.setBackground(Color.black);

		
		this.cmd.append("---------------------------------°á°úÃ¢-------------------------------\n");
		this.MP.add(this.cmd, BorderLayout.CENTER);
		this.add(this.MP);
		this.setSize(400, 300);
		this.setVisible(true);

	}

	public void sendcode(String code) {
		this.cmd.append(code + "\n");

	}

}
