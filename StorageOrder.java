/**
 * StorageOrder.java
 * @author: Ghodratollah Aalipour
 * @author: Ruzan Sasuri
 * @author: Akash Venkatachalam
 *
 * This class includes the use of generics and merge-sort algorithm
 */
public class StorageOrder<T extends Comparable<T>>
{

    int initialSize = 10;
    int filled 	= 0;
    T[] data;
    T[]    localData ;
    T x;
    static int interatorPosition = 0;
    private boolean aord;


    /**
     *The constructor which initializes the values
     * @param y
     */
    @SuppressWarnings("unchecked")
    public StorageOrder(T y)	{
        x = y;
        aord = true;
        data= (T[])java.lang.reflect.Array.newInstance(x.getClass(), initialSize);
        for (int index=0; index<data.length - 1; index++)     {
            data[index] =  null;
        }

    }

    /***
     * Parametrized constructor initializing the values
     * @param y
     * @param asc
     */
    @SuppressWarnings("unchecked")
    public StorageOrder(T y,boolean asc)
    {
        x = y;
        //sorted = new Sorting(new Integer(0),asc);
        aord = asc;
        data= (T[])java.lang.reflect.Array.newInstance(x.getClass(), initialSize);
        for (int index = 0; index < data.length - 1; index++)
        {
            data[index] = null;
        }
    }

    /**
     * A 'to' array stores the data of 'from' array
     * @param to generic array
     * @param from generic array
     */
    public void copy(T[] to, T[] from)	{
        for (int index=0; index< filled ; index++)     {
            to[index] = from[index];
        }

    }
    /*public void sort() {
        if ( data.length == 1 )
            return;
        localData = new Integer[ filled ];
        copy(localData, data);
        for (int index=0; index<localData.length - 1; index++)     {
            for (int walker=0; walker<localData.length - index - 1; walker++)  {
                Integer left = localData[walker];
                Integer right = localData[walker+1];
                if ( compare(left, right ) > 0 )        {
                    Integer tmp = localData[walker];
                    localData[walker] = localData[walker + 1];
                    localData[walker+1] = tmp;
                }
            }
        }
        copy(data, localData);
    }*/


    /**
     * Method to add elements to the array
     * @param e element to be added
     * @return boolean , true if success
     */
    @SuppressWarnings("unchecked")
    public boolean add(Comparable<T> e)	{
        for (int index = 0; index<data.length; index++)
            if ( data[index] == null )	{
                data[index] = (T)e;
                filled++;
                data = sort(data);
                return true;
            }

        System.out.println("Array is full !!");

        return false;
    }

    /**
     * Method to remove an element from the array
     * @param e element to be removed
     * @return boolean, true if success
     */
    public boolean remove(T e)	{
        for (int index = 0; index < filled; index++)     {
            if (  data[index].compareTo(e) == 0 )	{
                data[index] = data[filled-1];
                data[filled-1] = null;
                filled --;
                data = sort(data);
                return true;
            }
        }
        System.out.println(e+ " Not found !!");
        return false;
    }

    /**
     *The contain method checks if the particular value is present in the array or not
     * @param e the target value
     * @return boolean value
     */
    public boolean contain(T e)	{
        for (int index = 0; index < filled; index++)     {
            if (  data[index].equals(e) )
                return true;
        }
        return false;
    }

    /**
     * Method to get the size of filled array
     * @return position until array is filled
     */
    public int size()	{
        return filled;
    }

    /**
     *Method to reset the array
     */
    public void startFromBeginning() {
        interatorPosition = 0;
    }

    /**
     * method to check the presence of atleast one element
     * @return
     */
    public boolean hasNext()	{
        return ( interatorPosition < filled );
    }

    /**
     *
     * @return
     */
    public T next()	{

        return data[interatorPosition++];
    }

    /**
     * Adds the list we want to add with our current list
     * @param c
     * @return
     */
    public boolean addAll(StorageOrder<T> c)	{
        while ( c.hasNext() ) {
            add(c.next() );
        }
        startFromBeginning();
        return true;
    }

    public double evaluate()	{
        double result = 0;
        while ( hasNext() ) {
            T aInteger = next();
            assert aInteger.equals(new Integer(0)) : "Division by zero cannot take place.";
            //result += 1.0 / aInteger;
        }
        return result;
    }
    //Our code

    /**
     * Method to remove element from the list
     * @param aList
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] removElmList(T[] aList){
        int len=aList.length;
        T[] newList=(T[])java.lang.reflect.Array.newInstance(x.getClass(), len - 1);;
        for (int i=0; i<len-1; i++){
            newList[i]=aList[i+1];
        }
        return newList;
    }

    /**
     * Concatenates two given list
     * @param list1 the first list, its on the left
     * @param list2 the second list, its on the right
     * @return result of concatenation of two lit
     */
    @SuppressWarnings("unchecked")
    public T[] listConcat(T[] list1, T[] list2){
        int len=list1.length+list2.length;
        T[] newList=(T[])java.lang.reflect.Array.newInstance(x.getClass(), len);
        for (int i=0; i<len; i++){
            if (i<list1.length) newList[i]=list1[i];
            else newList[i]=list2[i-list1.length];
        }
        return newList;
    }

    /**
     * This method merges two sorted list
     * @param list1 first list
     * @param list2 second list
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] merge(T[] list1, T[] list2){
        if (list1.length == 0) return list2;
        if (list2.length == 0) return list1;
        if (compare(list1[0],list2[0]) < 0) {
            T[] a=(T[])java.lang.reflect.Array.newInstance(x.getClass(), 1);
            a[0] = list1[0];
            return listConcat(a, merge(removElmList(list1), list2) );
        }

        else if (compare(list1[0],list2[0]) > 0) {
            T [] a=(T[])java.lang.reflect.Array.newInstance(x.getClass(), 1);
            a[0] = list2[0];
            return listConcat(a, merge(list1, removElmList(list2)));
        }
        else {
            T [] a=(T[])java.lang.reflect.Array.newInstance(x.getClass(), 2);
            a[0] = list1[0];
            a[1] = list2[0];
            return listConcat(a, merge(removElmList(list1), removElmList(list2)));
        }
    }

    /**
     * Method used to compare two objects
     * @param o1 object1
     * @param o2 object2
     * @return positive, negative or a zero value
     */
    public int compare(T o1, T o2)
    {
        int comp = 0;
        if(!aord)
        {
            if(o1 == null)
            {
                comp = -1;
            }
            else if(o2 == null)
            {
                comp = 1;
            }
            else
            {
                if (o1.compareTo(o2) < 0)
                {
                    comp = 1;
                }
                else if (o1.compareTo(o2) > 0)
                {
                    comp = -1;
                }
            }
        }
        else
        {
            if(o1 == null)
            {
                comp = 1;
            }
            else if(o2 == null)
            {
                comp = -1;
            }
            else
            {
                if (o1.compareTo(o2) > 0)
                {
                    comp = 1;
                }
                else if (o1.compareTo(o2) < 0)
                {
                    comp = -1;
                }
            }
        }
        return comp;
    }

    /**
     * Method to print out a given list
     * @param aList list to be printed
     */
    void printTo(T[] aList){
        int len=aList.length;
        for (int i=0; i<len;i++){
            System.out.print(aList[i]);
        }
    }

    /**
     *To sort an array based on merge sort algorithm
     * @param aList array to be sorted
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] sort(T[] aList){
        int len=aList.length;
        if (len<=1) return aList;
        else {
            T[] half1= (T[])java.lang.reflect.Array.newInstance(x.getClass(), len/2);
            T[] half2= (T[])java.lang.reflect.Array.newInstance(x.getClass(), (len+1)/2);
            for (int i=0; i<len; i++){
                if (i<=(len/2)-1) half1[i]=aList[i];
                else half2[i-len/2]=aList[i];
            }
            return merge(sort(half1),sort(half2));
        }
    }
    //our code

    /**
     * The main method
     * @param args
     */
    public static void main(String args[] )     {
        StorageOrder<Integer> aStorageOrder = new StorageOrder<Integer>(new Integer(0),true);
        aStorageOrder.add(1);
        aStorageOrder.add(12);
        aStorageOrder.add(2);
        aStorageOrder.add(-1);
        aStorageOrder.remove(12);
        System.out.println("aStorageOrder.contain(2); " + aStorageOrder.contain(2) );
        System.out.println("aStorageOrder.contain(22); " + aStorageOrder.contain(22) );
        StorageOrder<Integer> bStorageOrder = new StorageOrder<Integer>(new Integer(0),true);
        bStorageOrder.addAll(aStorageOrder);
        while(bStorageOrder.hasNext())
        {
            System.out.print(bStorageOrder.next() + " ");
        }
/**        bStorageOrder.startFromBeginning();
 while(aStorageOrder.hasNext())
 {
 System.out.print(aStorageOrder.next() + " ");
 }

 System.out.println(aStorageOrder);
 System.out.println("aStorageOrder.contains(a)  " + aStorageOrder.contain(0));
 System.out.println("aStorageOrder.contains(f)  " + aStorageOrder.contain(4));
 System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove(2));
 System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove(1));
 System.out.println(aStorageOrder);
 aStorageOrder.startFromBeginning();
 while ( aStorageOrder.hasNext() ) {
 System.out.println("	" + aStorageOrder.next() ) ;
 }
 */
    }
}