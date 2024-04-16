
/* global Swal */
let Swal;
let empleados;
let cliente;
let clientes;
let productos;
let productosVenta = [];

const search = document.querySelector('.search');
const btn = document.querySelector('.btn');
const input = document.querySelector('.input');

//btn.addEventListener('click', () => {
//    search.classList.toggle('active');
//    input.focus();
//});

let token = localStorage.getItem("token");
let empleado = localStorage.getItem("empleado");
function ingresar() {
    let user = document.getElementById("txtUser").value;
    let password = document.getElementById('txtPass').value;

    user = normalizar(user);
    user = sanitizar(user);
    password = normalizar(password);
    password = sanitizar(password);

//    alert(user);
//    alert(password);

    let us = {usuario: user, passw: password};
    let param = {user: JSON.stringify(us)};
    fetch("http://localhost:8080/Primer_Parcial/api/acceso/login",
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                //alert(response.idEmpleado);
                if (response.idEmpleado > 0 && response != null) {
                    //Swal.fire("ACCESSO CONCEDIDO!");
                    //alert(response.token);
                    localStorage.setItem("token", response.user.token);
                    localStorage.setItem("empleado", response.idEmpleado);
                    //alert("empleado"+localStorage.getItem("empleado"));
                    window.location.href = "http://localhost:8080/Primer_Parcial/html/main.html";
                    //alert(response.token);
                } else if (response.idEmpleado == 0) {
                    alert("ACCESSO DENEGADO!");
                } else {
                    alert(response.error);
                }
            });
}

function normalizar(text) {
    for (let i = 0; i < text.length; i++) {
        text = text.replace("á", "a");
        text = text.replace("é", "e");
        text = text.replace("ó", "o");
        text = text.replace("í", "i");
        text = text.replace("ú", "u");
        text = text.replace("ü", "u");
        text = text.replace("ñ", "n");
        text = text.replace("É", "E");
        text = text.replace("Á", "A");
        text = text.replace("Í", "I");
        text = text.replace("Ó", "O");
        text = text.replace("Ú", "U");
        text = text.replace("Ñ", "N");
    }
    return text;
}
function sanitizar(text) {
    for (let i = 0; i < text.length; i++) {
        text = text.replace(")", "");
        text = text.replace("(", "");
        text = text.replace(",", "");
        text = text.replace(";", "");
        text = text.replace("|", "");
        text = text.replace("&", "");
        text = text.replace("*", "");
        text = text.replace("\"", "");
    }
    return text;
}


function logout() {
    let param = {t: localStorage.getItem("token")};
    let ruta = "http://localhost:8080/Primer_Parcial/api/acceso/logout";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                if (response.result) {
                    window.location.href = "http://localhost:8080/Primer_Parcial/";
                    localStorage.removeItem("token");
                } else if (response.error) {
                    Swal.fire(response.error);
                }
            });
}

function getAll() {
    let token = localStorage.getItem("token");
    let param = {t: token};
    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/getAll";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                console.log(response.result);


                console.log(response);

                empleados = response;
                let datos = "";
                let i = 0;
                empleados.forEach((empleado) => {
                    let dato1 = empleado.idEmpleado + "<br>"
                            + empleado.codigo + "<br>"
                            + empleado.fechaIngreso + "<br>"
                            + empleado.puesto + "<br>"
                            + empleado.salarioBruto + "<br>";
                    //+ empleado.activo+"<br>";
                    let dato2 = empleado.persona.nombre + "<br>"
                            + empleado.persona.apPat + "<br>"
                            + empleado.persona.apMat + "<br>";
                    let dato3 = empleado.persona.genero + "<br>"
                            + empleado.persona.fechaNac + "<br>"
                            + empleado.persona.rfc + "<br>"
                            + empleado.persona.curp + "<br>";
                    let dato4 = empleado.persona.domicilio + "<br>"
                            + empleado.persona.cpPersona + "<br>"
                            + empleado.persona.ciudad + "<br>"
                            + empleado.persona.estado + "<br>"
                            + empleado.persona.telefono + "<br>";
                    let dato5 = empleado.user.id + "<br>"
                            + empleado.user.usuario + "<br>"
                    let dato6 = empleado.sucursal.nombre + "<br>"
                            + empleado.sucursal.domicilio + "<br>"
                            + empleado.sucursal.colonia + "<br>"
                            + empleado.sucursal.codigoPostal + "<br>"
                            + empleado.sucursal.ciudad + "<br>"
                            + empleado.sucursal.estado + "<br>"
                            + empleado.sucursal.telefono + "<br>";
                    datos += "<tr>";
                    datos += "<td>" + dato1 + "</td>";
                    datos += "<td>" + dato2 + "</td>";
                    datos += "<td>" + dato3 + "</td>";
                    datos += "<td>" + dato4 + "</td>";
                    datos += "<td>" + dato5 + "</td>";
                    datos += "<td>" + dato6 + "</td>";
                    if (empleado.activo === 0) {
                        datos += "<td> <button type='button' class='btn btn-light' onclick='test(" + empleado.persona.nombre + ")'> Modificar</button> </td>";
                        datos += "<td> <button type='button' class='btn btn-success' onclick='activarEmpl(" + empleado.idEmpleado + ")'> Activar</button> </td>";

                    } else if (empleado.activo === 1) {

                        datos += "<td> <button type='button' class='buton' onclick='test(" + i + ")'> Modificar</button> </td>";
                        datos += "<td> <button type='button' class='buton' onclick='eliminarEmpl(" + empleado.idEmpleado + ")'> Eliminar</button> </td>";
                    }
                    i++;
                });
                document.getElementById("tbEmpleados").innerHTML = datos;

            });

}
function getAllV() {
    let token = localStorage.getItem("token");
    let param = {t: token};
    let ruta = "http://localhost:8080/Primer_Parcial/api/venta/getAll";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                //console.log(response.result);


                console.log(response);

                let detalle = response;
                let datos = "";
                let i = 0;
                detalle.forEach((detalle) => {
                    let dato1 = detalle.producto.nombre + "<br>"
                            + detalle.producto.nombreGenerico + "<br>"
                            + detalle.producto.formaFarmaceutica + "<br>"
                            + detalle.producto.unidadMedida + "<br>"
                            + detalle.producto.presentacion + "<br>"
                            + detalle.producto.precioVenta + "<br>";
                    //+ empleado.activo+"<br>";
                    let dato2 = detalle.venta.fechaHora + "<br>";
                    let dato3 = detalle.venta.cliente.email + "<br>"
                            + detalle.venta.cliente.persona.nombre + "<br>"
                            + detalle.venta.cliente.persona.apPat + "<br>"
                            + detalle.venta.cliente.persona.apMat + "<br>"
                            + detalle.venta.cliente.persona.telefono + "<br>"
                            + detalle.venta.cliente.persona.estado + "<br>";

                    let dato4 = detalle.venta.empleado.email + "<br>"
                            + detalle.venta.empleado.puesto + "<br>"
                            + detalle.venta.empleado.persona.nombre + "<br>"
                            + detalle.venta.empleado.persona.apPat + "<br>"
                            + detalle.venta.empleado.persona.apMat + "<br>"
                            + detalle.venta.empleado.persona.telefono + "<br>";
                    datos += "<tr>";
                    datos += "<td>" + dato1 + "</td>";
                    datos += "<td>" + dato2 + "</td>";
                    datos += "<td>" + dato3 + "</td>";
                    datos += "<td>" + dato4 + "</td>";
                    datos += "<td>" + detalle.cantidad + "</td>";
                    datos += "<td>" + detalle.precioVenta + "</td>";

                    i++;
                });
                document.getElementById("tablaVenta").innerHTML = datos;

            });

}

async function cargaGetAll() {
    try {
        const response = await fetch("getAll.html");
        const html = await response.text();

        // Insertar HTML en el elemento con ID 'contenido'
        document.getElementById('contenido').innerHTML = html;

        // Aplicar estilos mediante JavaScript
        const style = document.createElement('style');
        style.innerHTML = `
            
            .tab {
                border: solid 3px #371172;
            }
            .table{
               
                color: #371172;
                font-size:18px;
                table-layout: fixed;
                border-collapse:collapse;
                
            }
            thead{
                background: rgba(0.145, 0.043, 0.282, 0.4);
            }
            th{
            padding:20px 15px;
            font-weight:700;
            text-transform: uppercase;
            }
        td{
        padding: 15px;
        border-bottom: solid 1px black;
        }
        `;
        document.head.appendChild(style);

    } catch (error) {
        console.error('Error al cargar el archivo HTML:', error);
    }
}


async function cargaGet() {
    const response = await fetch("getAll.html");
    const html = await response.text();

    // Insertar HTML en el elemento con ID 'contenido'
    document.getElementById('contenido').innerHTML = html;
    getAll();
}
async function cargaVenta() {
    const response = await fetch("venta.html");
    const html = await response.text();
    document.getElementById('contenido').innerHTML = html;
}
async function cargaCatalogoVenta() {
    const response = await fetch("ventaCatalogo.html");
    const html = await response.text();
    document.getElementById('contenido').innerHTML = html;
    getAllV();
}
async function cargaCreate() {
    const response = await fetch("create.html");
    const html = await response.text();

    // Insertar HTML en el elemento con ID 'contenido'
    document.getElementById('contenido').innerHTML = html;

}


function activarEmpl(id) {
    //let id = empleado.idEmpleado;
    let token = localStorage.getItem("token");
    let param = {idE: id, token: token};
    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/activar";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                if (response.result === "success") {

                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Activando',
                        footer: 'Ya ha sido activao'
                    });
                } else {
                    //alert("No se desactivo");
                }
                getAll();
            });
}
function eliminarEmpl(idE) {
    //let id = empleado.idEmpleado;
    let token = localStorage.getItem("token");
    //alert(idE);
    let param = {idE: idE, token: token};
    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/delete";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                if (response.result === "success") {

                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'No se desactivado',
                        footer: '...assign()'
                    });
                } else {
//                    Swal.fire({
//                       
//                        text: "Empleado eliminado"
//                       
//                    });

                }
                getAll();
            });
}


function insertarEmp() {

    let nombre = document.getElementById("txtEmpleadoNombre").value;
    let apPat = document.getElementById("txtEmpleadoApellidoPaterno").value;
    let apMat = document.getElementById("txtEmpleadoApellidoMaterno").value;
    let genero = document.querySelector('input[name="radioEmpleadoGenero"]:checked');
    if (genero) {
        genero = genero.value;
    } else {
        genero = "";
    }
    let fechNac = document.getElementById("txtEmpleadoFechaDeNacimiento").value;
    let rfc = document.getElementById("txtEmpleadoRfc").value;
    let curp = document.getElementById("txtEmpleadoCurp").value;
    let domicilio = document.getElementById("txtEmpleadoDomicilio").value;
    let codigoPostal = document.getElementById("txtEmpleadoCodigoPostal").value;
    let ciudad = document.getElementById("txtEmpleadoCiudad").value;
    let estado = document.getElementById("txtEmpleadoEstado").value;
    let telefono = document.getElementById("txtEmpleadoTelefono").value;
    let foto = document.getElementById("txtFoto").value;
    let usuar = document.getElementById("txtUsuario").value;
    let passw = document.getElementById("txtPassw").value;
    let rol = document.querySelector('input[name="radioRol"]:checked');
    if (rol) {
        rol = rol.value;
    } else {
        rol = "";
    }
    let email = document.getElementById("txtEmpleadoCorreo").value;
    let fechaIng = document.getElementById("txtFechaIng").value;
    let puesto = document.getElementById("txtEmpleadoPuesto").value;
    let salario = document.getElementById("txtEmpleadoSalario").value;
    let codigo = document.getElementById("txtCodigo").value;

    nombre = normalizar(nombre);
    nombre = sanitizar(nombre);
    apPat = normalizar(apPat);
    apPat = sanitizar(apPat);
    apMat = normalizar(apMat);
    apMat = sanitizar(apMat);
//    fechNac = normalizar(fechNac);
//    fechNac = sanitizar(fechNac);
    rfc = normalizar(rfc);
    rfc = sanitizar(rfc);
    curp = normalizar(curp);
    curp = sanitizar(curp);
    domicilio = normalizar(domicilio);
    domicilio = sanitizar(domicilio);
    codigoPostal = normalizar(codigoPostal);
    codigoPostal = sanitizar(codigoPostal);
    ciudad = normalizar(ciudad);
    ciudad = sanitizar(ciudad);
    estado = normalizar(estado);
    estado = sanitizar(estado);
    telefono = normalizar(telefono);
    telefono = sanitizar(telefono);
    foto = normalizar(foto);
    foto = sanitizar(foto);
    usuar = normalizar(usuar);
    usuar = sanitizar(usuar);
    passw = normalizar(passw);
    passw = sanitizar(passw);
    email = normalizar(email);
    email = sanitizar(email);
//    fechaIng = normalizar(fechaIng);
//    fechaIng = sanitizar(fechaIng);
    puesto = normalizar(puesto);
    puesto = sanitizar(puesto);
    salario = normalizar(salario);
    salario = sanitizar(salario);
    codigo = normalizar(codigo);
    codigo = sanitizar(codigo);
    let persona = {
        nombre: nombre,
        apPat: apPat,
        apMat: apMat,
        genero: genero,
        fechaNac: fechNac, //cambiar al formato sql
        rfc: rfc,
        curp: curp,
        domicilio: domicilio,
        cpPersona: codigoPostal,
        ciudad: ciudad,
        estado: estado,
        telefono: telefono,
        foto: foto
    };
    let user = {

        usuario: usuar,
        passw: passw,
        rol: rol
    };
    let empleado = {
        email: email,
        codigo: codigo,
        fechaIngreso: fechaIng,
        puesto: puesto, //Muajaja
        salarioBruto: salario,
        activo: 1,
        persona: persona,
        user: user,
        sucursal: {idSucursal: 1}
    };
    //console.log(JSON.stringify(empleado));
//     "email": "email",
//  "codigo": "codigo",
//  "fechaIngreso": "2024/10/10",
//  "puesto": "BOSS",
//  "salarioBruto": 10,
//  "activo": 1
    validarCampos();
    let params = {e: JSON.stringify(empleado), token: token};

    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/insert";

    fetch(ruta,
            {
                method: "POST",
                headers: {'Content-Type':
                            'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(params)
            })
            .then(response => response.json())
            .then(response => {
                if (response.result) {
                    alert("inserción correcta", response.result, "success");
                    cargaGet();
                }
                //borrarFormulario();
                if (response.error) {
                    alert("Problemas al insertar ", response.error, "error");
                    validarRFCyCURP();
                    validarFormatoFecha();
                    validarCodigoPostal();
                }

            });
}


async function cargaUpdate() {
    const response = await fetch("update.html");
    const html = await response.text();

    // Insertar HTML en el elemento con ID 'contenido'
    document.getElementById('contenido').innerHTML = html;

}

function modificarEmp() {

    let idE = document.getElementById("txtIdEmp").value;
    let nombre = document.getElementById("txtEmpleadoNombre").value;
    let apPat = document.getElementById("txtEmpleadoApellidoPaterno").value;
    let apMat = document.getElementById("txtEmpleadoApellidoMaterno").value;
    let genero = document.querySelector('input[name="radioEmpleadoGenero"]:checked');
    if (genero) {
        genero = genero.value;
    } else {
        genero = "";
    }
    let fechNac = document.getElementById("txtEmpleadoFechaDeNacimiento").value;
    let rfc = document.getElementById("txtEmpleadoRfc").value;
    let curp = document.getElementById("txtEmpleadoCurp").value;
    let domicilio = document.getElementById("txtEmpleadoDomicilio").value;
    let codigoPostal = document.getElementById("txtEmpleadoCodigoPostal").value;
    let ciudad = document.getElementById("txtEmpleadoCiudad").value;
    let estado = document.getElementById("txtEmpleadoEstado").value;
    let telefono = document.getElementById("txtEmpleadoTelefono").value;
    let foto = document.getElementById("txtFoto").value;
    let usuar = document.getElementById("txtUsuario").value;
    let passw = document.getElementById("txtPassw").value;
    let rol = document.querySelector('input[name="radioRol"]:checked');
    if (rol) {
        rol = rol.value;
    } else {
        rol = "";
    }
    let email = document.getElementById("txtEmpleadoCorreo").value;
    let fechaIng = document.getElementById("txtFechaIng").value;
    let puesto = document.getElementById("txtEmpleadoPuesto").value;
    let salario = document.getElementById("txtEmpleadoSalario").value;
    let codigo = document.getElementById("txtCodigo").value;

    nombre = normalizar(nombre);
    nombre = sanitizar(nombre);
    apPat = normalizar(apPat);
    apPat = sanitizar(apPat);
    apMat = normalizar(apMat);
    apMat = sanitizar(apMat);
//    fechNac = normalizar(fechNac);
//    fechNac = sanitizar(fechNac);
    rfc = normalizar(rfc);
    rfc = sanitizar(rfc);
    curp = normalizar(curp);
    curp = sanitizar(curp);
    domicilio = normalizar(domicilio);
    domicilio = sanitizar(domicilio);
    codigoPostal = normalizar(codigoPostal);
    codigoPostal = sanitizar(codigoPostal);
    ciudad = normalizar(ciudad);
    ciudad = sanitizar(ciudad);
    estado = normalizar(estado);
    estado = sanitizar(estado);
    telefono = normalizar(telefono);
    telefono = sanitizar(telefono);
    foto = normalizar(foto);
    foto = sanitizar(foto);
    usuar = normalizar(usuar);
    usuar = sanitizar(usuar);
    passw = normalizar(passw);
    passw = sanitizar(passw);
    email = normalizar(email);
    email = sanitizar(email);
//    fechaIng = normalizar(fechaIng);
//    fechaIng = sanitizar(fechaIng);
    puesto = normalizar(puesto);
    puesto = sanitizar(puesto);
    salario = normalizar(salario);
    salario = sanitizar(salario);
    codigo = normalizar(codigo);
    codigo = sanitizar(codigo);
    let persona = {
        nombre: nombre,
        apPat: apPat,
        apMat: apMat,
        genero: genero,
        fechaNac: fechNac, //cambiar al formato sql
        rfc: rfc,
        curp: curp,
        domicilio: domicilio,
        cpPersona: codigoPostal,
        ciudad: ciudad,
        estado: estado,
        telefono: telefono,
        foto: foto
    };
    let user = {

        usuario: usuar,
        passw: passw,
        rol: rol
    };
    let empleado = {
        email: email,
        codigo: codigo,
        fechaIngreso: fechaIng,
        puesto: puesto, //Muajaja
        salarioBruto: salario,
        activo: 1,
        persona: persona,
        user: user,
        sucursal: {idSucursal: 1}
    };
    //console.log(JSON.stringify(empleado));
//     "email": "email",
//  "codigo": "codigo",
//  "fechaIngreso": "2024/10/10",
//  "puesto": "BOSS",
//  "salarioBruto": 10,
//  "activo": 1
    let params = {e: JSON.stringify(empleado), idE: idE, token: token};

    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/modificar";

    fetch(ruta,
            {
                method: "POST",
                headers: {'Content-Type':
                            'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(params)
            })
            .then(response => response.json())
            .then(response => {
                if (response.result) {
                    alert("modificacion correcta", response.result, "success");
                    cargaGet();
                }
                //borrarFormulario();
                if (response.error) {
                    alert("Problemas al modificar ", response.error, "error");
                    validarRFCyCURP();
                }
            });
}

function validarCampos() {
    let campos = [
        "txtEmpleadoNombre",
        "txtEmpleadoApellidoPaterno",
        "txtEmpleadoApellidoMaterno",
        "txtEmpleadoFechaDeNacimiento",
        "txtEmpleadoRfc",
        "txtEmpleadoCurp",
        "txtEmpleadoDomicilio",
        "txtEmpleadoCodigoPostal",
        "txtEmpleadoCiudad",
        "txtEmpleadoEstado",
        "txtEmpleadoTelefono",
        "txtFoto",
        "txtUsuario",
        "txtPassw",
        "txtEmpleadoCorreo",
        "txtFechaIng",
        "txtEmpleadoPuesto",
        "txtEmpleadoSalario",
        "txtCodigo"
    ];

    for (let i = 0; i < campos.length; i++) {
        let valorCampo = document.getElementById(campos[i]).value;
        if (valorCampo.trim() === "") {
            alert("Por favor, completa todos los campos.");
            return false;
        }
    }

    let genero = document.querySelector('input[name="radioEmpleadoGenero"]:checked');
    let rol = document.querySelector('input[name="radioRol"]:checked');

    if (!genero) {
        alert("Por favor, selecciona un género.");
        return false;
    }

    if (!rol) {
        alert("Por favor, selecciona un rol.");
        return false;
    }

    // Puedes agregar más validaciones según tus necesidades

    return true;
}

function validarRFCyCURP() {
    // Obtén el valor de los campos RFC y CURP
    let rfc = document.getElementById("txtEmpleadoRfc").value;
    let curp = document.getElementById("txtEmpleadoCurp").value;
    //alert(rfc);
    // Verifica la longitud del RFC
    if (rfc.length > 15) {
        // Muestra una alerta si el RFC supera los 15 caracteres
        alert("El RFC no puede tener más de 15 caracteres.");
        return false; // Puedes ajustar esto según tus necesidades
    }

    // Verifica la longitud de la CURP
    if (curp.length > 18) {
        // Muestra una alerta si la CURP supera los 18 caracteres
        alert("La CURP no puede tener más de 18 caracteres.");
        return false; // Puedes ajustar esto según tus necesidades
    }

    return true; // Continuar con la lógica de tu aplicación si las longitudes son válidas
}

function validarFormatoFecha() {
    // Obtén el valor del campo de fecha
    let fecha = document.getElementById("txtEmpleadoFechaDeNacimiento").value;

    // Verifica el formato de la fecha (año mes día)
    let regexFecha = /^\d{4}-\d{2}-\d{2}$/;

    if (!regexFecha.test(fecha)) {
        // Muestra una alerta si el formato de la fecha no es válido
        alert("Formato de fecha inválido. Utiliza el formato YYYY-MM-DD.");
        return false; // Puedes ajustar esto según tus necesidades
    }

    return true; // Continuar con la lógica de tu aplicación si el formato es válido
}


function validarCodigoPostal() {
    // Obtén el valor del campo de código postal
    let codigoPostal = document.getElementById("txtEmpleadoCodigoPostal").value;

    // Verifica el tamaño del código postal
    if (codigoPostal.length !== 5) {
        // Muestra una alerta si el tamaño del código postal no es válido
        alert("El código postal debe tener exactamente 5 caracteres.");
        return false; // Puedes ajustar esto según tus necesidades
    }

    return true; // Continuar con la lógica de tu aplicación si el tamaño es válido
}


async function cargaEfirma() {
    const response = await fetch("efirma.html");
    const html = await response.text();

    // Insertar HTML en el elemento con ID 'contenido'
    document.getElementById('contenido').innerHTML = html;

}

function crearEfirma() {
    let nombre = document.getElementById("txtName").value;
    let apPat = document.getElementById("txtApPat").value;
    let apMat = document.getElementById("txtApMat").value;
    let campos = [
        "txtName",
        "txtApPat",
        "txtApMat"
    ];
    let f = new Date();
    let fecha = f.getDate() + "/" + (f.getMonth() + 1) + "/" + f.getFullYear();
    let hora = f.getHours() + ":" + f.getMinutes();
    //alert("Fecha:"+fecha+" y hora:"+hora+" de la creacion.");
    let efirma = nombre + apPat + apMat + fecha + hora;
    //alert(efirma);
    for (let i = 0; i < campos.length; i++) {
        let valorCampo = document.getElementById(campos[i]).value;
        if (valorCampo.trim() === "") {
            alert("Por favor, completa todos los campos.");
            return false;
        }
    }
    alert("Fecha:" + fecha + " y hora:" + hora + " de la creacion.");
    alert("EFIRMA: " + efirma);
    let param = {cadena: efirma};
    let ruta = "http://localhost:8080/Primer_Parcial/api/empleado/efirma";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                if (response.success === true) {
                    alert("Creada");
                } else {
                    alert("No se creada");
                }
            });
}


function getAllCliente() {
    //let parametros = {"telefono": telefono, 
    //  "token":localStorage.getItem("token")};
    let ruta = "http://localhost:8080/Primer_Parcial/api/cliente/getAllClie";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams()
            }
    ).then(response => response.json())
            .then(response => {
//                if (response.success === true) {
//                    alert("Encontrado");
                cargaResultadoBC(response);
//                } else {
//                     alert("No se encuentra");
//                }
            });

}
function buscarClienteV() {
    let telefono = document.getElementById("txtClienteV").value;
    //alert("Dato a buscar: "+telefono);
    let parametros = {"telefono": telefono,
        "token": localStorage.getItem("token")};
    let ruta = "http://localhost:8080/Primer_Parcial/api/cliente/buscar";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(parametros)
            }
    ).then(response => response.json())
            .then(response => {
//                if (response.success === true) {
//                    alert("Encontrado");
                cargaResultadoBC(response);
//                } else {
//                     alert("No se encuentra");
//                }
            });

}

function cargaResultadoBC(response) {
    let datosTabla = "";
    if (response.error != null) {
        datosTabla += "ERROR";
        alert("error");
    } else if (response.length == 0) {
        datosTabla += "Cliente inexistente";
        alert("Cliente inexistente");
    } else if (response.length > 0) {
        for (let i = 0; i < response.length; i++) {
            clientes = response;
            //alert("c"+cliente[i].persona.nombre);
            let nombre = response[i].persona.nombre + " " + response[i].persona.apPat + "" + response[i].persona.apMat;
            let direccion = response[i].persona.domicilio + "<br>" + response[i].persona.cpPersona;
            datosTabla += "<tr>";
            datosTabla += "<td>" + nombre + "</td>";
            datosTabla += "<td>" + direccion + "</td>";
            datosTabla += "<td>" + response[i].persona.telefono + "</td>";
            datosTabla += "<td>" + response[i].email + "</td>";
            //alert("a"+response[i]);
            datosTabla += "<td><button type='button' class='buton' onclick='addCliente(" + i + ");'>Add</button></td>";
            datosTabla += "</tr>";
        }
        document.getElementById("tbBusquedaClienteV").innerHTML = datosTabla;
    }

}


function addCliente(i) {

    cliente = clientes[i];

    let nombreCliente = cliente.persona.nombre + " " + cliente.persona.apPat + " " + cliente.persona.apMat;
    document.getElementById("nameClienteV").value = nombreCliente;
}





function getAllP() {
    let ruta = "http://localhost:8080/Primer_Parcial/api/producto/getAllPr";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams()
            }
    ).then(response => response.json())
            .then(response => {
                cargaResultadoBCP(response);
            });
}
function buscarProductoV() {
    let idP = document.getElementById("txtProductoV").value;
    //alert("Dato a buscar: "+telefono);
    let parametros = {"idP": idP,
        "token": localStorage.getItem("token")};
    let ruta = "http://localhost:8080/Primer_Parcial/api/producto/buscar";
    fetch(ruta,
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(parametros)
            }
    ).then(response => response.json())
            .then(response => {
                cargaResultadoBCP(response);
            });
}


function cargaResultadoBCP(response) {
    let datosTabla = "";
    if (response.error != null) {
        datosTabla += "ERROR";
        alert("error");
    } else if (response.length == 0) {
        datosTabla += "Producto inexistente";
        alert("Producto inexistente");
    } else if (response.length > 0) {
        for (let i = 0; i < response.length; i++) {
            productos = response;
            datosTabla += "<tr>";
            datosTabla += "<td>" + response[i].nombre + "</td>";
            datosTabla += "<td>" + response[i].precioVenta + "</td>";
            datosTabla += "<td>" + response[i].formaFarmaceutica + "</td>";
            datosTabla += "<td>" + response[i].presentacion + "</td>";
            datosTabla += "<td>" + response[i].concentracion + "</td>";
            datosTabla += "<td><button type='button' class='buton' onclick='seleccionarPV(" + i + ");'>Add</button></td>";
            datosTabla += "</tr>";
        }
        document.getElementById("tbBusquedaProdutoV").innerHTML = datosTabla;
    }

}
function addProducto(i) {
    console.log("obj" + i);
    let datosTabla = "";
    //alert("agua");
    productosVenta = productos[i];

    datosTabla += "<tr>";
    datosTabla += "<td>" + productosVenta.idProducto + "</td>";
    datosTabla += "<td>" + productosVenta.nombre + "</td>";
    datosTabla += "<td>" + productosVenta.precioVenta + "</td><br>";
    datosTabla += "<td>" + "cantidad" + "</td><br>";
    datosTabla += "<td>" + "importe" + "</td><br>";
    datosTabla += "<td><button type='button' class='buton' onclick='seleccionarPV(" + i + ");'>Eliminar</button></td>";
    datosTabla += "</tr>";
    document.getElementById("tblProductosV").innerHTML = datosTabla;

}

function seleccionarPV(i) {
    let producto = productos[i];
    productosVenta.push(producto);
    let tblBody = document.getElementById("tblProductosV");
    let hilera = document.createElement("tr");
    let celda1 = document.createElement("td");
    let textoCelda1 = document.createTextNode(producto.idProducto);
    let celda2 = document.createElement("td");
    let textoCelda2 = document.createTextNode(producto.nombre);
    let celda3 = document.createElement("td");
    let textoCelda3 = document.createTextNode(producto.precioVenta);
    let celda4 = document.createElement("td");
    let textoCelda4 = document.createTextNode("1");
    let celda5 = document.createElement("td");
    let textoCelda5 = document.createTextNode("nulo");
    //let celda6 = document.createElement("td");
    let botonEliminar = document.createElement("button");
    botonEliminar.className = "buton";
    let textoCelda6 = document.createTextNode("Eliminar");


    celda1.appendChild(textoCelda1);
    celda2.appendChild(textoCelda2);
    celda3.appendChild(textoCelda3);
    celda4.appendChild(textoCelda4);
    celda5.appendChild(textoCelda5);
    botonEliminar.appendChild(textoCelda6);
    hilera.appendChild(celda1);
    hilera.appendChild(celda2);
    hilera.appendChild(celda3);
    hilera.appendChild(celda4);
    hilera.appendChild(celda5);
    hilera.appendChild(botonEliminar);
    tblBody.appendChild(hilera);
    //alert("a");
    calcularTotal(i);

}


function calcularTotal() {
    let total = 0;
    for (let i = 0; i < productosVenta.length; i++) {
        total += productosVenta[i].precioVenta;
    }
    document.getElementById("txtTotal").value = total;
    //alert("total: " + total);
}




function cancelarVenta() {
    document.getElementById("txtClienteV").value = "";
    document.getElementById("txtProductoV").value = "";
    document.getElementById("nameClienteV").value = "";
    document.getElementById("tbBusquedaClienteV").innerHTML = "";
    document.getElementById("tbBusquedaProdutoV").innerHTML = "";
    document.getElementById("tblProductosV").innerHTML = "";


    cliente = null;
    clientes = null;
    productos = null;
    productosVenta = null;
    productosVenta = [];
}

function generarVenta() {
    let listaDV = [];
    for (let i = 0; i < productosVenta.length; i++) {
        let detalleVenta = {
            producto: productosVenta[i],
            estatus: "1",
            cantidad: 1,
            precioVenta: productosVenta[i].precioVenta
        };
        listaDV.push(detalleVenta);
    }
    let venta = {
        "fechaHora": "2024-03-06 7:24:04",
        estatus: 1,
        cliente: {
            "idCliente": cliente.idCliente
        },
        empleado: {
            "idEmpleado": localStorage.getItem("empleado"),
            sucursal: {
                idSucursal: "1"
            }
        },
        listaDV: listaDV
    };
    alert("cantidad: " + listaDV.length);

    //JSON.stringify(venta);
    //alert("cliete: " + venta.cliente.persona.nombre);
    //let param = {venta: JSON.stringify(venta)};
    let param = {venta: JSON.stringify(venta)};
    fetch("http://localhost:8080/Primer_Parcial/api/venta/create",
            {method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: new URLSearchParams(param)
            }
    ).then(response => response.json())
            .then(response => {
                if (response.succes) {
                    alert("mal");
                } else {
                    alert("venta generada");
                }
            });
}