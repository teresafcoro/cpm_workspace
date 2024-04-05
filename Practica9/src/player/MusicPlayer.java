package player;

import java.io.File;
import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	
	private BasicPlayer basicPlayer = null;
	private File cancionSonando;
	private boolean isAleatorio;
	
	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
		cancionSonando = null;
		isAleatorio = false;
	}
	
	public void play(File file) {
		try {
			basicPlayer.open(file);
			basicPlayer.play();
		} catch (Exception e){
		}
	}
	
	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
		}
	}
	
	public void setVolume(double vol, double volMax) {
		try {
			basicPlayer.setGain(vol/volMax);
		} catch (BasicPlayerException e) {
		}
	}

	public File getCancionSonando() {
		return cancionSonando;
	}
	public void setCancionSonando(File cancionSonando) {
		this.cancionSonando = cancionSonando;
	}

	public boolean isAleatorio() {
		return isAleatorio;
	}
	public void setAleatorio(boolean isAleatorio) {
		this.isAleatorio = isAleatorio;
	}
	
}
