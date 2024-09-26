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
    if (username === "admin" && password === "12345") {
        alert("登录成功！");
        errorMessage.style.display = "none";
        // 可在这里进行页面跳转或其他处理
    } 
    
    // Wrong username or password
    else {
        errorMessage.style.display = "block";
        errorMessage.textContent = "Wrong username or password";
    }
}
