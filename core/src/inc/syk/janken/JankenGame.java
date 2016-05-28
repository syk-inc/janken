package inc.syk.janken;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class JankenGame extends ApplicationAdapter {
  private final int SCREEN_SIZE_WIDTH = 800;
  private final int SCREEN_SIZE_HEIGHT = 480;

  OrthographicCamera camera;

  SpriteBatch batch;

  Texture guTexture;
  Texture tyokiTexture;
  Texture paTexture;
  Texture startButtanTexture;

  Rectangle gu;
  Rectangle tyoki;
  Rectangle pa;
  Rectangle startButtan;

  @Override
  public void create () {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT); //画面サイズ

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
    tyoki.x = (SCREEN_SIZE_WIDTH / 2) - (tyokiTexture.getWidth() / 2);
    tyoki.y = 20;
    tyoki.width = tyokiTexture.getWidth();
    tyoki.height = tyokiTexture.getHeight();

    pa = new Rectangle();
    pa.x = SCREEN_SIZE_WIDTH - paTexture.getWidth() - 20;
    pa.y = 20;
    pa.width = paTexture.getWidth();
    pa.height = paTexture.getHeight();

    startButtan = new Rectangle();
    startButtan.x = 20;
    startButtan.y = SCREEN_SIZE_HEIGHT - startButtanTexture.getHeight() - 20;
    startButtan.width = startButtanTexture.getWidth();
    startButtan.height = startButtanTexture.getHeight();


  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();


    batch.draw(guTexture, gu.x, gu.y);
    batch.draw(tyokiTexture,tyoki.x , tyoki.y);
    batch.draw(paTexture, pa.x, pa.y);
    batch.draw(startButtanTexture, startButtan.x, startButtan.y);

    Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    camera.unproject(tmp);

    if (startButtan.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched");
    }

    camera.update();
    batch.end();
  }
}
