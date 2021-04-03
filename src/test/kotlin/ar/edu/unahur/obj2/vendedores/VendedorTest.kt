package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      describe("¿Es influyente?") {
        it("NO") {
          vendedorFijo.influyente().shouldBeFalse()
        }
      }
    }
    describe("¿Es firme?") {
      it("NO") {
        vendedorFijo.esFirme().shouldBeFalse()
      }
      val certificaciones = Certificacion(true, 45)
      vendedorFijo.agregarCertificacion(certificaciones)
      it("SI") {
        vendedorFijo.esFirme().shouldBeTrue()
      }
      vendedorFijo.quitarCertificacion(certificaciones)
      describe("¿Que pasa si saco la certificacion?") {
        it("Me devuelve Falso") {
          vendedorFijo.esFirme().shouldBeFalse()
        }
      }
    }
  describe("Vendedor con Certificaciones") {
    describe("¿Cuanto putaje tiene?") {
      it("Sin certificaciones = 0") {
        vendedorFijo.puntajeCertificaciones().shouldBeExactly(0)
      }
      val certificaciones = Certificacion(true, 100)
      vendedorFijo.agregarCertificacion(certificaciones)
      it("Con Certificaciones = 100") {
        vendedorFijo.puntajeCertificaciones().shouldBeExactly(100)
      }

    }
    describe("¿Cuantas certificaciones tiene?"){
      it("Sin ninguna = cantidad 0"){
        vendedorFijo.certificacionesDeProducto().shouldBeExactly(0)
      }
      val certificaciones = Certificacion(true, 100)
      val certificaciones2 = Certificacion(true, 102)
      val certificaciones1 = Certificacion(true, 101)
      val certificaciones4 = Certificacion(false, 104)
      val certificaciones3 = Certificacion(false, 103)
      vendedorFijo.agregarCertificacion(certificaciones)
      vendedorFijo.agregarCertificacion(certificaciones1)
      vendedorFijo.agregarCertificacion(certificaciones2)
      vendedorFijo.agregarCertificacion(certificaciones3)
      vendedorFijo.agregarCertificacion(certificaciones4)
      it("Si tengo 3 = cantidad 3"){
        vendedorFijo.certificacionesDeProducto().shouldBeExactly(3)
      }
      describe("Agrego 2 Certificaciones que no son de productos"){
        it("Me da la cantidad total = 2"){
          vendedorFijo.otrasCertificaciones().shouldBeExactly(2)
        }

      }
    }
  }
  }


  describe("Viajante_1") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    describe("¿Es influyente?") {
      it("NO") {
        viajante.influyente().shouldBeFalse()
      }
    }
    }
  }
  describe("Viajante_2"){
    val cordoba = Provincia(1)
    val durazno = Ciudad(cordoba)
    val formosa = Provincia(3)
    val viajante = Viajante(listOf(formosa))

    describe("¿Es Influyente?"){
      it("NO"){
        viajante.influyente().shouldBeFalse()
      }
      val buenos_aires = Provincia(10000000)
      val san_justo = Ciudad(buenos_aires)
      val viajante = Viajante(listOf(buenos_aires))
      it("SI"){
      viajante.influyente().shouldBeTrue()
      }
      describe("¿Puede trabajar en esa Ciudad ?") {
        it("SI") {
          viajante.puedeTrabajarEn(san_justo).shouldBeTrue()
        }
        it("NO") {
          viajante.puedeTrabajarEn(durazno).shouldBeFalse()
        }
      }
      describe("Si tengo Certificaciones como Vendedor"){
      describe("¿Es firme?"){
        it("N0"){
          viajante.esFirme().shouldBeFalse()
        }
        val certificado1 = Certificacion(false,30)
        viajante.agregarCertificacion(certificado1)
        it("SI"){
          viajante.esFirme().shouldBeTrue()
        }
        describe("Puntaje"){
          it("Tengo 30 de puntaje"){
            viajante.puntajeCertificaciones().shouldBeExactly(30)
          }
        }
      }
    }

    }
  }

  val tucuman = Provincia(123123)
  val formosa = Provincia(90)
  val carapolasca = Ciudad(formosa)
  val buenosaires = Provincia(6000000)
  val moron = Ciudad(buenosaires)
  val castelar = Ciudad(buenosaires)
  val ituzaingo = Ciudad(buenosaires)
  val haedo = Ciudad(buenosaires)
  val fedeLandia = Ciudad(buenosaires)
  val chaco = Provincia(24000)
  val pepe = Ciudad(chaco)
  val certificado = Certificacion(true,300)
  val certificado2 = Certificacion(false,250)
  val certificado3 = Certificacion(true,400)

  describe("Comercio Corresponsal"){
    val comercio1 = ComercioCorresponsal(listOf(carapolasca,moron,ituzaingo,haedo,castelar))
    describe("¿Puede trabajar en las siguientes Ciudades?"){
      it("Carapolasca"){
        comercio1.puedeTrabajarEn(carapolasca).shouldBeTrue()
      }
      it("Moron"){
        comercio1.puedeTrabajarEn(moron).shouldBeTrue()
      }
      it("FedeLandia"){
        comercio1.puedeTrabajarEn(fedeLandia).shouldBeFalse()
      }
    }
    describe("¿Es influyente con ciudades?"){
      it("SI"){
        comercio1.influyente().shouldBeTrue()
      }
      val comercio2 = ComercioCorresponsal(listOf(carapolasca,fedeLandia))
      it("NO"){
        comercio2.influyente().shouldBeFalse()
      }
    }
    describe("¿Es influyente con provincias?"){
      val comercio4 = ComercioCorresponsal(listOf(fedeLandia,moron,ituzaingo))
      it("NO"){
        comercio4.influyente().shouldBeFalse()
      }
      val comercio3 = ComercioCorresponsal(listOf(fedeLandia,carapolasca,pepe))
      it("SI"){
        comercio3.influyente().shouldBeTrue()
      }
    }
    describe("¿Puede trabajar?"){
      it("En Ituazaingo"){
        comercio1.puedeTrabajarEn(ituzaingo).shouldBeTrue()
      }
      it("En Moron"){
        comercio1.puedeTrabajarEn(moron).shouldBeTrue()
      }
      it("En Pepe"){
        comercio1.puedeTrabajarEn(pepe).shouldBeFalse()
      }
    }
    describe("¿Es versatil?"){
      comercio1.agregarCertificacion(certificado)
      it("NO"){
        comercio1.esVersatil().shouldBeFalse()
      }
      comercio1.agregarCertificacion(certificado2)
      comercio1.agregarCertificacion(certificado3)
      it("SI"){
        comercio1.esVersatil().shouldBeTrue()
      }
    }
  }
  val viajante1 = Viajante(listOf(tucuman))
  val fijo1 = VendedorFijo(carapolasca)
  describe("Centros de distribuicion") {

    val centro1 = Centros_De_Distribucion(moron, mutableListOf(fijo1))

    it("Agrego vendedor viajante") {
      centro1.agregar_Vendedor(viajante1)
    }
    it("Agrego vendedor mismo vendedor = ERROR ") {
      shouldThrow<Exception> { centro1.agregar_Vendedor(fijo1) }
    }




    // Este test no lo entiendo bien porque el vendedor estrella seria la suma total de puntajes de los certificados, tengo 2 tipos de vendedores....
    // pero no tienen ninguna certificaciones, osea no tienen puntaje alguno.
    // pero si pongo vendedor fijo1 me tira como que "esta bien".
    // ahora, si pongo el otro vendeor viajante metira que esta mal y nose bien porque...
    // capas es la consulta o lo que espero, que esta mal..
    describe("Vendedor estrella") {
      centro1.vendedor_Estrella().shouldBe(fijo1)
      // centro1.vendedor_Estrella().shouldBe(viajante1)
    }
  }
})
