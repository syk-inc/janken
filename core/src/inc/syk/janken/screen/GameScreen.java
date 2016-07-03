package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
  private Stage stage;
  private long enemyHand;
  private BitmapFont font;
  private Image misterY;
  private Texture misterYguTexture;
  private Texture misterYcyokiTexture;
  private Texture misterYpaTexture;
  private Image miniY2Image;
  private Image miniY3Image;


  private enum Hands{
    GU(0),
    TYOKI(1),
    PA(2);
    private final int id;

    Hands(final int id){
      this.id = id;
    }

    public int getId() {
      return id;
    }
  }


  public GameScreen(JankenGame game){
    this.game = game;
  }

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    // stage
    stage = new Stage(new FillViewport(JankenGame.SCREEN_SIZE_WIDTH,JankenGame.SCREEN_SIZE_HEIGHT));
    Gdx.input.setInputProcessor(stage);

    result = new Result();

    // ミスターY
    Texture misterYTexture = new Texture("gu-cyokipa-.png");
    misterYguTexture = new Texture("misterY_gu-.png");
    misterYcyokiTexture = new Texture("misterY_cyoki.png");
    misterYpaTexture = new Texture("misterY_pa-.png");
    misterY = new Image(misterYTexture);
    misterY.setPosition(300,200);

    // miniY 1
    Texture miniY1Texture = new Texture("miniY_bule.png");
    final Image miniY1Image = new Image(miniY1Texture);
    int a = (JankenGame.SCREEN_SIZE_WIDTH / 4 * 3 )  - (miniY1Texture.getWidth() / 2);
    int b = (JankenGame.SCREEN_SIZE_HEIGHT / 4 * 3 ) - (miniY1Texture.getHeight() / 2);
    miniY1Image.setPosition(a,b);

    // miniY 2
    Texture miniY2Texture = new Texture("miniY_Purple.png");
    miniY2Image = new Image(miniY2Texture);
    int c = (JankenGame.SCREEN_SIZE_WIDTH / 4 * 3 )  - (miniY1Texture.getWidth() / 2) + 20;
    int d = (JankenGame.SCREEN_SIZE_HEIGHT / 4 * 3 ) - (miniY1Texture.getHeight() /2);
    miniY2Image.setPosition(c,d);

    // miniY 3
    Texture miniY3Texture = new Texture("miniY_red.png");
    miniY3Image = new Image(miniY3Texture);
    int e = (JankenGame.SCREEN_SIZE_WIDTH / 4 * 3 )  - (miniY1Texture.getWidth() / 2) + 40;
    int f = (JankenGame.SCREEN_SIZE_HEIGHT / 4 * 3 ) - (miniY1Texture.getHeight() / 2);
    miniY3Image.setPosition(e,f);

    // guu
    Texture guTexture = new Texture("guu.png");
    Image guImage = new Image(guTexture);
    guImage.setPosition(20,20);
    // guクリック
    guImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        drawEnemy();
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
        drawEnemy();
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
        drawEnemy();
        touchedPa();
      }
    });

    // スタートボタン
    Texture startButtanTexture = new Texture("start.png");
    Image startButtonImage = new Image(startButtanTexture);
    int x = (JankenGame.SCREEN_SIZE_WIDTH / 6 )  - (startButtanTexture.getWidth() / 2);
    int y = (JankenGame.SCREEN_SIZE_HEIGHT / 4 * 3 ) - (startButtanTexture.getHeight() / 2);
    startButtonImage.setPosition(x,y);
    // スタートボタンクリック
    startButtonImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        touchedStartButton();
      }
    });

    stage.addActor(startButtonImage);
    stage.addActor(guImage);
    stage.addActor(tyokiImage);
    stage.addActor(paImage);
    stage.addActor(misterY);
    stage.addActor(miniY1Image);
    stage.addActor(miniY2Image);
    stage.addActor(miniY3Image);
  }

  private void drawEnemy(){
    enemyHand = TimeUtils.millis() % 3;

    if(enemyHand == Hands.GU.getId()){
      misterY.setDrawable(new SpriteDrawable(new Sprite(misterYguTexture)));
    } else if(enemyHand == Hands.TYOKI.getId()){
      misterY.setDrawable(new SpriteDrawable(new Sprite(misterYcyokiTexture)));
    } else if(enemyHand == Hands.PA.getId()){
      misterY.setDrawable(new SpriteDrawable(new Sprite(misterYpaTexture)));
    }

    if (result.getLoseCount() == 1){
      miniY3Image.remove();
      return;
    }
    if (result.getLoseCount() == 3){
      miniY2Image.remove();
    }
  }

  @Override
  public void render(float delta) {
    //Gdx.app.log(LOG_TAG, "render");
    //Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  /*グーチョキパーの条件付け
  *グー　0　チョキ　1　パー　2
  */

  private void touchedGu() {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    if(enemyHand == Hands.GU.getId()){
      result.draw();
      System.out.println("ひきわけ");
    }
    if(enemyHand == Hands.TYOKI.getId()){
      result.win();
      System.out.println("かち");
    }
    if(enemyHand == Hands.PA.getId()){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
      if (result.getLoseCount() >= 5){
        game.setScreen(new ResultScreen(game, result));
        this.dispose();
      }
    }
  }

  private void touchedTyoki() {
    Gdx.gl.glClearColor(1, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == Hands.GU.getId()){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
      if (result.getLoseCount() >= 5){
        game.setScreen(new ResultScreen(game, result));
        this.dispose();
      }
    }
    if(enemyHand == Hands.TYOKI.getId()){
      result.draw();
      System.out.println("ひきわけ");
    }
    if((enemyHand == Hands.PA.getId())){
      result.win();
      System.out.println("かち");
    }
  }

  private void touchedPa() {
    Gdx.gl.glClearColor(0, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if(enemyHand == Hands.GU.getId()){
      result.win();
      System.out.println("かち");
    }
    if(enemyHand == Hands.TYOKI.getId()){
      result.lose();
      System.out.println(result.getLoseCount());
      System.out.println("まけ");
      if (result.getLoseCount() >= 5){
        game.setScreen(new ResultScreen(game, result));
        this.dispose();
      }
    }
    if(enemyHand == Hands.PA.getId()){
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
