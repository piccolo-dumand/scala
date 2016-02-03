package interview

/*
 Implement the following api for a FIFO (first-in, first-out) Queue with the indicated complexities
 This should be an *immutable* queue
 */

/* The O(1) behaviour is acheived by mataining two lists, one for enqueue (in) and the other list for dequeue (out)
 * 1) when adding elements, we put it as the head of the in list    
 * 2) when accessing element, we get it from the head of the out list
 * 3) when getting the tail of the queue, we return a new queue with the in list unchanged, 
 *    but the out list is replaced by the tail of the out list. 
 * 4) The reason for returning a new Queue instead of modifying the exisiting one is to keep immutable.
 * In general, the above operations on list are all O(1)operations. 
 * 
 * In some corner cases, we have to get the last element from the in list or reverse the in list
 * these are not O(1) operations, but one can argue that these case happens only rarely, 
 * so the overall O(1) complexity is maintained
 */
class Queue[T]protected(protected val in: List[T], protected val out: List[T]){
  
  // O(1)
  def isEmpty: Boolean = in.isEmpty && out.isEmpty
  
  // O(1)
  def insert(t: T): Queue[T] = new Queue(t::in, out)

  // O(1)
  /* accessing the last element in a list is not O(1), so this operation is not always O(1)
   * but considering the fact that this happens only when the out list is empty, this function call can be viewed as 
   * approximately O(1)
   */
  def head: Option[T] = 
    if (!out.isEmpty) Some(out.head)
    else if (!in.isEmpty) Some(in.last) 
    else None

  // O(1) amortised
  /* again, reversing a list is not O(1), so this operation is not always O(1)
   * but considering the fact that this happens only when the out list is empty, this function call can be viewed as 
   * approximately O(1)
   */
  def tail: Queue[T] = 
    if (!out.isEmpty) new Queue(in, out.tail)
    else if (!in.isEmpty) new Queue(Nil, in.reverse.tail)
    else Queue.empty
    
    /*
     * override the toString method for testing purpose, 
     * just to see how the two internal lists are moving
     */
  override def toString(): String = 
    out.toString()+in.reverse.toString()
}

object Queue {
  def empty[T]: Queue[T] = new Queue(Nil, Nil)
}
