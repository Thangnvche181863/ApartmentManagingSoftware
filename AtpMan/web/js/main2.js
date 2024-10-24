// Lấy các phần tử từ DOM
const nameInput = document.getElementById('name');
const typeInput = document.getElementById('type');
const feeInput = document.getElementById('fee');
const feeError = document.getElementById('feeError');
//const fileInput = document.getElementById('img');
const iconInput = document.getElementById('icon');
const descriptionInput = document.getElementById('description');

// Hàm kiểm tra giá trị input trong biểu mẫu
function validateForm() {

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
function previewImg(event) {
    const file = event.target.files[0];
    const imgPreview = document.getElementById('imgPreview'); // Phần tử img hiện tại
    
    if (file) {
        const url = URL.createObjectURL(file); // Tạo URL tạm thời cho ảnh mới
        imgPreview.src = url; // Gán URL cho phần tử img
        imgPreview.style.display = "block"; // Hiển thị ảnh mới
        imgPreview.onload = function() {
            URL.revokeObjectURL(imgPreview.src); // Giải phóng bộ nhớ khi ảnh tải xong
        };
    } else {
        // Kiểm tra xem URL hiện tại có sẵn không, nếu có, giữ nguyên URL
        const currentURL = imgPreview.getAttribute("src");
        if (currentURL) {
            imgPreview.style.display = "block"; // Hiển thị ảnh hiện tại nếu có
        } else {
            imgPreview.style.display = "none"; // Ẩn nếu không có URL nào
        }
    }
}


// Định dạng trường feeInput khi có sự thay đổi
feeInput.addEventListener('input', function (e) {
    let value = e.target.value;

    // Loại bỏ các ký tự không phải số, dấu phẩy hoặc dấu chấm
    value = value.replace(/[^0-9.,]/g, '');

    // Chỉ giữ lại một dấu chấm cho phần thập phân
    const parts = value.split('.');
    if (parts.length > 2) {
        value = parts.slice(0, 2).join('.'); // Giữ lại chỉ hai phần
    }

    // Định dạng phần nguyên (trước dấu chấm)
    const integerPart = parts[0].replace(/,/g, '') // Bỏ dấu phẩy hiện có
        .replace(/\B(?=(\d{3})+(?!\d))/g, ','); // Thêm dấu phẩy cho hàng nghìn

    // Kết hợp phần nguyên và phần thập phân, đảm bảo có dấu thập phân nếu có
    e.target.value = parts.length > 1 ? integerPart + '.' + parts[1] : integerPart;
});

// Định dạng số khi trang được tải
window.onload = function() {
    let initialValue = feeInput.value.replace(/[^0-9.,]/g, ''); // Xóa các ký tự không phải số
    if (initialValue) {
        const parts = initialValue.split('.');
        parts[0] = parts[0].replace(/,/g, '') // Bỏ dấu phẩy hiện có
                            .replace(/\B(?=(\d{3})+(?!\d))/g, ','); // Định dạng phần nguyên
        feeInput.value = parts.join('.'); // Kết hợp lại
    }
};

