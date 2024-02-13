
/* global Swal */
let Swal;
let empleados;
let token = localStorage.getItem("token");
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
                //alert(response.result);
                if (response.id > 0 && response != null) {
                    //Swal.fire("ACCESSO CONCEDIDO!");
                    localStorage.setItem("token", response.token);
                    window.location.href = "http://localhost:8080/Primer_Parcial/html/main.html";
                    //alert(response.token);
                } else if (response.id == 0) {
                    Swal.fire("ACCESSO DENEGADO!");
                } else {
                    Swal.fire(response.error);
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
    //let token = localStorage.getItem("token");
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
                            + empleado.user.nombre + "<br>"
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

                        datos += "<td> <button type='button' class='btn btn-light' onclick='test(" + i + ")'> Modificar</button> </td>";
                        datos += "<td> <button type='button' class='btn btn-danger' onclick='eliminarEmpl(" + empleado.idEmpleado + ")'> Eliminar</button> </td>";
                    }
                    i++;
                });
                document.getElementById("tbEmpleados").innerHTML = datos;

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
    alert("EFIRMA: "+efirma);
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