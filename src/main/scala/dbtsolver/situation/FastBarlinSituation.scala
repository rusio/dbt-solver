package dbtsolver.situation

import java.util.Arrays
import dbtsolver.Situation

/**
 * This implementation of Barlin is much faster because it drastically reduces the search space.
 * It uses the fact, that the winning height will always be somewhere between the minimal and the
 * maximal value of the initial situation. Therefore it only generates successors if they get
 * closer to that winning height.
 */
case class FastBarlinSituation(predecessor: FastBarlinSituation, bars: Array[Int]) extends Situation {

  def isWinning(): Boolean = {
    val b0 = bars(0)
    return bars.forall(_ == b0)
  }

  def successors(): List[FastBarlinSituation] = {

    // set the goal line to the average height of the initial situation
    if (GoalLine.height == Int.MinValue) {
      GoalLine.height = bars.sum / bars.size
    }

    // XXX: can we do this in a functinal style?
    for (i <- 0 until bars.length) {
      if (bars(i) < GoalLine.height) {
        val hiSucc = bars.clone();
        hiSucc(i) += 1
        return List(FastBarlinSituation(this, hiSucc))
      }
      if (bars(i) > GoalLine.height) {
        val loSucc = bars.clone();
        loSucc(i) -= 1
        return List(FastBarlinSituation(this, loSucc))
      }
    }
    return Nil
  }

  /**
   * @return True if the bars of the situations have equal heights.
   */
  override def equals(that: Any): Boolean = that match {
    case other: FastBarlinSituation => Arrays.equals(this.bars, other.bars)
    case _ => false
  }

  override def hashCode() = Arrays.hashCode(this.bars)

  override def toString() = Arrays.toString(bars)

}

object GoalLine {
  var height: Int = Int.MinValue
}
