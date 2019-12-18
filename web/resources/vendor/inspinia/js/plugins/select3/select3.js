function selectCoin( el, coin ){
    $( el ).parent().parent().parent().find( '.selected-coin' ).val( coin );
    $( el ).parent().parent().parent().find( '.coin-label' ).html( coin );
    $( el ).parent().parent().children().each( function(){
        console.log( this );
        $( this ).css( 'background-color', '#fff' );
    } );
    $( el ).parent().css( 'background-color', 'rgb(245,245,245)' );
}

function convert(){
    src = $( '#coin-src' ).val();
    dst = $( '#coin-dst' ).val();
    amntsrc = $( '#amount-src' ).val();

    $.ajax( {
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify({ 'from': src, 'to': dst, 'amount': amntsrc }),
        url: '/xhr/coin/converter',
        error: function(){
            alert( "An error occurred." );
        },
        success: function( data ){
            $( '#amount-dst' ).val( data.amount )
        }
    } );
}