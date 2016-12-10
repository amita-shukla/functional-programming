val chars = List('a','b','a')
val c = chars groupBy (f => f)
//val timesList = List((c.keys,c.mapValues(_.size)))

//val smList = List(c.mapValues(_.size))
//val newList = smList.flatten

val mewList = chars.groupBy(f => f).mapValues(_.size).toList.flatten




