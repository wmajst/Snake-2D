import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Snake implements KeyListener {
	public static int punkty, poziom;
	public static int i, j, ti, tj;
	static Random generator = new Random();
	public static int[][][] tab = new int[60][40][4];
	private static int dx, dy, tx, ty, petla, jedzenie, sleeptime;
	private static Window aOkno;
	// metoda na warunki poczatkowe gry - orzydaje sie do jej zresetowania
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void wPoczatkowe() {
		for (i = 0; i < 60; i++) {
			for (j = 0; j < 40; j++) {
				tab[i][j][0] = 0;
			}
		}
		sleeptime = 500;
		i = ti = 29;
		j = tj = 19;
		tab[i][j][0] = 1;
		tab[i][j][1] = 30;
		tab[i][j][2] = 19;
		i++;
		tab[i][j][0] = 1;
		tab[i][j][1] = 31;
		tab[i][j][2] = 19;
		jedzenie = dx = 1;
		dy = petla = punkty = poziom = 0;
	}
	// przebieg calej gry //
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void wPrzebieg() {
		for (;;) {
			aOkno.repaint();

			// pamiec //////////////////////////////////
			tx = i;
			ty = j;
			// kierunki ruchu w kolejnosci lewo,prawo,dol,gora 
			if (dx == 1)
				i++; 
			if (dx == -1)
				i--; 
			if (dy == 1)
				j++; 
			if (dy == -1)
				j--;
			// warunki brzegowe
			if (i == 60)
				i = 0;
			if (i < 0)
				i = 59;
			if (j == 40)
				j = 0;
			if (j < 0)
				j = 39;
			tab[tx][ty][1] = i; // pamiec dla elementu tego
			tab[tx][ty][2] = j; // na ktory najezdzam do wyzerowania
			
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// Warunki na aktywowane pola (co ma zostac wyswietlone itd) //
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// wjechanie w samego siebie (lepszego sposobu nie ma chyba) //
			if (tab[i][j][0] == 1) {
				petla = 1;
				do {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (petla == 1);
			}
			// zadnego jedzenia nic
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (tab[i][j][0] == 0) {
				tab[i][j][0] = 1;
				tab[ti][tj][0] = 0;
				int tempti = ti;
				ti = tab[ti][tj][1]; // znow ta pamiec
				tj = tab[tempti][tj][2];
			}
			// nastepne pole to jedzenie //
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (tab[i][j][0] > 1) {
				if (tab[i][j][0] < 102) //jedzenie czerwone
					punkty += 1;
				else if (tab[i][j][0] < 152) //pomaranczowe
					punkty += 2;
				else if (tab[i][j][0] < 172) //zielone
					punkty += 50;
				else if (tab[i][j][0] < 182) //niebieske
					punkty += 10;
				else if (tab[i][j][0] < 183) //rozowe
					punkty += 100;
				jedzenie = 1;
				tab[i][j][0] = 1;

			}

			// Przelicznik punkty -> poziom //
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			switch (punkty / 100) {
			case (1):
				poziom = 1;
				break;
			case 2:
				poziom = 2;
				break;
			case 5:
				poziom = 3;
				break;
			case 10:
				poziom = 4;
				break;
			case 20:
				poziom = 5;
				break;
			case 50:
				poziom = 6;
				break;
			case 100:
				poziom = 7;
				break;
			case 200:
				poziom = 8;
				break;
			case 500:
				poziom = 9;
				break;
			case 1000:
				poziom = 10;
				break;
			case 10000:
				poziom = 11;
				break;
			case 20000:
				poziom = 12;
				break;
			}
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// Tworzenie pokarmu (losowe) //
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (jedzenie == 1) {
				int xg, yg, g0;
				do {
					xg = generator.nextInt(60);
					yg = generator.nextInt(40);
					g0 = 2 + generator.nextInt(182);
				} while (tab[xg][yg][0] == 1);
				tab[xg][yg][0] = g0;
				jedzenie = 0;
			}
			
			// Regulacja predkosci gry //
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			sleeptime = (int) (500 / Math.pow(1.3, poziom));
			if (poziom==11) sleeptime = 10;
			if (poziom>11) sleeptime=0;
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		// Tworzenie okna //
		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		aOkno = new Window();
		aOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aOkno.setVisible(true);
		Snake sluchacz = new Snake();
		aOkno.addKeyListener(sluchacz);

		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Warunki poczatkowe snake'a //
		wPoczatkowe();
		// Petla przebiegu gry //
		wPrzebieg();

	}

	// Obsluga komunikatow // tak wiem kiepska
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void keyPressed(KeyEvent aKey) {
		int Key = aKey.getKeyCode();

		if ((Key ==KeyEvent.VK_W || Key == KeyEvent.VK_UP) && dy == 0 && ty==j) {
			dx = 0;
			dy = -1;
		} else if ((Key == KeyEvent.VK_S || Key == KeyEvent.VK_DOWN) && dy == 0 && ty==j) {
			dx = 0;
			dy = 1;
		} else if ((Key == KeyEvent.VK_A || Key == KeyEvent.VK_LEFT) && dx == 0 && tx==i) {
			dx = -1;
			dy = 0;
		} else if ((Key == KeyEvent.VK_D || Key == KeyEvent.VK_RIGHT) && dx == 0 && tx==i) {
			dx = 1;
			dy = 0;
		}
		if (Key == KeyEvent.VK_SPACE) {
			wPoczatkowe();
			petla = 0;
		}
		if (Key == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	// z tego akurat nie korzystam // ale byc musi //
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void keyReleased(KeyEvent aKey) {
	}

	public void keyTyped(KeyEvent aKey) {
	}
}
