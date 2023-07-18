
//Funcion para listar las parcelas con el cultivo según el año
function listarCultivos() {
    var ejercicio = document.getElementById("ejercicio").value;
    $.ajax({
        url: 'Controller',
        async: false,
        data: {
            ejercicio: ejercicio,
            ACTION: 'DETALLES.LISTAR'
        },
        dataType: 'json',
        success: function (response) {
            var divDetalles = document.querySelector(".detalles");
            var cad = "";

            for (var contador in response) {
                var parcela_id = response[contador].parcela_id;
                var nombre = response[contador].nombre;
                var poligono = response[contador].poligono;
                var parcela = response[contador].parcela;
                var superficie = response[contador].superficie;
                var cultivo = response[contador].cultivo;


                cad += "<div class='Cultivo'>";
                cad += "<p class='Descripcion-Tarjeta'>" + parcela_id + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + nombre + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + poligono + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + parcela + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + superficie + "</p>";

                cad += "<div class='Descripcion-Tarjeta'>";
                cad += "<select id='selectCultivo"+parcela_id+"'>";
                cad += "<option disabled selected hidden>" + cultivo + "</option>"; // Opción deshabilitada y oculta
                cad += "<option value='CULTIVO'>CULTIVO</option>";
                cad += "<option value='ALFANCE'>ALFANCE</option>";
                cad += "<option value='CEBADA'>CEBADA</option>";
                cad += "<option value='TRIGO'>TRIGO</option>";
                cad += "<option value='BARBECHO'>BARBECHO</option>";
                cad += "<option value='ESPARCETA'>ESPARCETA</option>";
                cad += "<option value='ALFALFA'>ALFALFA</option>";
                cad += "<option value='GIRASOL'>GIRASOL</option>";
                cad += "<option value='CENTENO'>CENTENO</option>";
                cad += "<option value='YEROS'>YEROS</option>";
                cad += "</select>";
                cad += "</div>";
                cad += "<button class='Descripcion-Tarjeta' id='Boton-Editar' onclick='cambiarCultivo("+parcela_id+")'>Cambiar</button>";

                
                cad += "</div>";


            }

            divDetalles.innerHTML = cad; // Establecer el contenido HTML del elemento divPeliculas
        },
        error: function (error) {
            alert(error);
        }
    });
}

function cambiarCultivo(parcela_id) {
  var nuevoCultivo = document.getElementById('selectCultivo'+parcela_id).value;
  var ejercicio = document.getElementById("ejercicio").value;
  
  $.ajax({
        url: 'Controller',
        async: false,
        data: {
            parcela_id: parcela_id,
            cultivo: nuevoCultivo,
            ejercicio: ejercicio,
            ACTION: 'DETALLES.CAMBIAR'
        },
        dataType: 'text',
        success: function (response) {
           if (response === 'iguales') {
                alert("Es igual que los 2 años anteriores");
            } else {

            }
            
        },
        error: function (error) {
            alert(error);
        }
    });
}


//Funcion para listar todas las parcelas 
function listarParcelas() {
    $.ajax({
        url: 'Controller',
        async: false,
        data: {
            ACTION: 'PARCELAS.LISTAR'
        },
        dataType: 'json',
        success: function (response) {
            var divDetalles = document.querySelector(".lista-parcelas");
            var cad = "";

            for (var contador in response) {
                var parcela_id = response[contador].parcela_id;
                var nombre = response[contador].nombre;
                var poligono = response[contador].poligono;
                var parcela = response[contador].parcela;
                var superficie = response[contador].superficie;


                cad += "<div class='Cultivo'>";
                cad += "<p class='Descripcion-Tarjeta'>" + parcela_id + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + nombre + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + poligono + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + parcela + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + superficie + "</p>";
                cad += "<button class='Descripcion-Tarjeta' id='Boton-Editar' onclick='eliminarParcela("+parcela_id+")'>Eliminar</button>";
                cad += "</div>";

            }

            divDetalles.innerHTML = cad; // Establecer el contenido HTML del elemento divPeliculas
        },
        error: function (error) {
            alert(error);
        }
    });
}

function estadisticasCultivos() {
      var propietario = document.getElementById('selectPropietario').value;
      var ejercicio = document.getElementById('ejercicio').value;

    $.ajax({
        url: 'Controller',
        async: false,
        data: {
            propietario: propietario,
            ejercicio: ejercicio,
            ACTION: 'CULTIVOS.LISTAR'
        },
        dataType: 'json',
        success: function (response) {
            var divDetalles = document.querySelector(".mostrarEstadisticas");
            var cad = "";

            for (var contador in response) {
                var cultivo = response[contador].cultivo;
                var hectareas = response[contador].hectareas;
                var porcentaje = response[contador].porcentaje;
                var parcela = response[contador].parcela;
                var superficie = response[contador].superficie;


                cad += "<div class='Cultivo'>";
                cad += "<p class='Descripcion-Tarjeta'>" + cultivo + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + hectareas + "</p>";
                cad += "<p class='Descripcion-Tarjeta'>" + porcentaje + "%</p>";
                cad += "</div>";

            }

            divDetalles.innerHTML = cad; // Establecer el contenido HTML del elemento divPeliculas
        },
        error: function (error) {
            alert(error);
        }
    });
}

function listarEjercicios() {
    $.ajax({
        url: 'Controller',
        async: false,
        data: {
            ACTION: 'EJERCICIOS.LISTAR'
        },
        dataType: 'json',
        success: function (response) {
            var divDetalles = document.querySelector(".listar-ejercicios");
            var cad = "";
            
            cad += "<form>";
            cad += "<label for='ejercicio'>Año:</label>";
            cad += "<select id='ejercicio' name='ejercicio' required>";


            for (var contador in response) {
                var ejercicio = response[contador].ejercicio;
                var ejercicio_id = response[contador].ejercicio_id;
                
                cad += "<option value="+ejercicio+">"+ejercicio+"</option>";
                        
            }
            
            cad += "</select><br>";
            cad += "</form>";    

            divDetalles.innerHTML = cad; // Establecer el contenido HTML del elemento divPeliculas
        },
        error: function (error) {
            alert(error);
        }
    });
}


function añadirAño() {
  var ejercicio = document.getElementById("modificar-ejercicio").value;
  
  $.ajax({
        url: 'Controller',
        async: false,
        data: {
            ejercicio: ejercicio,
            ACTION: 'DETALLES.NUEVO'
        },
        dataType: 'text',
        success: function (response) {
           if (response === 'iguales') {
                alert("Correcto");
            } else {

            }
            
        },
        error: function (error) {
            alert(error);
        }
    });
    location.reload();
}

function eliminarAño() {
  var ejercicio = document.getElementById("modificar-ejercicio").value;
  
  $.ajax({
        url: 'Controller',
        async: false,
        data: {
            ejercicio: ejercicio,
            ACTION: 'DETALLES.ELIMINAR'
        },
        dataType: 'text',
        success: function (response) {
           if (response === 'iguales') {
                alert("Correcto");
            } else {

            }
            
        },
        error: function (error) {
            alert(error);
        }
    });
    location.reload();
}

function añadirParcela() {
  var nombre = document.getElementById("nombre").value;
  var poligono = document.getElementById("poligono").value;
  var parcela = document.getElementById("parcela").value;
  var superficie = document.getElementById("superficie").value;
  
  
  $.ajax({
        url: 'Controller',
        async: false,
        data: {
            nombre: nombre,
            poligono: poligono,
            parcela: parcela,
            superficie: superficie,
            ACTION: 'PARCELAS.NUEVO'
        },
        dataType: 'text',
        success: function (response) {
           if (response === 'iguales') {
                alert("Correcto");
            } else {

            }
            
        },
        error: function (error) {
            alert(error);
        }
    });
    location.reload();
}

function eliminarParcela(parcela_id) {

  $.ajax({
        url: 'Controller',
        async: false,
        data: {
            parcela_id: parcela_id,
            ACTION: 'PARCELAS.ELIMINAR'
        },
        dataType: 'text',
        success: function (response) {
           if (response === 'iguales') {
                alert("Correcto");
            } else {

            }
            
        },
        error: function (error) {
            alert(error);
        }
    });
    location.reload();
}








