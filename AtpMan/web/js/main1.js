// Lấy các phần tử từ DOM
const nameInput = document.getElementById('name');
const typeInput = document.getElementById('type');
const feeInput = document.getElementById('fee');
const feeError = document.getElementById('feeError');
const fileInput = document.getElementById('img');
const iconInput = document.getElementById('icon');
const descriptionInput = document.getElementById('description');

// Hàm kiểm tra giá trị input trong biểu mẫu
function validateForm() {
    const validImageTypes = ['image/jpeg', 'image/png', 'image/gif'];
    const imgPreview = document.getElementById('imgPreview'); // Phần tử img hiện tại
    const currentImgSrc = imgPreview.getAttribute("src"); // Lấy src hiện tại
    const imgFile = fileInput.files[0]; // Lấy tệp hình ảnh từ input

    // Kiểm tra loại tệp hình ảnh nếu người dùng đã chọn file mới
    if (imgFile) {
        const fileType = imgFile.type;
        if (!validImageTypes.includes(fileType)) {
            alert('Please upload a valid image file (JPEG, PNG, or GIF).');
            fileInput.value = ''; // Reset file input
            return false; // Ngăn không cho gửi biểu mẫu
        }
    }

    // Reset không báo lỗi
    feeError.textContent = "";
    feeInput.classList.remove('is-invalid');

    // Kiểm tra tên
    if (nameInput.value.trim() === '') {
        alert("Name cannot be empty.");
        nameInput.focus();
        return false; 
    }

    // Kiểm tra loại
    if (typeInput.value.trim() === '') {
        alert("Type cannot be empty.");
        typeInput.focus();
        return false; 
    }

    // Kiểm tra phí
    const feeValue = feeInput.value.trim();
    if (isNaN(feeValue) || feeValue === '') {
        feeError.textContent = "Invalid fee value.";
        feeInput.classList.add('is-invalid'); // Thêm lớp báo lỗi
        feeInput.focus();
        return false; // Ngăn không cho gửi biểu mẫu
    }

    // Kiểm tra URL ảnh
    if (!imgFile && !currentImgSrc) {
        alert("Image URL cannot be empty.");
        fileInput.focus(); // Đặt tiêu điểm vào input
        return false; // Ngăn không cho gửi biểu mẫu
    }

    // Kiểm tra URL icon
    const iconValue = iconInput.value.trim();
    if (iconValue === '') {
        alert("Icon URL cannot be empty.");
        iconInput.focus();
        return false; // Ngăn không cho gửi biểu mẫu
    }

    // Kiểm tra mô tả
    if (descriptionInput.value.trim() === '') {
        alert("Description cannot be empty.");
        descriptionInput.focus();
        return false; // Ngăn không cho gửi biểu mẫu
    }

    return true; // Cho phép người dùng gửi biểu mẫu
}

// Hàm xem trước hình ảnh
function previewImg(e) {
    const file = e.target.files[0];
    const imgPreview = document.getElementById('imgPreview'); // Phần tử img hiện tại
    
    if (file) {
        const url = URL.createObjectURL(file); // Tạo URL tạm thời
        imgPreview.setAttribute("src", url); // Gán URL cho img
        imgPreview.style.display = "block"; // Hiển thị ảnh mới
    } else {
        // Nếu không có file mới, giữ nguyên URL hiện tại
        if (imgPreview.getAttribute("src")) {
            imgPreview.style.display = "block"; // Hiển thị ảnh hiện tại
        } else {
            imgPreview.style.display = "none"; // Ẩn nếu không có URL
        }
    }
}

