package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

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


  private Stage stage;
  private SpriteBatch batch;


  private long Ytime;


  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    // stage
    stage = new Stage(new FillViewport(JankenGame.SCREEN_SIZE_WIDTH,JankenGame.SCREEN_SIZE_HEIGHT));
    Gdx.input.setInputProcessor(stage);

    // ばっち
    batch = new SpriteBatch();

    // スタートボタン
    Texture startButtanTexture = new Texture("start.png");
    Image startButtonImage = new Image(startButtanTexture);

    int x = (JankenGame.SCREEN_SIZE_WIDTH / 2 )  - (startButtanTexture.getWidth() / 2);
    int y = (JankenGame.SCREEN_SIZE_HEIGHT / 2 ) - (startButtanTexture.getHeight() /2);
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
    misterYImage.setPosition((JankenGame.SCREEN_SIZE_WIDTH) - 200,1);


    // ステージに俳優を追加
    stage.addActor(startButtonImage);
    stage.addActor(misterYImage);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();

    /*if(TimeUtils.millis() > Ytime){
      misterY.x = misterY.x + 1;
      Ytime = TimeUtils.millis();
    }*/

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

  }
}
