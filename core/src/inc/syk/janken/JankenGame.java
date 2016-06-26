package inc.syk.janken;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import inc.syk.janken.screen.TitleScreen;

public class JankenGame extends Game {
  public static final int SCREEN_SIZE_WIDTH = 800;
  public static final int SCREEN_SIZE_HEIGHT = 480;

  @Override
  public void create () {
    // アプリが起動して最初に表示されるScreen
    setScreen(new TitleScreen(this));
    //setScreen(new ResultScreen(this,new Result()));
  }

  @Override
  public void render() {
    super.render();
  }

  // screenの切り替え
  @Override
  public void setScreen(Screen screen) {
    Screen currentScreen = getScreen();
    if (currentScreen != null){
      currentScreen.dispose();
    }

    super.setScreen(screen);
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
