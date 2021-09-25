package library;

/**
 * Creates Genre Object.
 *
 * @author Lipika
 */
public class Genre {

    private String genreName;

    /**
     * Constructor sets genre.
     *
     * @param genre Refers to GenreName.
     */
    public Genre(String genre) {
        if (genre.equals("Classical") || genre.equals("classical")) {
            this.genreName = "Classical";
        } else if (genre.equals("Jazz") || genre.equals("jazz")) {
            this.genreName = "Jazz";
        } else if (genre.equals("Pop") || genre.equals("pop")) {
            this.genreName = "Pop";
        } else if (genre.equals("Country") || genre.equals("country")) {
            this.genreName = "Country";
        } else this.genreName = "Unknown";
    }

    /**
     * Gets Genre Name.
     *
     * @return genreName in String format.
     */
    public String getGenreName() {
        return this.genreName;
    }
}
