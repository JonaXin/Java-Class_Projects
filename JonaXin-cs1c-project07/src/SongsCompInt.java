package hashTables;

import cs1c.SongEntry;

/**
 * The class that compares objects based on song IDs.
 */
public class SongsCompInt implements Comparable<Integer>{

    SongEntry data;

    /**
     * The constructor of the class, assigns attributes' value.
     * @param e the SongEntry object.
     */
    public SongsCompInt(SongEntry e)
    {
        data = e;
    }

    /**
     * The overridden method of toString()
     * @return the name of the song, duration etc as a String.
     */
    public String toString()
    {
        return data.toString();
    }

    /**
     * The overridden method of compareTo()
     * @param key the key to compare with.
     * @return the result of the comparison.
     */
    public int compareTo(Integer key)
    {
        return data.getID() - key;
    }

    /**
     * The overridden method of equal().
     * @param rhs
     * @return true if they are the same, and false otherwise.
     */
    public boolean equals( SongsCompInt rhs )
    {
        return data.equals(rhs.data);
    }

    /**
     * The overridden method of hashCode()
     * @return the hash value of the song based on its ID.
     */
    public int hashCode()
    {
        return data.getID();
    }
}
