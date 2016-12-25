import scala.collection.SortedMap

val word : String = "Aabcb"
word.toLowerCase().groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)


val sentence : List[String] = List("aaa","bbc","cdc","cbc")

sentence.mkString("")

val dictionary : List[String] = List("aaa","bbc","cdc","bCb")

def wordOccurrences (word:String) :  List[(Char,Int)] = word.toLowerCase().groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)

wordOccurrences("aba")

dictionary.groupBy( (w:String) => wordOccurrences(w))

val x = List(('a', 1), ('d', 2), ('l', 1), ('r', 1))
val y = List(('r', 1),('d',1))

val c = y.toMap

val d = y.foldLeft(SortedMap[Char,Int]())((a,b) => a.+(b._1 -> b._2))

x.map{case (c ,i) => (c,i+1)}

x.map{case (c ,i) => if(d.contains(c)) (c,i - d(c)) else (c,i)}.filter(p=> p._2!=0)


type Occurrences = List[(Char,Integer)]
//def subtract(x : Occurrences, y : Occurrences) : Occurrences = {
//
//}










