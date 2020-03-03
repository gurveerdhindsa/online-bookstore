var cart = [];

$(document).ready(function() {
    if (cart.length == 0) {
        $(".checkout-btn").hide()
    }
});

// Add a book
$(document).on("click", ".add-to-cart-btn", function(){
    // Grab the title, author and cost of selected book
    var selectedBook = new Object()
    selectedBook.title = $(this).siblings('.title').text()
    selectedBook.author = $(this).siblings('.author').text()
    selectedBook.cost = $(this).siblings('.cost').text()

    console.log('Added: ', selectedBook);

    cart.push(selectedBook)

    $(".empty-cart").hide()
    $(".checkout-btn").show()

    html = "<span class=\"item\">\n" +
        "       <span class=\"item-left\">\n" +
        "           <span class=\"item-info\">" +
        "               <span class=\"item-info-title\">" + cart[cart.length-1].title + "</span>" +
        "               <span class=\"item-info-author\">" + cart[cart.length-1].author + "</span>" +
        "               <span class=\"item-info-cost\">" + cart[cart.length-1].cost + "</span>" +
        "           </span>" +
        "        </span>" +
        "   <span class=\"item-right\">\n" +
        "       <button class=\"btn btn-xs btn-danger pull-right remove-from-cart-btn\">X</button>\n" +
        "   </span>"
    $(".dropdown-menu-list").append(html);
});

// Remove a book
$(document).on("click", ".remove-from-cart-btn", function(){
    // Grab the title, author and cost of selected book
    var selectedBook = new Object()
    selectedBook.title = $(this).closest(".item").find(".item-info-title").text()
    selectedBook.author = $(this).closest(".item").find(".item-info-author").text()
    selectedBook.cost = $(this).closest(".item").find(".item-info-cost").text()

    console.log('Removed: ', selectedBook);

    // Find the index at which book is being removed
    for (var i=0; i < cart.length; i++) {
        if (cart[i].title == selectedBook.title &&
            cart[i].author == selectedBook.author &&
            cart[i].cost == selectedBook.cost) {
            cart.splice(i,1)
            break;
        }
    }

    // Remove HTML element
    $(this).closest(".item").remove()

    if (cart.length == 0) {
        $(".empty-cart").show()
        $(".checkout-btn").hide()
    }

    console.log(cart)
});