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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;

import inc.syk.janken.JankenGame;
import inc.syk.janken.model.Result;

/**
 * Created by sota on 16/06/03.
 */
public class GameScreen implements Screen {
  public static final String LOG_TAG = "GameScreen";

  private JankenGame game;
  private Result result;

  public GameScreen(JankenGame game){
        this.game = game;
    }

  private Stage stage;
  private long enemyHand;

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    // stage
    stage = new Stage(new FillViewport(JankenGame.SCREEN_SIZE_WIDTH,JankenGame.SCREEN_SIZE_HEIGHT));
    Gdx.input.setInputProcessor(stage);

    result = new Result();

     // guu
    Texture guTexture = new Texture("guu.png");
    Image guImage = new Image(guTexture);
    guImage.setPosition(20,20);
    // guクリック
    guImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        touchedGu();
      }
    });

    // tyoki
    Texture tyokiTexture = new Texture("tyoki.png");
    Image tyokiImage = new Image(tyokiTexture);
    tyokiImage.setPosition((JankenGame.SCREEN_SIZE_WIDTH / 2) - (tyokiTexture.getWidth() / 2),20);
    // tyokiクリック
    tyokiImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        touchedTyoki();
      }
    });

    // paa
    Texture paTexture = new Texture("paa.png");
    Image paImage = new Image(paTexture);
    paImage.setPosition(JankenGame.SCREEN_SIZE_WIDTH  - paTexture.getWidth() - 20,20);
    // paクリック
    paImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        touchedPa();
      }
    });

    // スタートボタン
    Texture startButtanTexture = new Texture("start.png");
    Image startButtonImage = new Image(startButtanTexture);
    int x = (JankenGame.SCREEN_SIZE_WIDTH / 6 )  - (startButtanTexture.getWidth() / 2);
    int y = (JankenGame.SCREEN_SIZE_HEIGHT / 4 * 3 ) - (startButtanTexture.getHeight() /2);
    startButtonImage.setPosition(x,y);
     // スタートボタンクリック
      startButtonImage.addListener(new ClickListener(){
          @Override
          public void clicked(InputEvent event, float x, float y) {
         touchedStartButton();
      }
      });

    // ミスターY
    Texture misterYTexture = new Texture("misterY.png");
    Image misterYImage = new Image(misterYTexture);
    misterYImage.setPosition(300,200);

    stage.addActor(startButtonImage);
    stage.addActor(guImage);
    stage.addActor(tyokiImage);
    stage.addActor(paImage);
    stage.addActor(misterYImage);
  }

  @Override
  public void render(float delta) {
    //Gdx.app.log(LOG_TAG, "render");
    //Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    /*敵の手をランダムで出す*/
    enemyHand = TimeUtils.millis() % 3;
    //System.out.println(enemyHand);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();

  }

  /*グーチョキパーの条件付け
  *グー　0　チョキ　1　パー　2
  * result kati 0 hikiwake 1 make 2
  */
  private void touchedGu() {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result.draw();
      System.out.println("ひきわけ");
    }
    if(enemyHand == 1){
      result.win();
      System.out.println("かち");
    }
    if(enemyHand == 2){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
    }
  }

  private void touchedTyoki() {
    Gdx.gl.glClearColor(1, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
    }
    if(enemyHand == 1){
      result.draw();
      System.out.println("ひきわけ");
    }
    if(enemyHand == 2){
      result.win();
      System.out.println("かち");
    }
  }

  private void touchedPa() {
    Gdx.gl.glClearColor(0, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == 0){
      result.win();
      System.out.println("かち");
    }
    if(enemyHand == 1){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
    }
    if(enemyHand == 2){
      result.draw();
      System.out.println("ひきわけ");
    }
  }
  /*条件付けここまで*/

  private void touchedStartButton() {
    game.setScreen(new ResultScreen(game, result));
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
  }
}
