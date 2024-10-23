//function to check value input in form

const nameInput = document.getElementById('name');
const typeInput = document.getElementById('type');
const feeInput = document.getElementById('fee');
const feeError = document.getElementById('feeError');
const imgInput = document.getElementById('img');
const iconInput = document.getElementById('icon');
const descriptionInput = document.getElementById('description');

function  validateForm() {


    // Reset not report error
    feeError.textContent = "";
    feeInput.classList.remove('is-invalid');

    // Check name
    if (nameInput.value.trim() === '') {
        alert("Name does not empty.");
        //focus() a method cursor input tag
        nameInput.focus();
        return false; // 
    }

    // Check type
    if (typeInput.value.trim() === '') {
        alert("Type dose not empty.");
        typeInput.focus();
        return false; // 
    }

    // Check fee
    const feeValue = feeInput.value.trim();
    if (isNaN(feeValue) || feeValue === '') {
        feeError.textContent = "Invalid value.";
        feeInput.classList.add('is-invalid'); // Add layer report error
        feeInput.focus();
        return false; // prevent form
    }

    // check url img
    const imgValue = imgInput.value.trim();
    if (imgValue === '') {
        alert("URL img does not empty.");
        imgInput.focus();
        return false; // prevent form
    }

    // check urm icon
    const iconValue = iconInput.value.trim();
    if (iconValue === '') {
        alert("URL icon .");
        iconInput.focus();
        return false; // prevent form
    }

    // Kiểm tra mô tả
    if (descriptionInput.value.trim() === '') {
        alert("Description does not empty.");
        descriptionInput.focus();
        return false; // prevent form
    }

    return true; // allow user submit
}

function previewImg(e) {
    const file = e.target.files[0];
    const imgPreview = document.getElementById('imgPreview'); // Tìm phần tử img
    const after = document.getElementById("after");

    if (file) {
        const url = URL.createObjectURL(file); // Tạo URL tạm thời cho file
        imgPreview.setAttribute("src", url); // Đặt URL cho img
        imgPreview.style.display = "block"; // Hiển thị img
        after.style.display = "none";
    } else {
        imgPreview.style.display = "none"; // Ẩn img nếu không có file
    }
}


