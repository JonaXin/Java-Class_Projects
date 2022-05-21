package hashTables;

import SongEntry;
import java.util.ArrayList;

/**
 * The class that creates and populates two hash tables of type
 * FHhashQPwFind, one for songs' ID and one for songs' genre.
 */
public class TableGenerator {

    private FHhashQPwFind<Integer, SongsCompInt> tableOfSongIDs;
    private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;
    private ArrayList<String> genreList;

    /**
     * The constructor of the class, initiate class attributes.
     */
    public TableGenerator()
    {
        tableOfSongIDs = new FHhashQPwFind<>();
        tableOfSongGenres = new FHhashQPwFind<>();
        genreList = new ArrayList<>();
    }

    /**
     * The method that populates the table of ID, for each SongEntry
     * object we assign it an ID and use it as the key to insert into
     * the hash array.
     * @param songList the array of SongEntry objects.
     * @return the table of song IDs.
     */
    public FHhashQPwFind<Integer, SongsCompInt> populateIDtable(SongEntry[] songList)
    {
        SongsCompInt currentSongID;

        for(int i = 0; i < songList.length; i++)
        {
            currentSongID = new SongsCompInt(songList[i]);
            tableOfSongIDs.insert(currentSongID);
        }
        return tableOfSongIDs;
    }

    /**
     * The method that populates the table of genres, given all songs
     * for each new genres we add them to the Arraylist, and for those
     * genres that has appeared before we can just ignore them.
     * @param songList the array of SongEntry objects.
     * @return the table of song genres.
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] songList)
    {
        for(int i = 0; i < songList.length; i++)
        {
            SongEntry currentSong = songList[i];
            if (!genreList.contains(currentSong.getGenre()))
            {
                SongsCompGenre genre = new SongsCompGenre(currentSong);
                genreList.add(currentSong.getGenre());
                for( int j = 0; j < songList.length; j++)
                {
                    if( songList[j].getGenre().equals(currentSong.getGenre()) )
                        genre.addSong(currentSong);
                    tableOfSongGenres.insert(genre);
                }
            }
        }
        return tableOfSongGenres;
    }

    /**
     * The accessor for the genre Arraylist.
     * @return the arraylist of genres.
     */
    public ArrayList<String> getGenreNames()
    {
        return genreList;
    }
}
