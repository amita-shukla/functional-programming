val word : String = "Aabcb"
word.toLowerCase().groupBy(c=> c).mapValues(s=>s.length).toList.sortBy(_._1)








