package finalforeach.ld45.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import finalforeach.ld45.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Gladiators!";
		config.width=1280;
		config.height=768;
		new LwjglApplication(new Game(), config);
	}
}
