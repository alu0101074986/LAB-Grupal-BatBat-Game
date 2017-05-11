package al.artofsoul.GameState;


import java.awt.*;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;


import al.artofsoul.Audio.JukeBox;
import al.artofsoul.Entity.PlayerSave;
import al.artofsoul.Handlers.Keys;
import al.artofsoul.Main.GamePanel;


/**
 * @author ArtOfSoul
 *
 */


public class MenuState extends GameState {
	
	
	private BufferedImage bg;
	private BufferedImage head;
	
	
	private int currentChoice = 0;
	private String[] options = {
		"Play",
		"Quit"
	};
	
	
	private Font font;
	private Font font2;

	
	public MenuState(GameStateManager gsm) {
		
		super(gsm);
				
		try {
			 bg = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/bgGame.gif")).getSubimage(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			  	
			
			  	
			// load floating head
			head = ImageIO.read(
				getClass().getResourceAsStream("/HUD/Hud.gif")
			).getSubimage(0, 12, 12, 11);
			
			// titles and fonts

			font = new Font("Arial", Font.BOLD, 13);
			font2 = new Font("Arial", Font.PLAIN, 9);
			
			// load sound fx
			JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			JukeBox.load("/SFX/menuselect.mp3", "menuselect");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	public void init() {}
	
	public void update() {
		
		// check keys
		handleInput();
		
	}
	
	public void draw(Graphics2D g){
	

		
		
		// draw bg
		g.drawImage(bg, 0, 0, null);
		
		
		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Play", 140, 135);
		g.drawString("Quit", 140, 155);
		
		// draw floating head
		if(currentChoice == 0) g.drawImage(head, 120, 125, null);
		else if(currentChoice == 1) g.drawImage(head, 120, 145, null);
	
		
		// other
		g.setFont(font2);
		g.drawString("2017 � toni kolaba", 10, 232);
		
	
		
	}
	
	
	private void select() {
		if(currentChoice == 0) {
			JukeBox.play("menuselect");
			PlayerSave.init();
			gsm.setState(GameStateManager.LEVEL1ASTATE); /// start this level entrance
		}
		else if(currentChoice == 1) {
			System.exit(0);
		}		
		
	}
	




	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				JukeBox.play("menuoption", 0);
				currentChoice++;
			}
		}
	}
	
}










