window.addEventListener('load', function(){
    (function(){
    //con fectch invocamos a la api de odontologos con el metodo get
    //y nos devuelve un json con todos los odontologos
    const url= '/odontologos';
    const settings = {
        method: 'GET'
    };
    fetch(url, settings)
    .then(function(respuesta){
        return respuesta.json();
    })
    .then(function (info){
    console.log(info);
    //recorremos el json de odontologos
    for(let i=0; i<info.length; i++){
        //por cada odontologo creamos una fila
        //cada fila tendra un id
        var table = document.getElementById("odontologoTable");
        var odontologoRow= table.insertRow()
        let tr_id = 'tr_'+ info[i].id;
        odontologoRow.id = tr_id;
        // armamos cada columna de la fila
        odontologoRow.innerHTML = '<td class=\"td_nombre\">' + info[i].nombre.toUpperCase() + '</td>' +
        '<td class=\"td_apellido\">' + info[i].apellido.toUpperCase() + '</td>' ;


    };
    })
    })

(function(){
    let pathname = window.location.pathname;
    if(pathname == "/odontologoLista.html"){
    document.querySelector(".nav .nav-item a:last").addClass("active");
    }
    })

})