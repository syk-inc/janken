package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import inc.syk.janken.JankenGame;

/**
 * Created by sota on 16/06/03.
 */
public class GameScreen implements Screen {
  public static final String LOG_TAG = "GameScreen";

  private OrthographicCamera camera;

  private SpriteBatch batch;
  private BitmapFont font;

  private Texture guTexture;
  private Texture tyokiTexture;
  private Texture paTexture;

  private Rectangle gu;
  private Rectangle tyoki;
  private Rectangle pa;

  private long enemyHand;
  private long myHand;
  private long result;

  private JankenGame game ;

  public GameScreen(JankenGame game){
    this.game = game;
  }

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    camera = new OrthographicCamera();
    camera.setToOrtho(false, JankenGame.SCREEN_SIZE_WIDTH, JankenGame.SCREEN_SIZE_HEIGHT); //画面サイズ

    batch = new SpriteBatch();
    font = new BitmapFont();
    font.setColor(Color.RED);

    guTexture = new Texture("guu.png");
    tyokiTexture = new Texture("tyoki.png");
    paTexture = new Texture("paa.png");

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

  }

  @Override
  public void render(float delta) {
    Gdx.app.log(LOG_TAG, "render");
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    /*敵の手をランダムで出す*/
    enemyHand = TimeUtils.millis() % 3;
    /*
    System.out.println(enemyHand);
    +/

    /*タッチした場所による条件分け*/
    Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    camera.unproject(tmp);

    if (gu.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched gu");
      myHand = 0;
      touchedGu();
    }

    if (tyoki.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched tyoki");
      myHand = 1;
      touchedTyoki();
    }

    if (pa.contains(tmp.x, tmp.y)) {
      System.out.println("Is touched pa");
      myHand = 2;
      touchedPa();
    }

    /*batchでの画像表示はrenderの最後に行う*/
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    batch.draw(guTexture, gu.x, gu.y);
    batch.draw(tyokiTexture,tyoki.x , tyoki.y);
    batch.draw(paTexture, pa.x, pa.y);

    /*　結果を表示する
    font.draw(batch, "Hello World", 200, 200);
    */
    camera.update();
    batch.end();
  }

  /*グーチョキパーの条件付け
  *グー　0　チョキ　1　パー　2
  * result kati 0 hikiwake 1 make 2
  */
  private void touchedGu() {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result = 1;
      System.out.println("ひきわけ");
    }
    if(enemyHand == 1){
      result = 0;
      System.out.println("かち");
    }
    if(enemyHand == 2){
      result = 2;
      System.out.println("まけ");
    }
  }

  private void touchedTyoki() {
    Gdx.gl.glClearColor(1, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result = 2;
      System.out.println("まけ");
    }
    if(enemyHand == 1){
      result = 1;
      System.out.println("ひきわけ");
    }
    if(enemyHand == 2){
      result = 0;
      System.out.println("かち");
    }
  }

  private void touchedPa() {
    Gdx.gl.glClearColor(0, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result = 0;
      System.out.println("かち");
    }
    if(enemyHand == 1){
      result = 2;
      System.out.println("まけ");
    }
    if(enemyHand == 2){
      result = 1;
      System.out.println("ひきわけ");
    }
  }

  /*条件付けここまで*/

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
