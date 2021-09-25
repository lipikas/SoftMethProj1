package library;

/**
 * Creates Collection object.
 *
 * @author Kenisha
 */
public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    /**
     * Collection default constructor creates Collection object.
     */
    public Collection() {
        this.albums = new Album[4];
        this.numAlbums = 0;
    }

    /**
     * Searches for an Album in a Collection
     *
     * @param album is the album we are looking for
     * @return index of the album if found or else returns -1
     */
    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the capacity of the Albums array by 4
     */
    private void grow() {
        Album[] longerAlbums = new Album[numAlbums + 4];
        copyArray(albums, longerAlbums, numAlbums, 0);
        albums = longerAlbums;
    }

    /**
     * Adds an Album to the end of the Collection.
     *
     * @param album is Album we want to add.
     * @return true if Album is successfully added or false if the Album already exists in the Collection
     */
    public boolean add(Album album) {
        int index = find(album);
        if (index != -1) return false; //album already exists in collection

        if (numAlbums == albums.length) { //if collection is of max size
            grow(); //increase size
        }
        albums[numAlbums] = album; //adding album to last position
        numAlbums++;
        return true;
    }

    /**
     * Removes an Album from a Collection and maintains the same order.
     *
     * @param album is Album we want to remove.
     * @return true if Album is successfully removed or false if Album does not exist in Collection
     */
    public boolean remove(Album album) {
        int index = find(album);
        if (index == -1) return false; //album does not exist in collection
        for (int i = index; i < numAlbums; i++) {
            albums[i] = albums[i + 1];
        }
        numAlbums--;
        return true;
    }

    /**
     * Sets an Album to not available when lent out.
     *
     * @param album is Album we want to lend out.
     * @return true if Album is successfully lent out or false is album does not exist or is already unavailable
     */
    public boolean lendingOut(Album album) {
        int index = find(album);
        if (index == -1) return false; //album does not exist in collection
        if (!albums[index].getAvailability()) return false; //album is not available i.e., already lent out
        albums[index].setAvailability(false);
        return true;
    }

    /**
     * Sets an Album to available when returned.
     *
     * @param album is Album that is returned.
     * @return true if Album is successfully returned or false is album does not exist or is already available
     */
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if (index == -1) return false; //album does not exist in collection
        if (albums[index].getAvailability()) return false; //album is available i.e., never lent out
        albums[index].setAvailability(true);
        return true;
    }

    /**
     * Print the Albums in no specific order.
     */
    public void print() {
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
    }

    /**
     * Print the Albums in order of Release Date.
     */
    public void printByReleaseDate() {
        sortAlbumArray(0, numAlbums - 1, "date");
        print();
    }

    /**
     * Print the Albums in order of Genre.
     */
    public void printByGenre() {
        sortAlbumArray(0, numAlbums - 1, "genre");
        print();
    }

    /**
     * Recursive method that sorts Album array in ascending order by Genre or Release Date using Merge Sort
     *
     * @param leftInd  is the left index of the array
     * @param rightInd is the right index of the array
     * @param sortBy   to sort either by "genre" or by "date"
     */
    public void sortAlbumArray(int leftInd, int rightInd, String sortBy) {
        if (leftInd < rightInd) {
            int mid = (leftInd + rightInd) / 2;
            sortAlbumArray(leftInd, mid, sortBy); // Sort the first and second halves of the array
            sortAlbumArray(mid + 1, rightInd, sortBy);
            mergeAlbumArray(leftInd, mid, rightInd, sortBy); // Merge each of the halves
        }
    }

    /**
     * Helper method to merge two sorted arrays
     *
     * @param leftInd  is the left index of the array
     * @param mid      is the midpoint of the array
     * @param rightInd is the right index of the array
     * @param sortBy   to sort either by "genre" or by "date"
     */
    private void mergeAlbumArray(int leftInd, int mid, int rightInd, String sortBy) {
        int sizeSubArray1 = mid - leftInd + 1;
        int sizeSubArray2 = rightInd - mid;

        Album[] tempLeftArray = new Album[sizeSubArray1];
        Album[] tempRightArray = new Album[sizeSubArray2];

        copyArray(albums, tempLeftArray, sizeSubArray1, leftInd);
        copyArray(albums, tempRightArray, sizeSubArray2, mid + 1);

        int ind1 = 0, ind2 = 0; //initial indexes of temp arrays
        int compare;
        int indexMergedArray = leftInd; //initial index of the merged array
        boolean isDate = sortBy.equals("date");

        while (ind1 < sizeSubArray1 && ind2 < sizeSubArray2) {
            if (isDate) compare = tempLeftArray[ind1].compareDate(tempRightArray[ind2]);
            else compare = tempLeftArray[ind1].compareGenre(tempRightArray[ind2]);

            if (compare < 1) { //compare = -1 or 0 -> rightArray.date < leftArray.date
                albums[indexMergedArray] = tempLeftArray[ind1];
                ind1++;
            } else {
                albums[indexMergedArray] = tempRightArray[ind2];
                ind2++;
            }
            indexMergedArray++;
        }

        while (ind1 < sizeSubArray1) { //copy any remaining elements of left sub array
            albums[indexMergedArray] = tempLeftArray[ind1];
            ind1++;
            indexMergedArray++;
        }

        while (ind2 < sizeSubArray2) { //copy any remaining elements of right sub array
            albums[indexMergedArray] = tempRightArray[ind2];
            ind2++;
            indexMergedArray++;
        }
    }

    /**
     * Copy one array to another
     *
     * @param originalArray is the array we are copying from
     * @param newArray      is the array we are copying into
     * @param len           is the length we want to copy
     * @param offset        is the point in the original array from where we want to copy
     */
    private void copyArray(Album[] originalArray, Album[] newArray, int len, int offset) {
        if (originalArray == null || newArray == null) return;
        for (int i = 0; i < len; i++) {
            newArray[i] = originalArray[i + offset];
        }
    }

    /**
     * Accessor method that returns the number of albums
     *
     * @return number of Albums
     */
    public int getAlbumNumber() {
        return this.numAlbums;
    }

    /**
     * Accessor method that returns the Album
     *
     * @return the Album object
     */
    public Album[] getAlbums() {
        return this.albums;
    }

    /**
     * Testbed main for Collections class
     */
    public static void main(String[] args) {
        Collection collection = new Collection();

        //adding and removing albums from collection
        Album album1 = new Album("Album 1", "Artist 1", "Pop", "06/11/2001");
        Album album1Duplicate = new Album("Album 1", "Artist 1", "Jazz", "08/11/2001");
        Album album2 = new Album("Album 2", "Artist 2", "Classical", "07/11/1986");
        Album album3 = new Album("Album 1", "Artist 3", "Pop", "09/11/1996");
        Album album4 = new Album("Album 4", "Artist 4", "abcd", "10/11/2030");
        Album album5 = new Album("Album 5", "Artist 4", "Country", "10/11/2000");
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
        System.out.println("\n* * * PRINTING BY DATE * * *\n");
        collection.printByGenre();
    }
}
