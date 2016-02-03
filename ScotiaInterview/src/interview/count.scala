package interview

object CountCharacters {
  /*
    We want to produce a function that counts the number of chars in an English language spelling of a number.
    The top-level function countCharsInWords is provided for you.

    Part 1) Implement the toWords function

    Returns i as spelled in english (without commas, "and"s etc)
    Assume US notation, ie billion = 10^9
    eg.
     toWords(9) = "nine"
     toWords(99) = "ninety nine"
     toWords(999) = "nine hundred ninety nine"
  */
  
  def toWords(i: Int): String = {
    
    val units_string = Array("thousand", "million", "billion")
    val units_number = Array(1000, 1000000, 1000000000)
    /*
    since the input argument is of type Int, the ranges above is large enough. 
    if one wants to do that for larger numbers (Long for instance), this can be easily expanded by defining
    val units_string = Array("thousand", "million", "billion", "trillion", "quadrillion")
    val units_number = Array(1000, 1000000, 1000000000, 1E+12, 1E+15)
    or even larger, you name it.
    */
    if (i<0) 
      "negative "+toWords(-i)
    else if(i<1000){
      toWords1000(i)
    }else{
      var units = (math.log10(i)/3).toInt - 1 //figure out what "unit" we put for our numbers
      if(i%units_number(units)==0) //again, this check can be omitted if one doesn't handle zero in toWord100 function
        toWords1000(i/units_number(units))+ " " + units_string(units)
      else
        toWords1000(i/units_number(units))+ " " + units_string(units) + " "+ toWords(i%units_number(units))
    }
  }//end toWords
  
  /*
  convert number to words when number is less than 1000
  The number 1000 is a bit special in English, needed a separated definition for this function 
  it could be defined inside the toWord function for better encapsulation, but the trade off is very long function bodies and perhaps reduced readability
  I think which is a better practice is arguable and the choice should be made based on the design goals, 
  for now, just define it in the outer scope. 
  */
  def toWords1000(i: Int): String ={
    if(i>=1000) throw new IllegalArgumentException("input should be less than 1000")
    val tens = Array("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    val twenty = Array("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    
    //convert number to words when number is less than 100
    //note the fact that one can define nested functions in scala
    //defined here for better encapsulation
    def toWords100(i: Int): String = {
      if(i>=100) 
        throw new IllegalArgumentException("input should be less than 100")
      else if(i==0) //if one doesn't care about zero, the downstream function can be simplified a lot, we handle it for completeness 
        "zero" 
      else if (i<20) 
        twenty(i)
      else if(i%10==0)
        tens(i/10) 
      else
        tens(i/10)+" "+twenty(i%10)
    }
    
    if(i<100) 
      toWords100(i) 
    else if(i%100==0) 
      twenty(i/100)+" hundred" 
    else 
      twenty(i/100)+" hundred "+ toWords100(i%100)
  }//end toWords1000
  
  
  // countCharsInWords(9) == 4
  // countCharsInWords(99) == 10
  // countCharsInWords(999) == 21
  def countCharsInWords(i: Int): Int = toWords(i).filter(_ != ' ').length

  /*
    Part 2) Implement the countCharsInWordsOptimised function

    This should be a more efficient implementation of countCharsInWords.
    This does not need to re-use the above and may be an entirely different algorithm.
    Try to make this implementation as efficient as you can
  */
  

  def countCharsInWordsOptimised(i: Int): Int = {
    //val units_string = Array("thousand", "million", "billion")
    val units_length = Array(8, 7, 7) //the length of the 3 unit words above
    val units_number = Array(1000, 1000000, 1000000000)
    if(i<1000) 
      /* one could further simplify this by defining the optimised version for numbers smaller than 1000, 
       * but the trade off is more code and reduced readability. 
       * Considering the bottleneck is not likely to be the <1000 numbers, i will just use the existing function 
       */
      countCharsInWords(i)
     else {
       var units = (math.log10(i)/3).toInt - 1 //figure out what "unit" we put for our numbers
       countCharsInWordsOptimised(i/units_number(units)) + units_length(units) + countCharsInWordsOptimised(i%units_number(units)) 
     } 
  }
  
  
  
  
  
  
  
  
  
}