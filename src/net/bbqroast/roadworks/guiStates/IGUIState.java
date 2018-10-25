package net.bbqroast.roadworks.guiStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IGUIState {
    void mouseReleased(int button, int x, int y);
    void render(Graphics graphics, GameContainer gameContainer);
}
