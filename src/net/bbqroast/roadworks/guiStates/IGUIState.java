package net.bbqroast.roadworks.guiStates;

import net.bbqroast.roadworks.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IGUIState {
    void mouseReleased(int button, int x, int y, int xoff, int yoff);
    void render(Graphics graphics, GameContainer gameContainer, Camera camera);
}
