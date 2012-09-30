package dbtsolver.situation

import java.util.Arrays
import dbtsolver.Situation

import java.lang.StringBuilder
import java.sql.SQLOutput

/**
 * http://forums.bgdev.org/index.php?showtopic=36614&st=0
 * http://flashgamedistribution.com/game/Flipit-Mobile
 */
case class FlipitSituation(predecessor: Situation, fields: Array[Array[Boolean]]) extends Situation {

  def isWinning(): Boolean = {
    return false
  }

  def successors(): List[FlipitSituation] = {
    var result = List[FlipitSituation]()

    return result
  }

  override def equals(that: Any): Boolean = that match {
    case other: FlipitSituation => false
    case _ => false
  }

  override def hashCode() = 1;

  // WEITER
  override def toString() : String = {
    val ff:Array[Object] = fields.map(Arrays.toString(_))
    return Arrays.toString(ff)
  }
}



