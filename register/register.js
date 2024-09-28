function save() {
    const member = {
        firstname: document.getElementById('firstname').value,
        lastname: document.getElementById('lastname').value,
        dob: document.getElementById('dob').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value,
    };

    // 保存会员信息到 localStorage
    let members = JSON.parse(localStorage.getItem('members')) || [];
    members.push(member);
    localStorage.setItem('members', JSON.stringify(members));

    alert('Registration Successful!');
    window.location.href = "../main/main.html"; 
}

function cancel() {
    window.location.href = "../main/main.html"; // jump to main page
}

