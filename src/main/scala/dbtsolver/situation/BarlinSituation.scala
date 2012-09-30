package dbtsolver.situation

import java.util.Arrays
import dbtsolver.Situation

/**
 * A situation for in a puzzle with three vertical bars.
 * Each bar has a height which is an integer value.
 * On every turn the player increases or decreases the
 * height of some bar by 1, until all bars are aligned in
 * a horizontal line.
 *
 *   -
 * - |
 * | | -  <-- align the bars to the same height in order to win
 * | | |
 * -----
 * 0 1 2
 */
case class BarlinSituation(predecessor: BarlinSituation, bars: Array[Int]) extends Situation {

  def isWinning(): Boolean = {
    return bars.distinct.size == 1
  }

  def successors(): List[BarlinSituation] = {
    // XXX: can we do this in a functinal style?
    var result = List[BarlinSituation]()
    for (i <- 0 until bars.length) {
      val hiSucc = bars.clone(); hiSucc(i) += 1
      val loSucc = bars.clone(); loSucc(i) -= 1
      result = BarlinSituation(this, hiSucc) :: BarlinSituation(this, loSucc) :: result
    }

    return result
  }

  /**
   * @return True if the bars of the situations have equal heights.
   */
  override def equals(that: Any): Boolean = that match {
    case other: BarlinSituation => Arrays.equals(this.bars, other.bars)
    case _ => false
  }

  override def hashCode() = Arrays.hashCode(this.bars)

  override def toString() = Arrays.toString(bars)
}
