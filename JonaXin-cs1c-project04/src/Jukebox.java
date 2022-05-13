package queues;

import cs1c.SongEntry;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * The class that simulates the function of adding a song to a playlist.
 * Creates three queue objects of type SongEntry, each represents a
 * playlist. The main function is adding songs to them.
 */
public class Jukebox {
    Queue<SongEntry> favoritePL = new Queue<>("favoritePL");
    Queue<SongEntry> roadTripPL = new Queue<>("roadTripPL");
    Queue<SongEntry> loungePL = new Queue<>("loungePL");

    /**
     * The method that helps add songs to playlist, read the input file
     * and add songs that can be searched in the tune store to a specific
     * playlist. Throw a FileNotFoundException if the input file cannot
     * be found.
     * @param inputFile the input file that contains songs to add.
     * @param allSongs the "tune store" for searching a matched song.
     */
    public void fillPlaylists(String inputFile, SongEntry[] allSongs)
    {
        File file = new File(inputFile);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                for (int i = 0; i < allSongs.length; i++) {
                    if (allSongs[i].getTitle().equals(parts[1])) {
                        switch (parts[0]) {
                            case "favorites":
                                favoritePL.enqueue(allSongs[i]);
                                break;
                            case "lounge":
                                loungePL.enqueue(allSongs[i]);
                                break;
                            case "road trip":
                                roadTripPL.enqueue(allSongs[i]);
                                break;
                        } // switch
                        break;
                    } // if match
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * The accessor for favorites playlist.
     * @return the whole favorites playlist.
     */
    public Queue<SongEntry> getFavoritePL() {
        return favoritePL;
    }

    /**
     * The accessor for road trip playlist.
     * @return the whole road trip playlist.
     */
    public Queue<SongEntry> getRoadTripPL()
    {
        return roadTripPL;
    }

    /**
     * The accessor for lounge playlist.
     * @return the whole lounge playlist.
     */
    public Queue<SongEntry> getLoungePL()
    {
        return loungePL;
    }
}
