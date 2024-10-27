// ================================== custom date picker ==================================
document.addEventListener("DOMContentLoaded", function () {
    const dateInput = document.getElementsByClassName("dateInput");
    const today = new Date();

    let year = today.getFullYear();
    let month = ('0' + (today.getMonth() + 1)).slice(-2); // add leading zero if needed
    let day = ('0' + today.getDate()).slice(-2); // add leading zero if needed\

    let formattedDate = year + '-' + month + '-' + day;

    console.log(formattedDate)

    for (let i = 0; i < dateInput.length; i++) {
        dateInput.item(i).setAttribute("value", formattedDate);
        dateInput.item(i).setAttribute("min", formattedDate);
    }
});

// ================================== activate pop up ==================================
document.addEventListener('DOMContentLoaded', function () {
    // Get the button element
    const openModalBtn = document.getElementById('openModalBtn');

    // Open the modal when the button is clicked
    openModalBtn.addEventListener('click', function () {
        const myModal = new bootstrap.Modal(document.getElementById('myModal'));
        myModal.show();
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Get the button element
    const openModalBtn = document.getElementById('openWarningModelBtn');

    // Open the modal when the button is clicked
    openModalBtn.addEventListener('click', function () {
        const myModal = new bootstrap.Modal(document.getElementById('warningModal'));
        myModal.show();
    });
});

// ================================== delete request ==================================
function deleteItem() {
    fetch('/create-delivery/delete-item', {
        method: 'post'
    }).then(response => {
        if (response.ok) {
            console.log("OK");
            document.querySelector('.product-list').innerHTML = ''; // Only reload if the response is OK
        } else {
            console.error("INCOMPLETE");
        }
    }).catch(error => console.error('Error:', error));
}

function resetItem() {
    fetch('/delivery-edit/reset-item', {
        method: 'post'
    }).then(response => {
        if (response.ok) {
            console.log("OK");

            productList = document.querySelector('.product-list-inserted');
            if (productList.innerHTML.length != 0)
                productList.innerHTML = '';
        } else {
            console.error("INCOMPLETE");
        }
    }).catch(error => console.error('Error:', error));
}
