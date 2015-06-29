public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq; //root is at pq[1]. pq[0] is always null to simplify computation
    private int N = 0;
    
    public MaxPQ(int max)
    {
        pq = (Key[]) new Comparable[max+1];
    }
    
    public int size()
    { return N; }
    
    public void insert(Key k)
    {
        pq[++N] = k; //insert at the bottom of the heap and swim it up to the rightful position
        swim(N);
    }
    
    public Key delMax()
    {
        Key max = pq[1]; // max is always at the root
        exch(1, N--); // take one from the bottom of the tree (The least one) and then sink it down to the rightful position
        pq[N+1] = null; // set to null the element you deleted
        sink(1);
        return max;
    }
    
    private void sink(int k)
    {
        while(2*k <= N)
        {
            int j = 2*k; // children of k are at 2k and 2k+1
            if(j < N && less(j, j+1)) j++; // we're going to switch k with the greater of the two children either j or j+1 so we check which one is greater j or j+1 and increment j to the greater of the two
            if(!less(k, j)) break; // there's no need to continue the loop of k has reached the rightful position. 
            exch(k, j);
            k = j; //continue to test if there is an anamoly in the children
        }
   }
   
   private void swim(int k)
   {
       int j = k/2; //j is the parent node for k
       while(k>1 && less(j, k))
       {
           exch(j, k);
           k = k/2;
       }
   }
   
   private void exch(int i, int j)
   {
       Key temp = pq[i];
       pq[i] = pq[j];
       pq[j] = temp;
   }
   
   private void less(int i, int j)
   {
       return pq[i].compareTo(pq[j]) < 0; 
   }
}
              