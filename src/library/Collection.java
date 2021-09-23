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
//        for (int i = 0; i < numAlbums; i++) {
//            longerAlbums[i] = albums[i];
//        }
        copyArray(albums, longerAlbums, numAlbums,0);
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
        numAlbums--;
        return true;
    }

    //set to not available
    public boolean lendingOut(Album album) {
        int index = find(album);
        if(index == -1) return false; //album does not exist in collection
        if(!albums[index].getAvailability()) return false; //album is not available i.e., already lent out
        albums[index].setAvailability(false);
        return true;
    }

    //set to available
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if(index == -1) return false; //album does not exist in collection
        if(albums[index].getAvailability()) return false; //album is available i.e., never lent out
        albums[index].setAvailability(true);
        return true;
    }

    //display the list without specifying the order
    public void print() {
        //TODO: Check if numAlbums > 0 in driver method
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
    }

    public void printByReleaseDate() {
        sortAlbumArray(0, numAlbums - 1, "date");
        print();
    }

    public void printByGenre() {
        sortAlbumArray(0, numAlbums - 1, "genre");
        print();
    }

    public void sortAlbumArray(int leftInd, int rightInd, String sortBy) {
        if (leftInd < rightInd) {
            int mid = (leftInd+rightInd)/2;

            // Sort first and second halves
            sortAlbumArray(leftInd, mid, sortBy);
            sortAlbumArray(mid+1, rightInd, sortBy);

            // Merge the sorted halves
            mergeAlbumArray(leftInd, mid, rightInd, sortBy);
        }
    }

    private void mergeAlbumArray(int leftInd, int mid, int rightInd, String sortBy) {
        int sizeSubarray1 = mid - leftInd + 1;
        int sizeSubarray2 = rightInd - mid;

        /* Create temp arrays */
        Album[] tempLeftArray = new Album[sizeSubarray1];
        Album[] tempRightArray = new Album[sizeSubarray2];

        /*Copy data to temp arrays*/
        copyArray(albums, tempLeftArray, sizeSubarray1, leftInd);
//        for (int i = 0; i < sizeSubarray1; i++){
//            tempLeftArray[i] = albums[leftInd + i];
//        }
        copyArray(albums, tempRightArray, sizeSubarray2, mid + 1);
//        for (int i = 0; i < sizeSubarray2; i++){
//            tempRightArray[i] = albums[mid + i + 1];
//        }

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int ind1 = 0, ind2 = 0, compare;

        // Initial index of merged subarray array
        int indexMergedArray = leftInd;
        boolean isDate = sortBy.equalsIgnoreCase("date");
        while (ind1 < sizeSubarray1 && ind2 < sizeSubarray2) {
            if(isDate)  compare = tempLeftArray[ind1].compareDate(tempRightArray[ind2]);
            else    compare = tempLeftArray[ind1].compareGenre(tempRightArray[ind2]);

            if (compare < 1) //equals -1 or 0 -> rightArray.date < leftArray.date
            {
                albums[indexMergedArray] = tempLeftArray[ind1];
                ind1++;
            }
            else
            {
                albums[indexMergedArray] = tempRightArray[ind2];
                ind2++;
            }
            indexMergedArray++;
        }

        /* Copy remaining elements of L[] if any */
        while (ind1 < sizeSubarray1)
        {
            albums[indexMergedArray] = tempLeftArray[ind1];
            ind1++;
            indexMergedArray++;
        }

        /* Copy remaining elements of R[] if any */
        while (ind2 < sizeSubarray2)
        {
            albums[indexMergedArray] = tempRightArray[ind2];
            ind2++;
            indexMergedArray++;
        }
    }

    //copy array from one to another
    private void copyArray(Album[] originalArray, Album[] newArray, int len, int offset){
        if(originalArray == null || newArray == null)   return;
        for(int i = 0; i < len; i++){
            newArray[i] = originalArray[i + offset];
        }
    }

    /**
     * Gets Album number
     *
     * @return number of Albums
     */
    public int getAlbumNumber() {
        return this.numAlbums;
    }

    public Album[] getAlbums() {
        return this.albums;
    }

    //TODO: find out if you need a testbed main
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
