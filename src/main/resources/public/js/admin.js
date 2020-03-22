// Edit a book
$(document).on("click", ".edit-book", function() {
    // Grab the title, author and cost of selected book
    var selectedBook = new Object()
    selectedBook.title = $(this).siblings('.title').text()
    selectedBook.isbn = $(this).siblings('.isbn').text()
    selectedBook.author = $(this).siblings('.author').text()
    selectedBook.cost = Number($(this).siblings('.cost').text().replace('$',''))

    console.log('Editing the book: ', selectedBook);
});