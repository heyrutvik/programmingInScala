package gist

import java.io.File
import java.util.Scanner

import scala.collection.immutable.HashMap
import scala.collection.immutable.Stream.Empty

/**
  * String holder.. Optional
  * @param s String
  */
case class Word(s: String) {
  override def toString: String = s
}

/**
  * WordStream creates Stream using words spitted by getWord function
  * @param getWord
  */
case class WordStream(getWord: () => Option[Word]) {
  def get: Stream[Word] = {
    getWord() match {
      case Some(w) => w #:: get
      case None => Empty
    }
  }
}

object CountWord extends App {

  /**
    * @param f File
    * @return function which returns Option[Word] if available otherwise None
    */
  def initScanner(f: File): () => Option[Word] = {
    val scanner = new Scanner(f)
    () => if (scanner.hasNext) Some(Word(scanner.next())) else None
  }

  /**
    * create map and return function which operates on that map.
    * @return function which take Word and insert into map with count
    */
  def initMap(): Word => Map[Word, Int] = {
    var table = new HashMap[Word, Int]
    w => {
      table = table + ((w, table.getOrElse(w, 0) + 1))
      table
    }
  }

  def count(file: File): Map[Word, Int] = {
    val wordStream = WordStream(initScanner(file))
    val addWord = initMap()
    wordStream.get.flatMap(w => addWord(w)).toMap
  }

  println(count(new File("/tmp/CountWord.txt")))
}