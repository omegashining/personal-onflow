//Flot Multiple Axes Line Chart
$(function() {

	function euroFormatter(v, axis) {
        return parseFloat(v).toFixed(axis.tickDecimals) + "$";
    }

	$.ajax({
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: '/xhr/coin/bitcoin/chart',
            error: function () {
                alert("An error occurred.");
            },
            success: function (exchangerates) {
	            position="right";
                $.plot($("#flot-line-chart-multi"), [{
			            data: exchangerates.eur,
			            label: "BTC/EUR exchange rate"
			        },{
			            data: exchangerates.usd,
			            label: "BTC/USD exchange rate",
			            yaxis: 2
			        }], {
			            xaxes: [{
			                mode: 'time'
			            }],
			            yaxes: [{
			                min: 0
			            }, {
			                // align if we are to the right
			                alignTicksWithAxis: position == "right" ? 1 : null,
			                position: position,
			                tickFormatter: euroFormatter
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
			                borderWidth:0,
			                hoverable: true //IMPORTANT! this is needed for tooltip to work,

			            },
			            tooltip: true,
			            tooltipOpts: {
			                content: "%s for %x was %y",
			                xDateFormat: "%y-%0m-%0d",

			                onHover: function(flotItem, $tooltipEl) {
			                    // console.log(flotItem, $tooltipEl);
			                }
			            }
				});

            }
        });

});
