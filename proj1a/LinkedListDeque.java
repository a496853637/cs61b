public class LinkedListDeque<T> {

    private class TNode{
        private T item;
        private TNode prev;
        private TNode next;

        private TNode(T i, TNode p, TNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    /**
     * the initiate the LinkedList
     */
    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new TNode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0;i<other.size(); i++){
            this.addLast((T) other.get(i));
        }
    }
    /**
     * to add a new item to the front
     * @param item
     */
    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * to add a new item to the back
     * @param item
     */
    public void addLast(T item){
        sentinel.prev = new TNode(item,sentinel.prev,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * return if the list is empty
     * @return true for yes; false for no
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * return the size of the list
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * print the whole linkedlist
     */
    public void printDeque(){
        TNode toPrint = sentinel.next;
        for(int i = 0; i<size; i++){
            System.out.print(toPrint.item+" ");
            toPrint = toPrint.next;
        }
        System.out.println();
    }

    /**
     * remove the first item of the list
     * @return toRemove---the removed item
     */
    public T removeFirst(){
        T toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if(!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    /**
     * remove the last item of the list
     * @return toRemove---the removed item
     */
    public T removeLast(){
        T toRemove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if(!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }

    /**
     *  get the item at the given index
     * @param index
     * @return the got item
     */
    public T get(int index){
        TNode toGet = sentinel.next;
        if(index >= size){
            return null;
        }
        for(int i = 0; i < index; i++){
            toGet = toGet.next;
        }
        return toGet.item;
    }

    /**
     * the same as get but use recursion
     * @param index
     * @param curr
     * @return
     */
    private T getRecursive(int index, TNode curr){
        if (index == 0){
            return curr.item;
        }
        return getRecursive(index-1, curr.next);
    }

    public T getRecursive(int index){
        return getRecursive(index, sentinel.next);
    }
}