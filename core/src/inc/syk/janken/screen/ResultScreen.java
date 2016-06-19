package inc.syk.janken.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

import inc.syk.janken.JankenGame;
import inc.syk.janken.model.Result;

/**
 * Created by sota on 16/06/03.
 */
public class ResultScreen implements Screen {
  public static final String LOG_TAG = "ResultScreen";

  private JankenGame game;
  private Result result;

  private Stage stage;
  private Batch batch;

  private BitmapFont font;

  public ResultScreen(JankenGame game, Result result){
    this.game   = game;
    this.result = result;
  }

  @Override
  public void show() {
    Gdx.app.log(LOG_TAG, "show");

    stage = new Stage(new FillViewport(JankenGame.SCREEN_SIZE_WIDTH,JankenGame.SCREEN_SIZE_HEIGHT));
    Gdx.input.setInputProcessor(stage);

    Texture misterY = new Texture("misterY.png");
    final Image misterYImage = new Image(misterY);
    misterYImage.setPosition(0,0);

    Action toRight = Actions.moveBy(JankenGame.SCREEN_SIZE_WIDTH-misterYImage.getWidth(), 0, 1,Interpolation.fade);
    Action toLeft = Actions.moveBy(-(JankenGame.SCREEN_SIZE_WIDTH-misterYImage.getWidth()), 0, 1,Interpolation.fade);
    // TODO 進行方向とmisterYの向きを合わせる

    SequenceAction seq = Actions.sequence();
    seq.addAction(toRight);
    seq.addAction(toLeft);

    RepeatAction forever = Actions.forever(seq);//無限ループさせる

    misterYImage.addAction(forever);


    batch = stage.getBatch();

    font = new BitmapFont();
    font.getData().setScale(3);

    Label.LabelStyle style = new Label.LabelStyle();
    style.font = font;
    style.fontColor = new Color(0f,0f,0f,1);

    Label label = new Label("Back to Title", style);
    label.setPosition( (JankenGame.SCREEN_SIZE_WIDTH / 2 )-(label.getWidth()/2), (JankenGame.SCREEN_SIZE_HEIGHT / 2)+50);

    // クリック
    label.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.setScreen(new TitleScreen(game));
      }
    });

    misterYImage.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        Action up = Actions.moveBy(0,120,0.3f,Interpolation.pow2);
        Action down = Actions.moveBy(0,-120,0.5f,Interpolation.pow2);

        SequenceAction jump = Actions.sequence();
        jump.addAction(up);
        jump.addAction(down);

        misterYImage.addAction(jump);
      }
    });


    stage.addActor(misterYImage);
    stage.addActor(label);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();

    batch.begin();
    font.draw(batch, "Result", (JankenGame.SCREEN_SIZE_WIDTH / 2 )-font.getSpaceWidth(), (JankenGame.SCREEN_SIZE_HEIGHT / 2)+20);
    font.draw(batch, "Score: " + result.getScore(),  (JankenGame.SCREEN_SIZE_WIDTH / 2 )-font.getSpaceWidth(), (JankenGame.SCREEN_SIZE_HEIGHT / 2));
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
    stage.dispose();
    font.dispose();
  }
}
