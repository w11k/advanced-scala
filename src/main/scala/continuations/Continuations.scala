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
package continuations

import scala.util.continuations.{ reset, shift }

object ContinuationsApp extends Application {

//  def plusOne(i: Int) = i + 1
//  def minusOne(i: Int) = i - 1
//  println(minusOne(plusOne(0)))
//
//  def plusOneCps(i: Int, cont: Int => Int) = cont(i + 1)
//  println(plusOneCps(0, minusOne))
//
//  def plusOneCps2(i: Int, cont: Int => Int) =
//    if (i > 0) cont(i + 1) else Int.MinValue
//  println(plusOneCps2(0, minusOne))
//
//  def plusOneCont(i: Int) = shift { cont: (Int => Int) =>
//    cont(i + 1)
//  }
//  println(reset { minusOne(plusOneCont(0)) })
//
//  def plusOneCont2(i: Int) = shift { cont: (Int => Int) =>
//    if (i > 0) cont(i + 1) else Int.MinValue
//  }
//  println(reset { minusOne(plusOneCont2(0)) })

  reset {
    val x = read("First Int: ")
    val y = read("Second Int: ")
    val result = x + y
    println("Result: %s" format result)
  }

  def read(label: String) = shift { cont: (Int => Unit) =>
    val s = readLine(label)
    try {
      cont(s.toInt)
    } catch {
      case _: NumberFormatException => println("Illegal argument: %s" format s)
    }
  }
}
