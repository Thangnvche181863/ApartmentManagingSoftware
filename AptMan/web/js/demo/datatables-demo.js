// Call the dataTables jQuery plugin
$(document).ready(function () {
    $('#dataTable').DataTable({
        "columnDefs": [
            {
                "targets": 2, 
                "orderDataType": "dom-data-order" 
            },
            {
                "targets": 3, 
                "orderable": false 
            }
        ]
    });
});
