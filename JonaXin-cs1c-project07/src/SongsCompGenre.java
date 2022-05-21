package hashTables;

import SongEntry;
import java.util.ArrayList;

/**
 * The class that compares objects based on song genres, and
 * determine the number of songs in each genre.
 */
public class SongsCompGenre implements Comparable<String>{

    String genreName;
    SongEntry song;
    ArrayList<SongEntry> data = new ArrayList<>();

    /**
     * The constructor of the class, assigns attributes' value.
     * @param s the SongEntry object
     */
    public SongsCompGenre(SongEntry s)
    {
        song = s;
        genreName = s.getGenre();
    }

    /**
     * The overridden method of toString()
     * @return the name of the song, duration etc as a String.
     */
    public String toString()
    {
        return song.toString();
    }

    /**
     * The overridden method of compareTo()
     * @param key the key to compare with.
     * @return the result of the comparison.
     */
    public int compareTo(String key)
    {
        return song.getGenre().compareTo(key);
    }

    /**
     * The overridden method of equal().
     * @param rhs
     * @return true if they are the same, and false otherwise.
     */
    public boolean equals( SongsCompGenre rhs )
    {
        return song.equals(rhs.song);
    }

    /**
     * The overridden method of hashCode()
     * @return the hash value of the song based on its genre.
     */
    public int hashCode()
    {
        return song.getGenre().hashCode();
    }

    /**
     * The method that adds a SongEntry object to a genre Arraylist.
     * @param s a song object.
     */
    public void addSong(SongEntry s)
    {
        data.add(s);
    }

    /**
     * the accessor for the Arraylist.
     * @return the Arraylist.
     */
    public ArrayList<SongEntry> getData()
    {
        return data;
    }

    /**
     * the accessor for the song's genre.
     * @return the genre of the song.
     */
    public String getName()
    {
        return genreName;
    }
}
