var user;
var isAdmin = false;
var books;
var allBooksHTML;
var genreFilter = [];
var allGenresHTML;
var authorFilter = [];
var allAuthorsHTML;
var searchedBooksHTML;
var timeout = null;


function promptUser(){
    user = prompt("Please enter your ID.");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: window.location.origin + "/admin/" + user
    }).then(function(data) {
        if (data) {
            isAdmin = true
        }
    });
}
window.onload=promptUser();

$(document).ready(function() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: window.location.origin + "/books"
    }).then(function(data) {
        if(data) {
            books = data;
            var len = books.length;
            allBooksHTML = "";

            if(len > 0) {
                for(var i=0;i<len;i++) {
                    if(books[i].isbn) {
                        // Only add genre to array if it wasn't added before
                        if (genreFilter.indexOf(books[i].genre) === -1) {
                            genreFilter.push(books[i].genre)
                        }
                        // Only add author to array if it wasn't added before
                        if (authorFilter.indexOf(books[i].author) === -1) {
                            authorFilter.push(books[i].author)
                        }

                        allBooksHTML += "<div class=\"card\">" +
                            "       <div class=\"card-body\">" +
                            "           <h6 class=\"card-title\ title\">"+books[i].title+"</h6>" +
                            "           <p class=\"card-title\ genre\">"+books[i].genre+"</p>" +
                            "           <h8 class=\"card-text\ author\">"+"by "+books[i].author+"</h8>" +
                            "           <p class=\"card-text\ cost\ item-info-cost\">"+"$"+books[i].cost+"</p>" +
                            "           <p class=\"card-text\ isbn\">"+books[i].isbn+"</p>" +
                            "           <a class=\"btn add-to-cart-btn\">Add to cart</a>" +
                            "       </div>" +
                            "     </div>";
                    }
                }

                if(allBooksHTML != "") {
                    $(".bookstore-books").append(allBooksHTML);
                }

                if(genreFilter.length > 0) {
                    allGenresHTML = ""
                    for (var i=0; i<genreFilter.length; i++) {
                        allGenresHTML += "<a class=\"dropdown-item\" href=\"#\">"+genreFilter[i]+"</a>"
                    }

                    $(".dropdown-menu-genre").append(allGenresHTML);
                }

                if (authorFilter.length > 0) {
                    allAuthorsHTML = ""
                    for (var i=0; i<authorFilter.length; i++) {
                        allAuthorsHTML += "<a class=\"dropdown-item\" href=\"#\">"+authorFilter[i]+"</a>"
                    }

                    $(".dropdown-menu-author").append(allAuthorsHTML);
                }
            }
            injectAdminFeatures()
        }
    });
});

function filterBooks() {
    if (timeout) {
        clearTimeout(timeout);
    }

    // Get search input value
    searchInput = $(".bookstore-filter-search").val()

    // Get the contents of filters
    selectedGenre = $(".dropdown-menu-genre").find(".dropdown-item-checked").text()
    selectedAuthor = $(".dropdown-menu-author").find(".dropdown-item-checked").text()

    if (searchInput.length > 0 || selectedGenre != "All Genres" || selectedAuthor != "All Authors") {
        timeout = setTimeout(function() {
            var data = {}
            data["title"] = searchInput

            if (selectedGenre == "All Genres") {
                data["genre"] = null
            } else {
                data["genre"] = selectedGenre
            }

            if (selectedAuthor == "All Authors") {
                data["author"] = ""
            } else {
                data["author"] = selectedAuthor
            }

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: window.location.origin + "/filter",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000
            }).then(function(data) {
                // Remove all books from view
                $(".bookstore-books").empty()

                if(data && data.length > 0) {
                    // Remove no books found message
                    $(".books-message").empty()

                    // Then display the books that match search
                    searchedBook = data;
                    searchedBooksHTML = "";

                    for (var i = 0; i < searchedBook.length; i++) {
                        if (searchedBook[i].title && searchedBook[i].author && searchedBook[i].cost) {
                            searchedBooksHTML += "<div class=\"card\">" +
                                "       <div class=\"card-body\">" +
                                "           <h6 class=\"card-title\ title\">" + searchedBook[i].title + "</h6>" +
                                "           <p class=\"card-title\ genre\">" + searchedBook[i].genre + "</p>" +
                                "           <h8 class=\"card-text\ author\">" + "by " + searchedBook[i].author + "</h8>" +
                                "           <p class=\"card-text\ cost\ item-info-cost\">" + "$" + searchedBook[i].cost + "</p>" +
                                "           <p class=\"card-text\ isbn\">"+ searchedBook[i].isbn + "</p>" +
                                "           <a class=\"btn add-to-cart-btn\ valid-button\">Add to cart</a>" +
                                "       </div>" +
                                "     </div>";
                        }
                    }

                    $(".bookstore-books").append(searchedBooksHTML);

                    injectAdminFeatures()
                } else {
                    $(".books-message").append("<h4 class=\"no-books-found-message\">No books found :(</h4>")
                }
            });
        }, 1000);
    } else {
        // Remove no books found message
        $(".books-message").empty()

        // Remove all books from search results
        searchedBooksHTML = ""
        $(".bookstore-books").empty()

        // Then display all books
        $(".bookstore-books").append(allBooksHTML);

        injectAdminFeatures()
    }
}

function inventoryCheckFromBookAdd(buttonClicked, selectedBook) {
    var quantity
    for(var i=0;i<books.length;i++){
        if (books[i].isbn == selectedBook.isbn) {
            quantity = books[i].quantity
            break;
        }
    }

    if (quantity - getQuantityFromCart(selectedBook) == 0) {
        buttonClicked.text = "Out of stock"
        $(buttonClicked).removeClass('valid-button')
        $(buttonClicked).addClass('disabled-button')
    }
}

function inventoryCheckFromBookRemove(removedBook) {
    button = $("p:contains('" + removedBook.isbn + "')").siblings('.add-to-cart-btn')
    if ($(button).text('Out of stock')) {
        $(button).text("Add to cart")
        $(button).removeClass('disabled-button')
        $(button).addClass('valid-button')
   }
}

// Tweak bootstrap dropd[own components to display selected item
$(function(){
    $(".dropdown-menu-filter").on('click', 'a', function() {
        $(this).closest(".bookstore-filter-dropdown").find(".btn:first-child").text($(this).text());
        $(this).closest(".bookstore-filter-dropdown").find(".btn:first-child").val($(this).text());

        $(this).siblings(".dropdown-item-checked").removeClass("dropdown-item-checked")
        $(this).addClass("dropdown-item-checked")

        filterBooks()
    });
});

function injectAdminFeatures() {
    // Inject admin features if they are logged in
    if (isAdmin) {
        $('.card-body').each(function(){
            $(this).append('<i class="fas fa-edit edit-book" data-toggle="modal" data-target=".bd-example-modal-lg"></i>');
        });
    }
}

