package library;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
    //returns album index, or return -1
    private int find(Album album) {
        for(int i = 0; i < numAlbums; i++){
            if(albums[i].equals(album)){
                return i;
            }
        }
        return -1;
    }
    //increase the capacity of the array list by 4
    private void grow() {
        Album[] longerAlbums = new Album[numAlbums + 4];
        for(int i = 0; i < numAlbums; i++){
            longerAlbums[i] = new Album(albums[i]);
        }

    }
//    public boolean add(Album album) {}
//    public boolean remove(Album album) {}//maintains same order of albums after deletion
//    public boolean lendingOut(Album album) {} //set to not available
//    public boolean returnAlbum(Album album) {} //set to available
//    public void print() {} //display the list without specifying the order
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
