/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const feeInput = document.getElementById('fee');


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