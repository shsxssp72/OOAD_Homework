package gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * @author 10645
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class GUI {

	/**
	 * The list of parameters for UIManager.setLookAndFeel()
	 * index = 0, Motif
	 * index = 1, Mac (os required)
	 * index = 2, GTK (os required)
	 * index = 3, cross-platform
	 * index = 4, current os
	 * index = 5, Metal (Default)
	 * index = 6, Windows
	 * index = 7, Windows Classic
	 */
	private List<String> lookAndFeel = Arrays.asList("com.sun.java.swing.plaf.motif.MotifLookAndFeel",
			"com.sun.java.swing.plaf.mac.MacLookAndFeel",
			"com.sun.java.swing.plaf.gtk.GTKLookAndFeel",
			UIManager.getCrossPlatformLookAndFeelClassName(),
			UIManager.getSystemLookAndFeelClassName(),
			"javax.swing.plaf.metal.MetalLookAndFeel",
			"com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
			"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

	public GUI() {
		this.initialize();
		fileMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {

			}

			@Override
			public void menuDeselected(MenuEvent e) {

			}

			@Override
			public void menuCanceled(MenuEvent e) {

			}
		});
	}

	private void setLookAndFeel(int selection) {
		try {
			UIManager.setLookAndFeel(lookAndFeel.get(selection));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
//		Set LookAndFeel to Windows
		int lookAndFeelType = 6;
		setLookAndFeel(lookAndFeelType);

		JFrame frame = new JFrame("GUI");
		frame.setContentPane(this.mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1280, 800);
		frame.setVisible(true);
		System.out.println("Main Panel is created!");
	}

	private JPanel mainPanel;
	@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
	private JButton OKButton;
	private JButton stopButton;
	private JPanel menuPanel;
	private JMenuBar mainMenu;
	private JMenu fileMenu;
	private JMenuItem loadSelection;
	private JMenuItem saveSelection;
	private JMenuItem newSelection;
	private JPanel buildPanel;
	private JPanel runPanel;
	private JPanel playgroundPanel;

	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
}
