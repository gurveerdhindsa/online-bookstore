var user;
var books;
var allBooksHTML;
var searchedBooksHTML;
var timeout = null;

function promptUser(){
    user = prompt("Please enter your ID.");
}
window.onload=promptUser();


$(document).ready(function() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        // url: "http://localhost:8080/books"
        url: "https://amazin-online-bookstore.herokuapp.com/books"
    }).then(function(data) {
        if(data) {
            books = data;
            var len = books.length;
            allBooksHTML = "";
            var genreFilter = "";
            var authorFilter = "";

            if(len > 0){
                for(var i=0;i<len;i++){
                    if(books[i].title && books[i].author && books[i].cost){
                        genreFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].genre+"</a>"
                        authorFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].author+"</a>"

                        allBooksHTML += "<div class=\"card\">" +
                            "       <div class=\"card-body\">" +
                            "           <h5 class=\"card-title\ title\">"+books[i].title+"</h5>" +
                            "           <p class=\"card-title\ genre\">"+books[i].genre+"</p>" +
                            "           <h8 class=\"card-text\ author\">"+"by "+books[i].author+"</h8>" +
                            "           <p class=\"card-text\ cost\ item-info-cost\">"+"$"+books[i].cost+"</p>" +
                            "           <p class=\"card-text\ isbn\">"+books[i].isbn+"</p>" +
                            "           <a class=\"btn add-to-cart-btn\">Add to cart</a>" +
                            "       </div>" +
                            "     </div>";
                    }
                }

                if(allBooksHTML != ""){
                    $(".bookstore-books").append(allBooksHTML);
                }

                if(genreFilter != ""){
                    $(".dropdown-menu-genre").append(genreFilter);
                }

                if (authorFilter != ""){
                    $(".dropdown-menu-author").append(authorFilter);
                }
            }
        }
    });
});

function searchBook(searchInput) {
    if (timeout) {
        clearTimeout(timeout);
    }

    if (searchInput.length > 0) {
        timeout = setTimeout(function() {
            var data = {}
            data["title"] = searchInput

            $.ajax({
                type: "POST",
                contentType: "application/json",
                // url: "http://localhost:8080/title",
                url: "https://amazin-online-bookstore.herokuapp.com/title",
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
                                "           <h5 class=\"card-title\ title\">" + searchedBook[i].title + "</h5>" +
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
    } else {

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

