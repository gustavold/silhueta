package silhueta

import scala.io.Source
import java.io._

object ManipuladorDeArquivos {
  
  def lerArquivoDeEntrada(nomeDoArquivo: String):List[Edificio] = {
    val list = Source.fromFile(nomeDoArquivo).getLines.toList
    processaEdificio(list.tail)
  }

  def processaEdificio(list: List[String]) : List[Edificio] ={
 
    if(list.size == 1){
      val p = list.head.split(Array(' ','\n'))
      return List(Edificio(p(0).toInt,p(1).toInt,p(2).toInt))
    }

    val p = list.head.split(Array(' ','\n'))
    return Edificio(p(0).toInt,p(1).toInt,p(2).toInt) :: processaEdificio(list.tail)
  }

  def escreveArquivoDeSilhueta(nomeDoArquivo: String, elemSilhuetas: List[ElemSilhueta]) = {
    val writer = new PrintWriter(new File(nomeDoArquivo))
    val silhuetas = processaSilhueta(elemSilhuetas)

    writer.write(silhuetas.size+"\n")
    silhuetas.foreach{
      writer.write
    }
    writer.close()
  }

  def processaSilhueta(list: List[ElemSilhueta]) : List[String] ={
    if(list.size == 1){
      val p = list.head.x + " "+list.head.h+"\n"
      return List(p)
    }

    val p = list.head.x + " "+list.head.h+"\n"
    return p :: processaSilhueta(list.tail)
  }
}
