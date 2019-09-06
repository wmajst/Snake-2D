import java.awt.*;
import javax.swing.*;

// ta klasa nie wymaga komentarza //
class Window extends JFrame {
	public static Rysunki aRysunki;
	private static final long serialVersionUID = 10L;

	public Window() {
		setSize(675, 462);
		setResizable(false);
		setTitle("Snake - Wojciech Majstrzyk@173087");
		setLocation(200, 200);
		Toolkit narzedzia = Toolkit.getDefaultToolkit();
		Image Ikona = narzedzia.getImage("Snake.gif");
		setIconImage(Ikona);
		setBackground(new Color(250, 0, 0));
		aRysunki = new Rysunki();
		aRysunki.setBackground(new Color(200, 200, 100));
		Container Zawartosc = getContentPane();
		Zawartosc.add(aRysunki);
	}

}
