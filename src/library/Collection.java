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
        //System.out.println("Collection is growing"); //TODO: delete this statement
        Album[] longerAlbums = new Album[numAlbums + 4];
        for (int i = 0; i < numAlbums; i++) {
            longerAlbums[i] = albums[i];
        }
        albums = longerAlbums;
    }

    //adds an album to the end of the collection
    public boolean add(Album album) {

        int index = find(album);
        if(index != -1) return false; //album already exists in collection

        if (numAlbums == albums.length) { //if collection is of max size
            grow(); //increase size
        }
        albums[numAlbums] = album; //adding album to last position
        numAlbums++;
        return true;
    }

    //maintains same order of albums after deletion
    public boolean remove(Album album) {
        int index = find(album);
        if(index == -1) return false; //album does not exist in collection
        for (int i = index; i < numAlbums; i++) {
            albums[i] = albums[i + 1];
        }
        this.numAlbums--;
        return true;
    }

    //set to not available
    public boolean lendingOut(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album exists in collection
                if(!albums[i].getAvailability()) return false;// not available
                albums[i].setNotAvailable();
                return true; // available
            }
        }
        return false; //album does not exist in collection
    }

    //set to available
    public boolean returnAlbum(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) == true) { //checking if album exists in collection
                if (albums[i].getAvailability() == true) { //checking if album has indeed been lent out
                    return false; // album never lent out
                }
                albums[i].setAvailable();
                return true; // album returned
            }
        }
        return false; //album does not exist in collection
    }

    //display the list without specifying the order
    public void print() {
        //TODO: Check if numAlbums > 0 in driver method
        for (int i = 0; i < numAlbums; i++) {
            albums[i].toString();
        }
    }

//    public void printByReleaseDate() {}
//    public void printByGenre() {}

    /**
     * Gets Album number
     * @return number of Albums
     */
    public int getAlbumNumber(){
        return this.numAlbums;
    }
    /**
     * Testbed main for Collections class
     */
    public static void main(String[] args){
        Collection collection = new Collection();

        //adding and removing albums from collection
        Album album1 = new Album("Album 1", "Artist 1", "Pop", "06/11/2001");
        Album album1Duplicate = new Album("Album 1", "Artist 1", "Genre", "08/11/2001");
        Album album2 = new Album("Album 2", "Artist 2", "Rock", "07/11/2001");
        Album album3 = new Album("Album 1", "Artist 3", "Genre", "09/11/2001");
        Album album4 = new Album("Album 4", "Artist 4", "Metal", "10/11/2001");
        Album album5 = new Album("Album 5", "Artist 4", "Metal", "10/11/2001");
        boolean boolAdd = collection.add(album1);
        System.out.println("Album added: " + boolAdd);
        collection.print();
        boolAdd = collection.add(album1Duplicate);
        System.out.println("Album added: " + boolAdd);
        collection.print();
        boolAdd = collection.add(album2);
        System.out.println("Album added: " + boolAdd);
        collection.print();
//        boolean boolRemove = collection.remove(album2);
//        System.out.println("Album removed: " + boolRemove);
//        collection.print();
//        boolRemove = collection.remove(album2);
//        System.out.println("Album removed: " + boolRemove);
//        collection.print();
//        boolAdd = collection.add(album3);
//        System.out.println("Album added: " + boolAdd);
//        collection.print();
        boolAdd = collection.add(album4);
        System.out.println("Album added: " + boolAdd);
        collection.print();
        boolAdd = collection.add(album5);
        System.out.println("Album added: " + boolAdd);
        collection.print();
//        boolRemove = collection.remove(album1);
//        System.out.println("Album removed: " + boolRemove);
//        collection.print();
//        boolRemove = collection.remove(album3);
//        System.out.println("Album removed: " + boolRemove);
//        collection.print();
//        boolRemove = collection.remove(album3);
//        System.out.println("Album removed: " + boolRemove);
//        collection.print();
        boolean lend = collection.lendingOut(album3);
        System.out.println("Album lending: " + lend);
        collection.print();
        lend = collection.lendingOut(album1);
        System.out.println("Album lending: " + lend);
        collection.print();
        boolean boolReturn = collection.returnAlbum(album1);
        System.out.println("Album return: " + boolReturn);
        collection.print();
        boolReturn = collection.returnAlbum(album4);
        System.out.println("Album return: " + boolReturn);
        collection.print();
    }

    public Album[] getAlbums(){
        return this.albums;
    }
}
