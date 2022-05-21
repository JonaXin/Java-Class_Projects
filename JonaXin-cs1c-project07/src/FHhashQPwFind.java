package hashTables;

import java.util.NoSuchElementException;

/**
 * The extended class of FHhashQP, enables the client to search for
 * an object based on its key.
 * @param <KeyType> the type of the key.
 * @param <E> the generic class.
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType> >
        extends FHhashQP<E>{

    /**
     * The method that searches for an object in the array and return
     * it if found, else throws an exception.
     * @param key the key for searching.
     * @return the object if found.
     * @throws NoSuchElementException if the object is not in the array.
     */
    public E find(KeyType key) throws NoSuchElementException
    {
        E object = mArray[findPosKey(key)].data;
        if (object == null)
            throw new NoSuchElementException();
        return object;
    }

    /**
     * The private helper method that returns the index of the object
     * in the array based on its key.
     * @param key the key for searching.
     * @return the index of the object in the array.
     */
    protected int findPosKey( KeyType key )
    {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while ( mArray[index].state != EMPTY
                && mArray[index].data.compareTo(key)!= 0 )
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if ( index >= mTableSize )
                index -= mTableSize;
        }
        return index;
    }

    /**
     * The method that returns the hash value of the object based on
     * its key.
     * @param key the key for searching.
     * @return the hash value.
     */
    protected int myHashKey( KeyType key)
    {
        int hashVal;

        hashVal = key.hashCode() % mTableSize;
        if(hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }
}