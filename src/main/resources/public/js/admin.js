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
    $('.modal-content .modal-isbn').text(selectedBook.isbn)
    $('.modal-content #edit-title').val(selectedBook.title)
    $('.modal-content #edit-author').val(selectedBook.author)
    $('.modal-content #edit-genre').val(selectedBook.genre)
    $('.modal-content #edit-cost').val(selectedBook.cost)
});


// Checkout books
$('.edit-book-submit').on('click', function(event) {

    var editedBook = new Object()
    editedBook.title = $('.modal-content #edit-title').val()
    editedBook.isbn = $('.modal-content .modal-isbn').text()
    editedBook.author = $('.modal-content #edit-author').val()
    editedBook.genre = $('.modal-content #edit-genre').val()
    editedBook.cost = $('.modal-content #edit-cost').val()

    console.log('User edited book: ', editedBook);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/update?bookIsbn=" + Number(editedBook.isbn),
        // url: "https://amazin-online-bookstore.herokuapp.com/update/?bookIsbn=" + Number(editedBook.isbn),
        data: JSON.stringify(editedBook),
        dataType: 'json',
    });
});