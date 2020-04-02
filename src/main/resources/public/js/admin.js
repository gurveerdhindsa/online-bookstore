

// Edit a book
$(document).on("click", ".edit-book", function() {

    $('#edit-modal').modal("show")

    // Grab the title, author and cost of selected book
    var selectedBook = new Object()
    selectedBook.title = $(this).siblings('.title').text()
    selectedBook.isbn = $(this).siblings('.isbn').text()
    selectedBook.genre = $(this).siblings('.genre').text()
    selectedBook.author = $(this).siblings('.author').text().replace('by ','')
    selectedBook.cost = Number($(this).siblings('.cost').text().replace('$',''))

    for (var i=0; i<books.length; i++) {
        if (books[i].isbn === selectedBook.isbn) {
            selectedBook.publisher = books[i].publisher
            selectedBook.quantity = Number(books[i].quantity)
        }
    }

    console.log('Editing the book: ', selectedBook);

    $('.edit-book-modal-content .modal-title').text("Edit book: " + selectedBook.title)
    $('.edit-book-modal-content .modal-isbn').text(selectedBook.isbn)
    $('.edit-book-modal-content #edit-title').val(selectedBook.title)
    $('.edit-book-modal-content #edit-author').val(selectedBook.author)
    $('.edit-book-modal-content #edit-publisher').val(selectedBook.publisher)
    $('.edit-book-modal-content #edit-genre').val(selectedBook.genre)
    $('.edit-book-modal-content #edit-cost').val(selectedBook.cost)
    $('.edit-book-modal-content #edit-quantity').val(selectedBook.quantity)
});


// Submit a book edit
$(document).on("click", ".edit-book-submit", function() {
    var editedBook = new Object()
    editedBook.title = $('.edit-book-modal-content #edit-title').val()
    editedBook.isbn = $('.edit-book-modal-content .modal-isbn').text()
    editedBook.author = $('.edit-book-modal-content #edit-author').val()
    editedBook.publisher = $('.edit-book-modal-content #edit-publisher').val()
    editedBook.genre = $('.edit-book-modal-content #edit-genre').val()
    editedBook.cost = Number($('.edit-book-modal-content #edit-cost').val())
    editedBook.quantity = Number($('.edit-book-modal-content #edit-quantity').val())

    console.log('User edited book: ', editedBook);


    $('#edit-modal').modal("hide")


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location.origin + "/update?bookIsbn=" + editedBook.isbn,
        data: JSON.stringify(editedBook),
        dataType: 'json',
        timeout: 3000
    }).done(function() {
        // alert ("Book successfully edited!")
        refreshBooks()
    })
});

// Add a new book
$(document).on("click", ".add-book-submit", function() {
    console.log('Adding a new book...');

    var newBook = new Object()

    newBook.title = $('.add-book-modal-content #add-title').val()
    newBook.isbn = $('.add-book-modal-content #add-isbn').val()
    newBook.author = $('.add-book-modal-content #add-author').val()
    newBook.genre = $('.add-book-modal-content #add-genre').val()
    newBook.cost = Number($('.add-book-modal-content #add-cost').val())

    console.log("New book: ", newBook)

    $('#add-modal').modal("hide")


    // $.ajax({
    //     type: "POST",
    //     contentType: "application/json",
    //     url: window.location.origin + "/admin/add",
    //     data: JSON.stringify(newBook),
    //     dataType: 'json',
    //     success: addSuccess()
    // });
    //
    // function addSuccess() {
    //     console.log("add successful!")
    //
    //     refreshBooks()
    //     alert("Added a book!")
    // }
});