package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import inc.syk.janken.JankenGame;

/**
 * Created by sota on 16/06/03.
 */
public class TitleScreen implements Screen {
  public static final String LOG_TAG = "TitleScreen";

  private JankenGame game;

  /**
   * コンストラクタ
   * @param game
   */
  public TitleScreen(JankenGame game){
    this.game = game;
  }

  private OrthographicCamera camera;
  private SpriteBatch batch;
  private Texture startButtanTexture;
  private Rectangle startButton;
  private Texture misterYTexture;

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    camera = new OrthographicCamera();
    camera.setToOrtho(false, JankenGame.SCREEN_SIZE_WIDTH, JankenGame.SCREEN_SIZE_HEIGHT);

    batch = new SpriteBatch();

    // スタートボタン
    startButtanTexture = new Texture("start.png");
    startButton        = new Rectangle();
    startButton.x      = (JankenGame.SCREEN_SIZE_WIDTH / 2 )  - (startButtanTexture.getWidth() / 2);
    startButton.y      = (JankenGame.SCREEN_SIZE_HEIGHT / 2 ) - (startButtanTexture.getHeight() /2);
    startButton.width  = startButtanTexture.getWidth();
    startButton.height = startButtanTexture.getHeight();

    // ミスターY
    misterYTexture = new Texture("misterY.png");
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    batch.draw(startButtanTexture, startButton.x, startButton.y);
    batch.draw(misterYTexture, startButton.x, startButton.y);

    Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    camera.unproject(tmp);

    if (startButton.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched");
      touchedStartButton();
    }

    camera.update();
    batch.end();
  }

  /**
   * スタートボタン押下時の処理
   */
  private void touchedStartButton() {
    game.setScreen(new GameScreen(game));
    this.dispose();
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
    startButtanTexture.dispose();
  }
}
