function clearSearch() {
    var formInput = $('#searchUFInputName');
    var clearButton = $('#searchUFClearButton');

    if (formInput.val() === '') {
        clearButton.addClass('hidden');
    } else {
        clearButton.removeClass('hidden');
    }
    formInput.val('');
    $('#searchUserForm').submit();
}

function getUserRecords(id) {
    $.ajax({
        url: '/user/' + id,
        method: 'GET',
        dataType: 'html'
    })
        .done(function (result) {
            $('#recordTable').html(result);
        });
}

function getRecordsInterval(id) {
    var str = $('#recordForm').serialize();
    alert(str + id);

    $.ajax({
        url: '/user/' + id,
        method: 'POST',
        data: str,
        dataType: 'html'
    })
        .done(function (result) {
            $('#recordTable').html(result);
        });

    return false;
}
