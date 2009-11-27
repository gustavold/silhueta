package silhueta

import scala.io.Source
import java.io.PrintWriter
import java.io.File

object ManipuladorDeArquivos {
 
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
    Console.println(silhuetas.length)
    silhuetas.foreach(Console.print)
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
}
