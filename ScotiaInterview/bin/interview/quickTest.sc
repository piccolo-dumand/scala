package interview

object quickTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //some quick tests for the CountChracters
  CountCharacters.toWords(123491838)              //> res0: String#321149 = one hundred twenty three million four hundred ninety o
                                                  //| ne thousand eight hundred thirty eight
  CountCharacters.toWords(100008)                 //> res1: String#321149 = one hundred thousand eight
  CountCharacters.toWords(1200000000)             //> res2: String#321149 = one billion two hundred million
  CountCharacters.toWords(0)                      //> res3: String#321149 = zero
  CountCharacters.toWords(-11)                    //> res4: String#321149 = negative eleven
  
  CountCharacters.countCharsInWords(1999)         //> res5: Int#1080 = 32
  CountCharacters.countCharsInWordsOptimised(1999)//> res6: Int#1080 = 32
  
  CountCharacters.toWords(1212121213)             //> res7: String#321149 = one billion two hundred twelve million one hundred twe
                                                  //| nty one thousand two hundred thirteen
  CountCharacters.countCharsInWords(1212121213)   //> res8: Int#1080 = 78
  CountCharacters.countCharsInWordsOptimised(1212121213)
                                                  //> res9: Int#1080 = 78
  
  
  //some tests for the Immutable queue
  val q1: Queue[Int] = Queue.empty                //> q1  : interview#27.Queue#36923[Int#1080] = List()List()
  q1.isEmpty                                      //> res10: Boolean#2502 = true
	val q2 = q1.insert(2)                     //> q2  : interview#27.Queue#36923[Int#1080] = List()List(2)
	val q3 = q1.insert(1).insert(2).insert(3) //> q3  : interview#27.Queue#36923[Int#1080] = List()List(1, 2, 3)
	val q4 = q3.tail                          //> q4  : interview#27.Queue#36923[Int#1080] = List(2, 3)List()
	q4.head                                   //> res11: Option#1914[Int#1080] = Some(2)
	q1.isEmpty                                //> res12: Boolean#2502 = true
	val q5 = q4.insert(10)                    //> q5  : interview#27.Queue#36923[Int#1080] = List(2, 3)List(10)
	q5.head                                   //> res13: Option#1914[Int#1080] = Some(2)
	
}