document.getElementById("nombre").addEventListener("blur", comprobarNombre());
document.getElementById("marca").addEventListener("blur", comprobarMarca());
document.getElementById("tipo").addEventListener("blur", comprobarTipo());
document.getElementById("precio").addEventListener("blur", comprobarPrecio());
document.getElementById("descripcion").addEventListener("blur", comprobarDescripcion());

function validarFormulario() {
	let resultado = false;

	resultado = comprobarNombre() &&
				comprobarMarca() &&
				comprobarTipo() &&
				comprobarPrecio() &&
				comprobarDescripcion();
		formulario.enviar.className = resultado ? "btn btn-success mb-1" : "btn btn-danger mb-1";

	return resultado;
}

function comprobarNombre() {
	let nombre = form.nombre;
	let resultado = nombre.value !== "";

	cambiarApariencia(nombre, resultado);
	return resultado;
}


function comprobarMarca() {
	let marca = form.marca;
	let resultado = marca.value !== "";

	cambiarApariencia(marca, resultado);
	return resultado;
}

function comprobarTipo() {
	let tipo = form.tipo;
	let resultado = tipo.value !== "";

	cambiarApariencia(tipo, resultado);
	return resultado;
}

function comprobarPrecio() {
	let precio = form.precio;
	let resultado = precio.value !== "";

	cambiarApariencia(precio, resultado);
	return resultado;
}

function comprobarDescripcion() {
	let descripcion = form.descripcion;
	let resultado = descripcion.value !== "";

	cambiarApariencia(descripcion, resultado);
	return resultado;
}

function cambiarApariencia(campo, estado) {
	if (estado) {
		campo.classList.remove("border-danger");
		campo.classList.add("border-success");
	}
	else {
		campo.classList.remove("border-success");
		campo.classList.add("border-danger");
	}

}