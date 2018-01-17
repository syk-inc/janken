package inc.syk.janken.model;

/**
 * Created by sota on 16/06/12.
 */
public class Result {
  private Long score = 0L;
  private Long winCount = 0L;
  private Long loseCount = 0L;
  private Long drawCount = 0L;

  /**
   * じゃんけんに勝った
   */
  public void win(){
    winCount += 1;
    score += 10;
  }

  /**
   * じゃんけんに負けた
   */
  public void lose(){
    loseCount += 1;
  }

  /**
   * じゃんけんに引き分けた
   */
  public void draw(){
    drawCount += 1;
  }

  public Long getScore() {
    return score;
  }

  public Long getWinCount() {
    return winCount;
  }

  public Long getLoseCount() {
    return loseCount;
  }

  public Long getDrawCount() {
    return drawCount;
  }
}
