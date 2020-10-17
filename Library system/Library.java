public class Library {
    /**
     * The maximal number of books this library can hold.
     */
    int maxBookCapacity;
    /**
     * The maximal number of books this library allows a single patron to borrow at the same time.
     */
    int maxBorrowedBooks;
    /**
     * The maximal number of registered patrons this library can handle.
     */
    int maxPatronCapacity;
    /**
     * array of books
     */
    Book[] bookArray;
    /**
     * array of patrons
     */
    Patron[] patronArray;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows a single patron to borrow at the same time
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity,
            int maxBorrowedBooks,
            int maxPatronCapacity) {
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
        this.bookArray = new Book[this.maxBookCapacity];
        this.patronArray = new Patron[this.maxPatronCapacity];


    }

    /**
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        for (int i = 0; i < this.bookArray.length; i++) {
            if (this.bookArray[i] == book) {
                return i;
            }}

        for (int i = 0; i < this.bookArray.length; i++) {
            if (this.bookArray[i] == null) {
                this.bookArray[i] = book;
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        if (bookId > this.bookArray.length) {
            return false;
        }
        return (this.bookArray[bookId] != null);

    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i <= this.bookArray.length; i++) {
            if (this.bookArray[i] == book) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if (bookId > this.bookArray.length) {
            return false;
        }
        if (this.bookArray[bookId] == null) {
            return false;
        }

        return (this.bookArray[bookId].getCurrentBorrowerId() == -1);
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot
     * and the patron was successfully registered,
     * a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        for (int i = 0; i < this.patronArray.length; i++) {
            if (this.patronArray[i] == patron) {
                return i;
            }
        }
        for (int i = 0; i < this.patronArray.length; i++) {
            if (this.patronArray[i] == null) {
                this.patronArray[i] = patron;
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        if (patronId > this.patronArray.length) {
            return false;
        }
        return (this.patronArray[patronId] != null);

    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library,
     * -1 otherwise.
     *
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library,
     * -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i <= this.patronArray.length; i++) {
            if (this.patronArray[i] == patron) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available,
     * the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.
     *
     * @param bookId   The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if (bookId > this.bookArray.length) {
            return false;
        }
        if (patronId > this.patronArray.length) {
            return false;
        }

        int counter = 0;
        for (int i = 0; i < this.bookArray.length; i++) {
            if (this.bookArray[i].getCurrentBorrowerId() == patronId) {
                counter += 1;
            }
        }
        if (counter == this.maxBorrowedBooks) {
            return false;}

        if (!isBookAvailable(bookId)){
            return false;
        }
        if (!this.patronArray[patronId].willEnjoyBook(this.bookArray[bookId])){
            return false;}

        this.bookArray[bookId].setBorrowerId(patronId);
        return true;
        }


    /**
     * Return the given book.
     *
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {
        bookArray[bookId].returnBook();
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     *
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most.
     * Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        int max = 0;
        int position = 0;
        for (int i = 0; i < this.bookArray.length; i++) {
            if (max < this.patronArray[patronId].getBookScore(this.bookArray[i])) {
                if (isBookAvailable(i)){
                max = this.patronArray[patronId].getBookScore(this.bookArray[i]);
                position = i;
            }}}

        return bookArray[position];


    }


}















