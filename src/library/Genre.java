package library;

public class Genre {

    private String genreName;

    /**
     * Constructor sets genre.
     *
     * @param genre - GenreName.
     */
    public Genre(String genre) {
        if (genre.equals("Classical")) {
            this.genreName = genre;
        } else if (genre.equals("Jazz")) {
            this.genreName = genre;
        } else if (genre.equals("Pop")) {
            this.genreName = genre;
        } else if (genre.equals("Country")) {
            this.genreName = genre;
        } else this.genreName = "Unknown";
    }

    /**
     * Gets Genre Name
     *
     * @return genreName
     */
    public String getGenreName() {
        return this.genreName;
    }

}
