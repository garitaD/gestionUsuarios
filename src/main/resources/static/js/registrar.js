
$(document).ready(function() {
    //On ready
});

async function registrarUsuario() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let confirmarPassword = document.getElementById('txtConfirmarPassword').value;

    if (confirmarPassword != datos.password){
        alert('Las contraseñas no coinciden');
        return; // con este return se corta la funcion y no hace el request
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos) //
    });
    alert("La cuenta fue creada con exito");
    window.location.href='login.html';

}

