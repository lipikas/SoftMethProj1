package library;

/**
 * This is an Album class that contains title, artist, genre, release Date & isAvailable.
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
     * @param title - Refers to the Album title.
     * @param artist
     * @param genre - Refers to Album genre.
     * @param releaseDate - Refers to Album release Date.
     */
    public Album(String title, String artist, String genre, String releaseDate) {
        this.title = title;
        this.artist = artist;
        this.genre = new Genre(genre);
        this.releaseDate = new Date(releaseDate);
    }

    /**
     * Method checks if title and artist are same for two albums.
     *
     * @param obj is Album object.
     * @return true if title and artist are title and artist are same for two albums or else false.
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
        return this.title + "::" + this.artist + "::" + this.genre.getGenreName() + "::" + this.releaseDate + ".";
    }

    public boolean getAvailability() { return this.isAvailable; }

    public void setNotAvailable() { this.isAvailable = false; }

    public void setAvailable() { this.isAvailable = true; }

    public void printDetails() {
//      Date date = new Date ("5/1/2000");
//      System.out.println("CompareTo date: " + this.releaseDate.compareTo(date));
//      System.out.println("Isvalid date: " +  this.releaseDate.isValid());
        System.out.println(this.title + "::" + this.artist + "::" + this.genre + "::");
        this.releaseDate.printDate();
        if (this.getAvailability()) System.out.print("::" + "is available");
        else System.out.print("::" + "is not available");
    }
}
