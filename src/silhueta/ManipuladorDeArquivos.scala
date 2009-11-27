package silhueta

import scala.io.Source
import java.io.PrintWriter
import java.io.File

object ManipuladorDeArquivos {
 
  val NLins = 600                     // número de linhas da imagem
  val NCols = 800                     // número de colunas da imagem
  val BordaInf = NLins - 1            // borda inferior (última linha da imagem)
  val MargemInf = 20                  // linhas do eixo base à borda inferior da imagem
  val Base = BordaInf - MargemInf     // linha do eixo base
  val Branco = 15                     // valor de maxval
  val Cinza = 10                      // cor da silhueta preenchida
  val Preto = 0                       // cor do eixo base


  def lerEntradaPadrao() : List[Edificio] = {
    Console.readLine //discard first line
    lerEntradaPadrao(Nil)
  }

  private def lerEntradaPadrao(acc: List[String]) : List[Edificio] = {
    Console.readLine match {
      case null => processaEdificio(acc)
      case line => lerEntradaPadrao(line :: acc)
    }
  }

  def lerArquivoDeEntrada(nomeDoArquivo: String):List[Edificio] = {
    val list = Source.fromFile(nomeDoArquivo).getLines.toList
    processaEdificio(list.tail)
  }

  private def processaEdificio(list: List[String]) : List[Edificio] = processaEdificio(list, Nil)

  private def processaEdificio(list: List[String], acc: List[Edificio]) : List[Edificio] = (list, acc)  match {
    case (Nil, acc) => acc
    case (h :: t, acc) => {
        val p = h.split(Array(' ','\n'))
   	 		processaEdificio(t, Edificio(p(0).toInt,p(1).toInt,p(2).toInt) :: acc)
      }
  }

  def escreveSilhuetaParaConsole(elemSilhuetas: List[ElemSilhueta]) = {
    val silhuetas = processaSilhueta(elemSilhuetas)
    println(silhuetas.length)
    silhuetas.foreach(print)
  }

  def escreveSilhuetaParaArquivo(nomeDoArquivo: String, elemSilhuetas: List[ElemSilhueta]) = {
    val writer = new PrintWriter(new File(nomeDoArquivo))
    val silhuetas = processaSilhueta(elemSilhuetas)

    writer.write(silhuetas.size+"\n")
    silhuetas.foreach{
      writer.write
    }
    writer.close()
  }

  //TODO: this should be tail recursive (probably could be a map)
  private def processaSilhueta(list: List[ElemSilhueta]) : List[String] ={
    if(list.size == 1){
      val p = list.head.x + " "+list.head.h+"\n"
      return List(p)
    }

    val p = list.head.x + " "+list.head.h+"\n"
    return p :: processaSilhueta(list.tail)
  }


  /* Recebe uma matriz a, dois índices de linha lin1 e lin2 tais que 0 ≤ lin1 ≤ lin2, dois índices de
   * coluna col1 e col2 tais que 0 ≤ col1 ≤ col2, e um valor k. A função preencheRetangulo preenche
   * com o valor k a submatriz retangular de a  delimitada por lin1, lin2, col1 e col2.
   */
  def preencheRetangulo(a: Matriz[Int],
                        lin1: Int, lin2: Int,
                        col1: Int, col2: Int, k: Int): Unit = {
    for(l <- lin1 to lin2){
      for(c <- col1 to col2){
        a(l,c) = k
      }
    }
  }

  def preencheMatriz(atual: ElemSilhueta, list: List[ElemSilhueta], matriz: Matriz[Int]): Unit = {
    if(list.size == 1)
    return
    val proximo = list.head
    println("0 <= l1:"+(Base - atual.h) + " <=l2:"+ (Base)+ ", 0 <=c1:"+ atual.x+ " <=c2:"+ proximo.x)
    preencheRetangulo(matriz, Base - atual.h, Base, atual.x, proximo.x, Cinza)
    preencheMatriz(proximo, list.tail, matriz)
  }

  def geraImagem(s: List[ElemSilhueta], nomeArq: String): Unit = {
    val writer = new PrintWriter(new File(nomeArq))

    writer.write("P2\n")
    writer.write("" + NCols + " " + NLins+"\n")
    writer.write("" + Branco+"\n")

    var matriz = new Matriz[Int](NLins, NCols, Branco)

    s.foreach{a=>
      println("h:"+a.h+"x:"+a.x)
    }

    preencheMatriz(s.head, s.tail, matriz)

    for(x <- 0 to NCols-1){
      matriz(Base, x) = Preto
    }

    for(l <- 0 to NLins-1){
      var line = ""
      for(c <- 0 to NCols-1){
        line = line + " " + matriz(l,c)
      }
      writer.write(line+"\n")
    }

    writer.close()
  }
}
