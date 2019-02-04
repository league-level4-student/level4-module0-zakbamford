package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener, ActionListener {
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private ColorSelectionPanel csp;
	private JButton save;

	private static final String FILE_NAME = "src/_02_Pixel_Art/save.dat";

	public void start() {
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);

		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		save = new JButton("Save");
		Save s;
		try {
			FileReader fr = new FileReader(new File(FILE_NAME));
			s = load(FILE_NAME);
			gp.setGrid(s.grid);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(save);
		gp.repaint();
		gp.addMouseListener(this);
		save.addActionListener(this);
		window.pack();
	}

	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			System.out.println("here");
			Save gridSave = new Save(gp.getGrid());
			save(gridSave);
		}
	}

	private static void save(Save save) {
		try (FileOutputStream fos = new FileOutputStream(new File(FILE_NAME));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(save);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Save load(String file) {
		try (FileInputStream fis = new FileInputStream(new File(file));
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Save) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
