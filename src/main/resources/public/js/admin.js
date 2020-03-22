// Edit a book
$(document).on("click", ".edit-book", function() {
    // Grab the title, author and cost of selected book
    var selectedBook = new Object()
    selectedBook.title = $(this).siblings('.title').text()
    selectedBook.isbn = $(this).siblings('.isbn').text()
    selectedBook.genre = $(this).siblings('.genre').text()
    selectedBook.author = $(this).siblings('.author').text().replace('by ','')
    selectedBook.cost = Number($(this).siblings('.cost').text().replace('$',''))

    console.log('Editing the book: ', selectedBook);

    $('.modal-content .modal-title').text("Edit book: " + selectedBook.title)
    $('.modal-content #edit-title').val(selectedBook.title)
    $('.modal-content #edit-author').val(selectedBook.author)
    $('.modal-content #edit-genre').val(selectedBook.genre)
    $('.modal-content #edit-cost').val(selectedBook.cost)
});