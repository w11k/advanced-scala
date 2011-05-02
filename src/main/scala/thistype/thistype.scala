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
package thistype

import util.Random


class ABad {

  var number = 1

  def double(): ABad = {
    number *= 2
    this
  }

  def triple(): ABad = {
    number = number * 3
    this
  }

}

class BBad1 extends ABad {

  def random(): BBad1 = {
    number *= Random.nextInt
    this
  }

}

class BBad2 extends ABad {

  override def double(): BBad2 = {
    super.double()
    this
  }

  override def triple(): BBad2 = {
    super.triple()
    this
  }

  def random(): BBad2 = {
    number *= Random.nextInt
    this
  }

}

class AGood {

  var number = 1

  def double(): this.type = {
    number *= 2
    this
  }

  def triple(): this.type = {
    number = number * 3
    this
  }

}

class BGood extends AGood {

  def random(): this.type = {
    number *= Random.nextInt
    this
  }

}



object ThisTypeApp {
  def main(args: Array[String]) {
    println("Number ABad: " + new ABad().double().triple().number)

    println("Number BBad1: " + new BBad1().random().number)
    println("Number BBad1: " + new BBad1().random().triple().number)

    // Geht nicht
    //println("Number BBad1: " + new BBad1().double().triple().random().number)

    println("Number BBad2: " + new BBad2().double().triple().random().number)

    println("Number AGood: " + new AGood().double().triple().number)

    println("Number BGood: " + new BGood().double().triple().random().number)
  }
}

