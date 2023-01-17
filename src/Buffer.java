public class Buffer {
	 private int size, cnt, inptr, outptr  ;
	 private boolean reachedEnd;
	 int[] list ;
	 private MySemaphore elements, places ;
	 public Buffer(int size){
	    this.size = size ;
	    inptr = 0 ;
	    outptr = 0 ;
	    cnt = 0 ;
	    reachedEnd = false;
	    elements = new MySemaphore(0) ;
	    places = new MySemaphore(size) ;
	    list = new int[size] ;
	 }
	 public void produce(int value){
		 places.p(); // make sure the buffer is not full
		 synchronized (this){
			 cnt++ ;
		 }
		 list[inptr] = value ;
		 inptr = (inptr+1)%size ;
		 elements.v();
	 }
	 public int consume(){
		 elements.p();   // make sure that there is elements to consume
		 synchronized (this){
			 cnt-- ;
		 }
		 int value = list[outptr] ;
		 outptr = (outptr+1)%size ;
		 places.v();
		 return value ;
	 }
	
	 public synchronized void setReachedEnd() {
		 this.reachedEnd = true;
		 elements.v();
	 }
	 public synchronized boolean isReachedEnd() {
		 return (reachedEnd&&cnt==0);
	 }
}