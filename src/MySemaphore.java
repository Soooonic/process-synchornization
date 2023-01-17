public class MySemaphore {
	protected int s = 0 ;
    protected MySemaphore(int initial) {
        s = initial ;
    }
    public synchronized void p() {
        s--;
        if (s <= 0)
            try {
                notifyAll();
                wait() ;
            } catch( InterruptedException e ) { }
    }
    public synchronized void v() {
        s++;
        notify() ;
    }
}