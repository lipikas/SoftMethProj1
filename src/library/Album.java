package library;

/**
 * Creates Album object and has respective album methods.
 *
 * @author Lipika
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre;
    private Date releaseDate;
    private boolean isAvailable;

    /**
     * Album constructor creates Album obj.
     *
     * @param title       Refers to the Album title.
     * @param artist      Refers to artist name.
     * @param genre       Refers to Album genre.
     * @param releaseDate Refers to Album release Date.
     */
    public Album(String title, String artist, String genre, String releaseDate) {
        this.title = title;
        this.artist = artist;
        this.genre = new Genre(genre);
        this.releaseDate = new Date(releaseDate);
        this.isAvailable = true;
    }

    /**
     * Creates Album object only for title and artist
     *
     * @param title  Refers to the Album title.
     * @param artist Refers to artist name.
     */
    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    /**
     * Method checks if title and artist are same for two albums.
     *
     * @param obj is Album object.
     * @return true if title and artist are same for two albums or else return false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Album) {
            Album album = (Album) obj;// casting
            return album.artist.equals(this.artist) && album.title.equals((this.title));
        }
        return false;
    }


    /**
     * Computes textual representation of Album object.
     *
     * @return String representation: **<Title>::<Artist>::<Genre>::<Release Date>::is available.
     */
    @Override
    public String toString() {
        String availability = "";
        if (this.getAvailability()) availability = "is available";
        else availability = "is not available";
        return this.title + "::" + this.artist + "::" + this.genre.getGenreName() + "::" + this.releaseDate.getDate() + "::" + availability;
    }

    /**
     * Gets availability of Albums.
     *
     * @return true if availability is true otherwise return false.
     */
    public boolean getAvailability() {
        return this.isAvailable;
    }

    /**
     * Sets isAvailable to false.
     */
    public void NotAvailable() {
        this.isAvailable = false;
    }

    /**
     * Sets isAvailable to true.
     */
    public void Available() {
        this.isAvailable = true;
    }
}
