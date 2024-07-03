import java.util.Scanner

data class Book(val title: String, val author: String, val isbn: String, val genre: String) {
    override fun toString(): String {
        return "Title: $title, Author: $author, ISBN: $isbn, Genre: $genre"
    }
}

class Library {
    private val books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun addAllBooks(booksToAdd: List<Book>) {
        books.addAll(booksToAdd)
    }

    fun removeBook(book: Book) {
        books.remove(book)
    }

    fun removeAllBooks() {
        books.clear()
    }

    fun searchBooks(keyword: String): List<Book> {
        return books.filter {
            it.title.contains(keyword, ignoreCase = true) ||
                    it.author.contains(keyword, ignoreCase = true) ||
                    it.isbn.contains(keyword, ignoreCase = true) ||
                    it.genre.contains(keyword, ignoreCase = true)
        }
    }

    fun countBooks(): Int {
        return books.size
    }

    fun isEmpty(): Boolean {
        return books.isEmpty()
    }

    fun sortBooksByTitle() {
        books.sortBy { it.title }
    }

    fun sortBooksByAuthor() {
        books.sortBy { it.author }
    }

    fun sortBooksByIsbn() {
        books.sortBy { it.isbn }
    }

    fun sortBooksByGenre() {
        books.sortBy { it.genre }
    }
}

object LibraryManagementSystem {
    private val scanner = Scanner(System.`in`)
    private val library = Library()

    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            println("\nLibrary Management System")
            println("1. Add Book")
            println("2. Remove Book")
            println("3. Search Books")
            println("4. Count Books")
            println("5. Check if Library is Empty")
            println("6. Sort Books")
            println("7. Remove All Books")
            println("8. Add Multiple Books")
            println("9. Exit")
            print("Enter your choice: ")
            val choice = scanner.nextInt()
            scanner.nextLine() // Consume newline

            when (choice) {
                1 -> addBook()
                2 -> removeBook()
                3 -> searchBooks()
                4 -> countBooks()
                5 -> checkIfEmpty()
                6 -> sortBooks()
                7 -> removeAllBooks()
                8 -> addMultipleBooks()
                9 -> {
                    println("Exiting... Thank you!")
                    System.exit(0)
                }
                else -> println("Invalid choice. Please enter a number between 1 and 9.")
            }
        }
    }

    private fun addBook() {
        print("Enter title: ")
        val title = scanner.nextLine()
        print("Enter author: ")
        val author = scanner.nextLine()
        print("Enter ISBN: ")
        val isbn = scanner.nextLine()
        print("Enter genre: ")
        val genre = scanner.nextLine()

        val book = Book(title, author, isbn, genre)
        library.addBook(book)
        println("Book added successfully!")
    }

    private fun removeBook() {
        print("Enter ISBN of the book to remove: ")
        val isbn = scanner.nextLine()

        val searchResults = library.searchBooks(isbn)
        if (searchResults.isEmpty()) {
            println("No book found with ISBN: $isbn")
            return
        }

        println("Found books with the given ISBN:")
        for (i in searchResults.indices) {
            println("${i + 1}. ${searchResults[i]}")
        }

        print("Enter the number of the book to remove: ")
        val bookIndex = scanner.nextInt() - 1
        scanner.nextLine() // Consume newline
        if (bookIndex < 0 || bookIndex >= searchResults.size) {
            println("Invalid book number.")
            return
        }

        val bookToRemove = searchResults[bookIndex]
        library.removeBook(bookToRemove)
        println("Book removed successfully!")
    }

    private fun searchBooks() {
        print("Enter keyword to search: ")
        val keyword = scanner.nextLine()
        val searchResults = library.searchBooks(keyword)
        if (searchResults.isEmpty()) {
            println("No books found matching the keyword: $keyword")
        } else {
            println("Search results:")
            for (book in searchResults) {
                println(book)
            }
        }
    }

    private fun countBooks() {
        val count = library.countBooks()
        println("Total number of books: $count")
    }

    private fun checkIfEmpty() {
        val isEmpty = library.isEmpty()
        if (isEmpty) {
            println("The library is empty.")
        } else {
            println("The library has books.")
        }
    }

    private fun sortBooks() {
        println("Sort books by:")
        println("1. Title")
        println("2. Author")
        println("3. ISBN")
        println("4. Genre")
        print("Enter your choice: ")
        val choice = scanner.nextInt()
        scanner.nextLine() // Consume newline

        when (choice) {
            1 -> {
                library.sortBooksByTitle()
                println("Books sorted by title.")
            }
            2 -> {
                library.sortBooksByAuthor()
                println("Books sorted by author.")
            }
            3 -> {
                library.sortBooksByIsbn()
                println("Books sorted by ISBN.")
            }
            4 -> {
                library.sortBooksByGenre()
                println("Books sorted by genre.")
            }
            else -> println("Invalid choice. Please enter a number between 1 and 4.")
        }
    }

    private fun removeAllBooks() {
        library.removeAllBooks()
        println("All books removed from the library.")
    }

    private fun addMultipleBooks() {
        print("Enter the number of books to add: ")
        val numberOfBooks = scanner.nextInt()
        scanner.nextLine() // Consume newline

        val booksToAdd = mutableListOf<Book>()
        for (i in 0 until numberOfBooks) {
            print("Enter title: ")
            val title = scanner.nextLine()
            print("Enter author: ")
            val author = scanner.nextLine()
            print("Enter ISBN: ")
            val isbn = scanner.nextLine()
            print("Enter genre: ")
            val genre = scanner.nextLine()

            val book = Book(title, author, isbn, genre)
            booksToAdd.add(book)
        }

        library.addAllBooks(booksToAdd)
        println("Books added successfully!")
    }
}
