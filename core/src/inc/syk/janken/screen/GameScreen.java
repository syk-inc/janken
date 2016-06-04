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
public class GameScreen implements Screen {
  public static final String LOG_TAG = "GameScreen";

  OrthographicCamera camera;

  SpriteBatch batch;

  Texture guTexture;
  Texture tyokiTexture;
  Texture paTexture;
  Texture startButtanTexture;

  Rectangle gu;
  Rectangle tyoki;
  Rectangle pa;
  Rectangle startButton;

  JankenGame game ;
  public GameScreen(JankenGame game){
    this.game = game;
  }

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    camera = new OrthographicCamera();
    camera.setToOrtho(false, JankenGame.SCREEN_SIZE_WIDTH, JankenGame.SCREEN_SIZE_HEIGHT); //画面サイズ

    batch = new SpriteBatch();

    guTexture = new Texture("guu.png");
    tyokiTexture = new Texture("tyoki.png");
    paTexture = new Texture("paa.png");
    startButtanTexture = new Texture("start.png");

    gu = new Rectangle();
    gu.x = 20;
    gu.y = 20;
    gu.width = guTexture.getWidth();
    gu.height = guTexture.getHeight();

    tyoki = new Rectangle();
    tyoki.x = (JankenGame.SCREEN_SIZE_WIDTH / 2) - (tyokiTexture.getWidth() / 2);
    tyoki.y = 20;
    tyoki.width = tyokiTexture.getWidth();
    tyoki.height = tyokiTexture.getHeight();

    pa = new Rectangle();
    pa.x = JankenGame.SCREEN_SIZE_WIDTH - paTexture.getWidth() - 20;
    pa.y = 20;
    pa.width = paTexture.getWidth();
    pa.height = paTexture.getHeight();

    startButton = new Rectangle();
    startButton.x = 20;
    startButton.y = JankenGame.SCREEN_SIZE_HEIGHT - startButtanTexture.getHeight() - 20;
    startButton.width = startButtanTexture.getWidth();
    startButton.height = startButtanTexture.getHeight();
  }

  @Override
  public void render(float delta) {
    Gdx.app.log(LOG_TAG, "render");
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();


    batch.draw(guTexture, gu.x, gu.y);
    batch.draw(tyokiTexture,tyoki.x , tyoki.y);
    batch.draw(paTexture, pa.x, pa.y);
    batch.draw(startButtanTexture, startButton.x, startButton.y);

    Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    camera.unproject(tmp);

    if (startButton.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched");
    }

    camera.update();
    batch.end();
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
