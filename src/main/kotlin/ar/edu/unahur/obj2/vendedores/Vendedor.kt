package ar.edu.unahur.obj2.vendedores

// Primero tenemos la clase certificacion va a tener 2 variables de solo lectura, una vez inicializado y ejecutado, no puede cambiarse.
// Segundo el tipo de esa variables como lo dice el codigo, una es booleana y la otra valor numerico entero.
// Tercero no tiene metodos o algun "comportamiento" asi que no va hacer nada esta clase, por ahora.
class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

// Clase abstracta de esta no se van a crear objetos, su funcion es de SuperClase.
abstract class Vendedor {

  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos y eliminar.
  // Esta lista va tener objetos de Certificacion.
  val certificaciones = mutableListOf<Certificacion>()

  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve un boleano.
  // Recibe por parametro un objeto Ciudad.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean

  // En las funciones declaradas con = no es necesario explicitar el tipo.
  // El tipo de dato que devuelve es Booleano, atraves  de una condicion.
  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
  // Aunque no devuelva nada, hace algo, agrega certificaciones a una lista.
  // el tipo de dato de Certificaciones es un objeto.
  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  // Devuelve un Booleano.
  fun esFirme() = this.puntajeCertificaciones() >= 30

  // Metodos que devuelve tipo de datos entero, 2 te devuelven la cantidad y el otro la sumatotal.
  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }

  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }

  fun puntajeCertificaciones() = certificaciones.sumBy { it.puntaje }

  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve un boleano.
  abstract fun influyente() : Boolean

}

// En los parámetros, es obligatorio poner el tipo
// Los tipos que recibe por parametro, va hacer un Objeto.
// Esta clase hereda de la SuperClase.
class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {

  // Definimos el metodod override, lo que hacemos es agregar un comportamiento o instruccion al metodo abstracto que heredamos.
  // El tipo de dato que recibe por parametro tambien es un objeto.
  // Este metodo devuelve un Booleano.
  override fun puedeTrabajarEn(ciudad: Ciudad) = ciudad == ciudadOrigen

  // Metodo override, lo configuramos para que devuelva un tipo de dato.
  //Ninguno es influyente, devuelve un tipo de dato booleano false siempre.
  override fun influyente() = false

}

// A este tipo de List no se le pueden agregar o quitar elementos una vez definida.
// La lista tiene objetos de tipo Provincia.
// Tambien hereda de la superClase.
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {

  // Recibe por parametro un objeto de tipo Ciudad.
  // Este metodo devuelve un tipo de dato Booleano.
  // Metodo override, lo configuramos para que devuelva un tipo de dato.
  override fun puedeTrabajarEn(ciudad: Ciudad) = provinciasHabilitadas.contains(ciudad.provincia)

  // Devuelve un tipo de dato booleano.
  // Metodo override, lo configuramos para que devuelva un tipo de dato.
  override fun influyente() = provinciasHabilitadas.sumBy { it.poblacion  } >= 10000000
}

// A este tipo de lista una vez instanciada no se le puede agregar o quitar elementos.
// El tipo de dato que tiene ese lista son objeto de tipo Ciudad.
// Otro mas que hereda de la superClase.
class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {

  // Metodo override, lo configuramos para que devuelva un tipo de dato.
  // Recibe por parametro un objeto de tipo Ciudad.
  // Este metodo devuelve un booleano.
  override fun puedeTrabajarEn(ciudad: Ciudad) = ciudades.contains(ciudad)

  // Metodo override, lo configuramos para que devuelva un tipo de dato.
  // Devuelve un tipo de dato boolenao.
  override fun influyente() = ciudades.count() >= 5 || ciudades.map{ it.provincia  }.toSet().size >= 3

}
// Creamos la clase.
// Va a tener una variable, que contiene un objeto de tipo de dato Ciudad.
// Luego tiene un objeto de tipo lista, que es mutable, se le puede agregar o eleminar objetos en este caso.
// El objeto que tiene la lista son de tipo Vendedor(Ya que trabaja con vendedores).
class Centros_De_Distribucion(val ciudad: Ciudad, val vendedores : MutableList<Vendedor>){

  // Este metodo hace algo y tambien puede llegar a devolver algo.
  // Recibe por parametro, un vendedor que obviamente es un objeto de tipo Vendedor(Puede ser cualquiera de los vendedores).
  // La funcion de este metodo es simple si el vendedor no esta en la lista, lo agrega, sino tira o devuelve error.
  fun agregar_Vendedor(vendedor : Vendedor){
    if ( !vendedores.contains(vendedor)){
      vendedores.add(vendedor)
    }
    else {
      error("El vendedor esta en la lista")
    }

    // Metodo que devuelve un objeto de tipo Vendedor.
    // Devuelve el vendedor que tiene el maximo puntaje total de certificiaciones.
    fun vendedor_Estrella() = vendedores.maxBy { it.puntajeCertificaciones()  }

    // Si puede cubrir, o no, una ciudad dada.
    // La condición es que al menos uno de los vendedores registrados pueda trabajar en esa ciudad.
    fun puede_Cubrir(ciudad: Ciudad): Boolean{
      return vendedores.any { vendedor ->  vendedor.puedeTrabajarEn(ciudad) }
    }

    // Buscar sobre los vendedores alguno que tenga al menos 1 certificacion.
    // Metodo que devuelte un tipo Boleano.
    fun vendedores_genericos() = vendedores.any { it.otrasCertificaciones() >= 1  }

    // Metodo que devuelve un tipo de dato booleano.
    fun esRobusto() : Boolean{
      return vendedores.filter { it.esFirme() }.toSet().count() >= 3
    }
  }
}



