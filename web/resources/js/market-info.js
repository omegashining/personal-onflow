function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

function marketinfo(coin) {
    $.ajax({
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: '/Lotar/currency/info/' + coin,
        error: function () {
            alert("An error occurred.");
        },
        success: function (result) {
            var data = result[0];
            $('#' + coin + '_price_usd').text(numberWithCommas(data.price_usd));
            $('#' + coin + '_volume_usd').text(numberWithCommas(data.volume_usd));
            $('#' + coin + '_market_cap_usd').text(numberWithCommas(data.market_cap_usd));
            $('#' + coin + '_available_supply').text(numberWithCommas(data.available_supply));
            $('#' + coin + '_total_supply').text(numberWithCommas(data.total_supply));
            $('#' + coin + '_percent_change_1h').text(numberWithCommas(data.percent_change_1h));
            $('#' + coin + '_percent_change_24h').text(numberWithCommas(data.percent_change_24h));
            $('#' + coin + '_percent_change_7d').text(numberWithCommas(data.percent_change_7d));
        }
    });
};

marketinfo('bitcoin');
marketinfo('ethereum-classic');
marketinfo('ethereum');
marketinfo('litecoin');
marketinfo('nxt');
marketinfo('waves');
marketinfo('monero');