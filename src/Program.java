import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {
	public Program(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawString("Howdy!", 10, 10);
	}

	public static void main(String[] args) {
		System.setProperty("java.library.path", "libs");

		// Extracted from Distributing Your LWJGL Application
		System.setProperty("org.lwjgl.librarypath", new File("libs/natives").getAbsolutePath());

		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Program("BioSim"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}