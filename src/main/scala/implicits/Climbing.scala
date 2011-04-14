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
package implicits

import scala.xml.NodeSeq

trait XmlSerializable[A] {
  implicit def toXmlSerializable(a: A) = new {
    def toXml(implicit format: XmlFormat[A]) = format toXml a
  }
}

trait XmlFormat[A] {
  def toXml(a: A): NodeSeq
}

object Grade extends XmlSerializable[Grade] {

  object Qualifier extends Enumeration {
    val Plus = Value("+")
    val Minus = Value("-")
    implicit def valueToOption(value: Value) = Option(value)
  }

  implicit def intToGrade(value: Int) = Grade(value)

  implicit object GradeXmlFormat extends XmlFormat[Grade] {
    def toXml(grade: Grade) = {
      import scala.xml.{ Attribute, Null, Text }
      (<grade value={ grade.value.toString } /> /: grade.qualifier) { (xml, qualifier) =>
        xml % Attribute("qualifier", Text(qualifier.id.toString), Null)
      }
    }
  }
}

case class Grade(value: Int, qualifier: Option[Grade.Qualifier.Value] = None) {
  override def toString = "%s%s".format(value, qualifier getOrElse "")
}

object Route extends XmlSerializable[Route] {
  implicit object RouteXmlFormat extends XmlFormat[Route] {
    def toXml(route: Route) = <route name={ route.name } >{ route.grade.toXml }</route>
  }
}

case class Route(name: String, grade: Grade) {
  override def toString = "%s (%s)".format(name, grade)
}

object ClimbingApp {

  def main(args: Array[String]) {
    val fightGravity = Route("Fight Gravity", Grade(8, Grade.Qualifier.Plus))
    println(fightGravity)
    println(fightGravity.toXml)
    implicit val localRouteXmlFormat = new XmlFormat[Route] {
      def toXml(route: Route) = <dummy/>
    }
    val kasperl = Route("Kasperltheater", 8)
    println(kasperl)
    println(kasperl.toXml)
  }
}
