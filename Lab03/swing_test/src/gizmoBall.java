import javax.swing.*;

public class gizmoBall {
	public static void main(String[] args) {
		JFrame frame = new JFrame("gizmoBall");
		frame.setContentPane(new gizmoBall().panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1280, 800);
		frame.setVisible(true);
	}

	private JPanel panel1;
	private JButton OKButton;
}
