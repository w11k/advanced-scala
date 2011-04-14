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
package cakepattern

import scala.collection.immutable.Seq

object Configuration extends NewsContext {

  lazy val news = new News

  override lazy protected val channels = List(new SportsChannel, new MusicChannel)

  override lazy protected val numberOfMessages = 2
}

trait NewsContext {

  class News {
    def latestMessages: Seq[String] =
      channels flatMap { _.messages take numberOfMessages }
  }

  protected def channels: Seq[Channel]

  protected def numberOfMessages: Int
}

trait Channel {
  def messages: Seq[String]
}

class SportsChannel extends Channel {
  override def messages =
    "GER:ESP 0:1" :: "ARG:GER 0:4" :: "GER:ENG 4:1" :: Nil
}

class MusicChannel extends Channel {
  override def messages =
    "Eminem Recovery rocks!" :: "Hole Nobody's Daughter is great!" :: Nil
}

object NewsApp extends Application {
  Configuration.news.latestMessages foreach println
}
