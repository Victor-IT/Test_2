function clearSearch() {
    var formInput = $('#formInputName1');
    var clearButton = $('#searchUserFormClearButton');

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