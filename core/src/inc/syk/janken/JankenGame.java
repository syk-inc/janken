package inc.syk.janken;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import inc.syk.janken.screen.GameScreen;


public class JankenGame extends Game {
  public static final int SCREEN_SIZE_WIDTH = 800;
  public static final int SCREEN_SIZE_HEIGHT = 480;

  private Screen nextScreen;

  @Override
  public void create () {
    // アプリが起動して最初に表示されるScreen
    //setScreen(new TitleScreen()); // TODO TitleScreenができたらこっちに変える
    setScreen(new GameScreen(this));
  }

  @Override
  public void render() {
    if (nextScreen != null) {
      super.setScreen(nextScreen);
      nextScreen = null;
    }

    super.render();
  }

  // screenの切り替え
  @Override
  public void setScreen(Screen screen) {
    nextScreen = screen;
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
