import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Rysunki extends JPanel {

	private static final long serialVersionUID = 101L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		// Struktura okna
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Rectangle2D aRect = new Rectangle2D.Double(601, 0, 2, 403);
		g2.setPaint(SystemColor.windowBorder);
		g2.draw(aRect);
		g2.fill(aRect);
		aRect = new Rectangle2D.Double(0, 401, 603, 2);
		g2.draw(aRect);
		g2.fill(aRect);
		aRect = new Rectangle2D.Double(603, 403, 72, 58);
		g2.draw(aRect);
		g2.fill(aRect);
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Licznik //
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String apkt = "" + Snake.punkty;
		String apoz = "" + Snake.poziom;

		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(new Color(255, 0, 0));
		g.drawString("Zdobyte", 606, 20);
		g.drawString("Punkty:", 606, 40);
		g.drawString("Poziom:", 606, 100);
		g.drawString(apkt, 606, 60);
		g.drawString(apoz, 606, 120);
		// Instrukcje //
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		g.drawString("Sterowanie: W,S,A,D lub za pomoc¹ strza³ek", 20, 415);
		g.drawString("ESC - zakoñczenie gry, SPACE - Nowa gra", 20, 430);
		g.drawString("Punktacja:", 606, 140);
		Rectangle2D JD;
		for (int z = 0; z < 5; z++) {
			JD = new Rectangle2D.Double(606, 150 + z * 20, 10, 10);
			switch (z) {
			case 0:
				g2.setPaint(Color.red);
				g.drawString(" - 1", 620, 160);
				break;
			case 1:
				g2.setPaint(Color.orange);
				g.drawString(" - 2", 620, 180);
				break;
			case 2:
				g2.setPaint(Color.green);
				g.drawString(" - 50", 620, 200);
				break;
			case 3:
				g2.setPaint(Color.blue);
				g.drawString(" - 10", 620, 220);
				break;
			case 4:
				g2.setPaint(Color.pink);
				g.drawString(" - 100", 620, 240);
				break;
			}
			g2.draw(JD);
			g2.fill(JD);
		}

		// Wyœwietlanie snake'a //
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int k = 0; k < 60; k++) {
			for (int l = 0; l < 40; l++) {

				if (Snake.tab[k][l][0] == 1) {
					Rectangle2D Wezus = new Rectangle2D.Double(k * 10, l * 10,
							10, 10);
					g2.setPaint(new Color(20, 255, 255));
					g2.draw(Wezus);
					g2.fill(Wezus);
					Ellipse2D WezusE = new Ellipse2D.Double(2 + k * 10,
							2 + l * 10, 6, 6);
					g2.setPaint(new Color(255, 255, 0));
					g2.draw(WezusE);
					g2.fill(WezusE);
				}
				// i jedzenia :D //
				if (Snake.tab[k][l][0] > 1) {
					Rectangle2D WezusJ = new Rectangle2D.Double(k * 10, l * 10,
							10, 10);
					if (Snake.tab[k][l][0] < 102)
						g2.setPaint(Color.red);
					else if (Snake.tab[k][l][0] < 152)
						g2.setPaint(Color.orange);
					else if (Snake.tab[k][l][0] < 172)
						g2.setPaint(Color.green);
					else if (Snake.tab[k][l][0] < 182)
						g2.setPaint(Color.blue);
					else if (Snake.tab[k][l][0] < 183)
						g2.setPaint(Color.pink);
					g2.draw(WezusJ);
					g2.fill(WezusJ);

				}
			}
		}
		// Rozroznienie glowy i ogona //
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		aRect = new Rectangle2D.Double(Snake.i * 10, Snake.j * 10, 10, 10);
		g2.setPaint(Color.white);
		g2.draw(aRect);
		g2.fill(aRect);
		aRect = new Rectangle2D.Double(Snake.ti * 10, Snake.tj * 10, 10, 10);
		g2.setPaint(Color.black);
		g2.draw(aRect);
		g2.fill(aRect);
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
}