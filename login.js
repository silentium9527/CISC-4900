function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const errorMessage = document.getElementById("error-message");

    // Login verification
    // Empty input
    if (username === "" || password === "") {
        errorMessage.style.display = "block";
        errorMessage.textContent = "Username or password cannot be empty";
        return;
    }

    // Login successful
    if (username === "admin" && password === "123") {
        alert("Login successful！");
        errorMessage.style.display = "none";
        // jump to main page
        window.location.href = "../main/main.html";
    } 
    
    // Wrong username or password
    else {
        errorMessage.style.display = "block";
        errorMessage.textContent = "Wrong username or password";
    }
}
