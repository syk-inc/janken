package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inc.syk.janken.JankenGame;

/**
 * Created by sota on 16/06/03.
 */
public class ResultScreen implements Screen {
  public static final String LOG_TAG = "ResultScreen";

  private OrthographicCamera camera;
  private SpriteBatch batch;

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    camera = new OrthographicCamera();
    camera.setToOrtho(false, JankenGame.SCREEN_SIZE_WIDTH, JankenGame.SCREEN_SIZE_HEIGHT);

    batch = new SpriteBatch();
  }

  @Override
  public void render(float delta) {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
