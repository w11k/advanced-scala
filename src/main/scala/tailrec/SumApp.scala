/*
 * Copyright 2011 Weigle Wilczek GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weiglewilczek.advancedscala
package tailrec

import System.{ currentTimeMillis => now }
import scala.annotation.tailrec

object SumApp extends Application {

  val numbers = (1 to 5000).toList
  println("Without tailrec: %s".format(measure(sum(numbers))))
  println("With tailrec: %s".format(measure(sumtr(numbers))))

  /** Calculate the sum over a List[Int] without tail call optimization. */
  private def sum(numbers: List[Int]): Long = {
    numbers match {
      case Nil => 0
      case x :: xs => x + sum(xs)
    }
  }

  /** Calculate the sum over a List[Int] with tail call optimization. */
  private def sumtr(numbers: List[Int]): Long = {
    @tailrec def sumtr(numbers: List[Int], result: Long): Long = numbers match {
      case Nil => result
      case x :: xs => sumtr(xs, x + result)
    }
    sumtr(numbers, 0)
  }

  private def measure(f: => Long): Long = {
    val t0 = now
    for (i <- 1 to 5000) { f }
    now - t0
  }
}
