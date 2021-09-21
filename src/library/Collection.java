package library;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    //default constructor
    public Collection(){
        this.albums = new Album[4];
        this.numAlbums = 0;
    }

    //returns album index, or return -1
    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return -1;
    }

    //increase the capacity of the array list by 4
    private void grow() {
        Album[] longerAlbums = new Album[numAlbums + 4];
        for (int i = 0; i < numAlbums; i++) {
            longerAlbums[i] = albums[i];
        }
        albums = longerAlbums;
    }

    //adds an album to the end of the collection
    public boolean add(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album already exists in collection
                return false;
            }
        }
        if (numAlbums == albums.length) { //if collection is of max size
            grow(); //increase size
        }
        albums[numAlbums] = album; //adding album to last position
        return true;
    }

    //maintains same order of albums after deletion
    public boolean remove(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album exists in collection
                for (int j = i; j < numAlbums - 1; j++) {
                    albums[i] = albums[i + 1];
                }
                return true;
            }
        }
        return false; //album does not exist in collection
    }

    //set to not available
    public boolean lendingOut(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album exists in collection
                albums[i].setNotAvailable();
                return true;
            }
        }
        return false; //album does not exist in collection
    }

    //set to available
    public boolean returnAlbum(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album exists in collection
                if (albums[i].getAvailability() == true) { //checking if album has indeed been lent out
                    return false;
                }
                albums[i].setAvailable();
                return true;
            }
        }
        return false; //album does not exist in collection
    }

    //display the list without specifying the order
    public void print() {
        //TODO: Check if numAlbums > 0 in driver method
        for (int i = 0; i < numAlbums; i++) {
            albums[i].printDetails();
            ;
        }
    }
//    public void printByReleaseDate() {}
//    public void printByGenre() {}

    /**
     * Testbed main for Collections class
     */
//    public static void main(String[] args){
//        Collection collection = new Collection();
//        collection.albums = {}
//    }
}
