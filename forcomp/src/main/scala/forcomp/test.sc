val word : String = "Aabcb"
word.toLowerCase().groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)


val sentence : List[String] = List("aaa","bbc","cdc","cbc")

sentence.mkString("")

val dictionary : List[String] = List("aaa","bbc","cdc","bCb")

def wordOccurrences (word:String) :  List[(Char,Int)] = word.toLowerCase().groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)

wordOccurrences("aba")

dictionary.groupBy( (w:String) => wordOccurrences(w))












