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
            $('#recordDiv').html(result);
        });
}

function getRecordsInterval(page, size) {
    var str = $('#recordForm').serialize();
    var id = $('#recordDiv').find('#userID').text();

    if (page !== undefined && size !== undefined) {
        str += "&page=" + page + "&size=" + size;
    }

    $.ajax({
        url: '/user/' + id,
        method: 'POST',
        data: str,
        dataType: 'html'
    })
        .done(function (result) {
            $('#recordDiv').html(result);
        });
}

function getDuration(duration) {

    var seconds = parseInt(duration % 60)
        , minutes = parseInt((duration / 60) % 60)
        , hours = parseInt((duration / (60 * 60)) % 24)
        , days = parseInt((duration / (60 * 60 * 24))
    );

    hours = (hours < 10) ? "0" + hours : hours;
    minutes = (minutes < 10) ? "0" + minutes : minutes;
    seconds = (seconds < 10) ? "0" + seconds : seconds;

    $('#duration').text(
        days + " дней "
        + hours + " часов "
        + minutes + " минут "
        + seconds + " секунд "
    );
}



/*$(document).ready(function () {
    var recordDiv = $(this).find('#recordDiv');
    recordDiv.on('click', '#recordFButton', function (event) {
        event.preventDefault();
        event.stopPropagation();
        getRecordsInterval();
    });
});*/
