var user;
var books;
var AllBooksHTML;
var timeout = null;

function getUser(){
    user = prompt("Please enter your name.");
}
window.onload=getUser();


$(document).ready(function() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        // url: "http://localhost:8080/books"
        url: "https://amazin-online-bookstore.herokuapp.com/books"
    }).then(function(data) {
        if(data) {
            books = data.content
            var len = books.length;
            AllBooksHTML = "";
            var genreFilter = "";
            var authorFilter = "";

            if(len > 0){
                for(var i=0;i<len;i++){
                    if(books[i].title && books[i].author && books[i].cost){
                        genreFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].genre+"</a>"
                        authorFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].author+"</a>"

                        AllBooksHTML += "<div class=\"card\">" +
                            "       <div class=\"card-body\">" +
                            "           <h5 class=\"card-title\ title\">"+books[i].title+"</h5>" +
                            "           <h8 class=\"card-text\ author\">"+"by "+books[i].author+"</h8>" +
                            "           <p class=\"card-text\ cost\ item-info-cost\">"+"$"+books[i].cost+"</p>" +
                            "           <p class=\"card-text\ isbn\">"+"ISBN: "+books[i].isbn+"</p>" +
                            "           <h6 class = \"card-text\genre\ " + books[i].genre+"</h6>" +
                            "           <a class=\"btn add-to-cart-btn\">Add to cart</a>" +
                            "       </div>" +
                            "     </div>";
                    }
                }
                if(AllBooksHTML != ""){
                    $(".bookstore-books").append(AllBooksHTML);
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
    if (searchInput.length > 0) {
        if (timeout) {
            clearTimeout(timeout);
        }
        timeout = setTimeout(function() {
            console.log("MAKE A POST REQUEST WITH SEARCH TEXT:", searchInput)

            var data = {}
            data["title"] = searchInput

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/books",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000
            }).then(function(data) {
                if(data) {
                    // Remove all books from view
                    $(".bookstore-books").empty()

                    // Then display the books that match search
                } else {
                    // Display 'No books found' message
                }
            });
        }, 2000);
    } else {
        // Remove all books from search results
        // $(".bookstore-books").empty()

        // Then display all books
        $(".bookstore-books").append(AllBooksHTML);
    }
}