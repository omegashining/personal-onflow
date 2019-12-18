function flotchart(coin) {
    function myFormatter(v, axis) {
        //console.log(v + ' - ' + axis.tickDecimals)
        //return parseFloat(v).toFixed(axis.tickDecimals);
        return parseFloat(String(v));
    }

    $.ajax({
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: '/Lotar/currency/chart/' + coin,
        error: function () {
            alert("An error occurred.");
        },
        success: function (exchangerates) {
            position = "right";
            $.plot($("#flot-chart-" + coin), [{
                    data: exchangerates.market_cap_by_available_supply,
                    label: "Market Capitalization"
                }, {
                    data: exchangerates.price_btc,
                    label: "Price (BTC)",
                    yaxis: 2
                }, {
                    data: exchangerates.price_usd,
                    label: "Price (USD)",
                    yaxis: 3
                }], {
                xaxes: [{
                        mode: 'time'
                    }],
                yaxes: [{
                        min: 0
                    }, {
                        // align if we are to the right
                        alignTicksWithAxis: position === "right" ? 1 : null,
                        position: position,
                        tickFormatter: myFormatter
                    }, {
                        // align if we are to the right
                        alignTicksWithAxis: position === "right" ? 1 : null,
                        position: position,
                        tickFormatter: myFormatter
                    }],
                legend: {
                    position: 'sw'
                },
                colors: ["#1ab394"],
                grid: {
                    color: "#999999",
                    hoverable: true,
                    clickable: true,
                    tickColor: "#D4D4D4",
                    borderWidth: 0,
                    hoverable: true //IMPORTANT! this is needed for tooltip to work,
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: %y - %x",
                    xDateFormat: "%d/%m/%y",
                    onHover: function (flotItem, $tooltipEl) {
                        // console.log(flotItem, $tooltipEl);
                    }
                }
            });
        }
    });
};

flotchart('bitcoin');
flotchart('ethereum-classic');
flotchart('ethereum');
flotchart('litecoin');
flotchart('nxt');
flotchart('waves');
flotchart('monero');