const URL = "http://localhost:8080/api/articulos"; // Asegúrate de que esta URL sea correcta
let modoEdicion = false;
let idEditar = null;

document.getElementById("formulario").addEventListener("submit", function (e) {
    e.preventDefault(); // Previene el envío del formulario por defecto

    // Obtener los valores de todos los campos del formulario
    const nombre = document.getElementById("nombre").value;
    const categoria = document.getElementById("categoria").value;
    const precio = parseFloat(document.getElementById("precio").value);
    const cantidadEnStock = parseInt(document.getElementById("cantidadEnStock").value);
    const pathFoto = document.getElementById("pathFoto").value;

    // Crear el objeto de datos completo para el artículo
    const datosArticulo = {
        nombre,
        categoria,
        precio,
        cantidadEnStock,
        pathFoto
    };

    let fetchOptions;
    let successMessage;
    let errorMessage;

    if (!modoEdicion) {
        // Modo Crear (POST)
        fetchOptions = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(datosArticulo)
        };
        successMessage = 'Artículo creado exitosamente.';
        errorMessage = 'Error al crear el artículo.';
    } else {
        // Modo Actualizar (PUT)
        fetchOptions = {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(datosArticulo) // Se envía el objeto completo, el ID va en la URL
        };
        successMessage = 'Artículo actualizado exitosamente.';
        errorMessage = 'Error al actualizar el artículo.';
    }

    // Realizar la petición Fetch
    fetch(modoEdicion ? `${URL}/${idEditar}` : URL, fetchOptions)
        .then(response => {
            if (!response.ok) { 
                // Intentar leer el mensaje de error del backend si está en JSON
                return response.json().then(err => {
                    throw new Error(err.message || JSON.stringify(err) || 'Error desconocido.');
                });
            }
            return response.json(); // Si la respuesta es OK, parsear el JSON
        })
        .then(() => {
            listar(); // Recargar la tabla de artículos
            this.reset(); // Limpiar el formulario
            modoEdicion = false; // Salir del modo edición
            idEditar = null; // Resetear ID de edición
            document.getElementById('mensaje-exito').textContent = successMessage;
            document.getElementById('mensaje-error').textContent = '';
        })
        .catch(error => {
            console.error(errorMessage, error);
            document.getElementById('mensaje-error').textContent = `${errorMessage} Detalles: ${error.message}`;
            document.getElementById('mensaje-exito').textContent = '';
        });
});

function listar() {
    fetch(URL)
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById("lista-articulos");
            tbody.innerHTML = "";
            data.forEach(a => {
                const fila = document.createElement("tr");
                fila.innerHTML = `
                    <td>${a.id}</td>
                    <td>${a.nombre}</td>
                    <td>${a.categoria || ''}</td>   <td>$${a.precio ? a.precio.toFixed(2) : 'N/A'}</td>
                    <td>${a.cantidadEnStock !== null ? a.cantidadEnStock : 'N/A'}</td> <td><img src="${a.pathFoto || 'https://via.placeholder.com/50x50?text=No+Image'}" alt="Imagen" width="50"></td> <td>
                    <button class="btn editar" onclick="editar(${a.id}, '${(a.nombre || '').replace(/'/g, "\\'")}', '${(a.categoria || '').replace(/'/g, "\\'")}', ${a.precio}, ${a.cantidadEnStock}, '${(a.pathFoto || '').replace(/'/g, "\\'")}')">Editar</button>
                    <button class="btn eliminar" onclick="eliminar(${a.id})">Eliminar</button>
                    </td>
                `;
                tbody.appendChild(fila);
            });
        })
        .catch(error => {
            console.error("Error al listar artículos:", error);
            document.getElementById('mensaje-error').textContent = `Error al cargar artículos: ${error.message}`;
        });
}

function eliminar(id) {
    if (confirm("¿Deseas eliminar este artículo?")) {
        fetch(`${URL}/${id}`, { method: "DELETE" })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message || JSON.stringify(err) || 'Error desconocido al eliminar.'); });
                }
                listar();
                document.getElementById('mensaje-exito').textContent = 'Artículo eliminado exitosamente.';
                document.getElementById('mensaje-error').textContent = '';
            })
            .catch(error => {
                console.error("Error al eliminar el artículo:", error);
                document.getElementById('mensaje-error').textContent = `Error: ${error.message}`;
                document.getElementById('mensaje-exito').textContent = '';
            });
    }
}

function editar(id, nombre, categoria, precio, cantidadEnStock, pathFoto) {
    document.getElementById("nombre").value = nombre;
    document.getElementById("categoria").value = categoria;
    document.getElementById("precio").value = precio;
    document.getElementById("cantidadEnStock").value = cantidadEnStock;
    document.getElementById("pathFoto").value = pathFoto;

    modoEdicion = true;
    idEditar = id;
    document.getElementById('mensaje-exito').textContent = '';
    document.getElementById('mensaje-error').textContent = '';
}

listar(); // Llamar a listar al cargar la página para mostrar los artículos existentes