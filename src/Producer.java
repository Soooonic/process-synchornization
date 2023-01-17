public class Producer implements Runnable {
    private Buffer buff;
    int N;
    public Producer(Buffer buff, int n){
        N = n;
        this.buff=buff;
    }
    @Override
    public void run() {
        if(N>=1) {
            buff.produce(1);
        }
        buff.produce(2);
        for (int i = 3; i <=N ; i+=2) {
            if(checkprime(i)){
                buff.produce(i);
            }
        }
        buff.setReachedEnd();
    }
    public boolean checkprime(int x){
        for (int i = 2; i <= x/i; i++) {
            if(x%i==0)
                return false;
        }
        return true;
    }
}
