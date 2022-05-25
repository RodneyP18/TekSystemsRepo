// This is just a function I learned to make the article creation form on the my createArt.html.

tinymce.init({
    selector: 'textarea#here',
    a_plugin_option: true,
    a_configuration_option: 400,
    height: "480"
});

// This is my example of using a collection array, and populating a drop down list with a for loop on my createArt.html.

var select = document.getElementById("selectCat");
var options = ["Tabby", "Maine Coon", "Siamese", "Persian", "Bengal", "Munchkin"];

for (var i = 0; i < options.length; i++) {
    var opt = options[i];
    var el = document.createElement("option");
    el.textContent = opt;
    el.value = opt;
    select.appendChild(el);
}

// This is my example of using a regex for email validation and validation for the the form on my register page.

function validateform() {
    var mailformat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var username = document.registerForm.username.value;
    var password = document.registerForm.password.value;
    var firstname = document.registerForm.first_name.value;
    var lastname = document.registerForm.last_name.value;
    var email = document.registerForm.email.value;

    if (username == null || username == "") {
        alert("Name can't be blank");
        return false;
    } else if (password.length < 6) {
        alert("Password must be at least 6 characters long.");
        return false;
    } else if (firstname == null || firstname == "") {
        alert("Name can't be blank");
        return false;
    } else if (lastname == null || lastname == "") {
        alert("Name can't be blank");
        return false;
    } else if (email.match(mailformat)) {
        return true;
    } else {
        alert("You have entered an invalid email address!");
        return false;
    }

}