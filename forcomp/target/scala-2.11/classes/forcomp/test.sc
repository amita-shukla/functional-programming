val word : String = "aabcb"
word.groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)







