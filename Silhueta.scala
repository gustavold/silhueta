/*
 * Main.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

object Silhueta {

	def trace(s: String) = println(s)

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]) :Unit = {
	// default confs
	var alg = "3"
	var arqIn = "-"
	var arqSilhueta = "-"
	var arqImg = "-"
	// supplied confs
	try {
		alg = args(0)
		arqIn = args(1)
		arqSilhueta = args(2)
		arqImg = args(3)
	}catch { case _ => /* That's ok */}

	val edifs = arqIn match {
		case "-"      => ManipuladorDeArquivos.lerEntradaPadrao
		case fileName => ManipuladorDeArquivos.lerArquivoDeEntrada(fileName)
	}

	val s = alg match {
		case "1" => AlgoritmosSilhueta.algoritmo1(edifs)
		case "2" => AlgoritmosSilhueta.algoritmo2(edifs)
		case "3" => AlgoritmosSilhueta.algoritmo3(edifs)
		case "L" => AlgoritmosSilhueta.silhuetaComFoldLeft(edifs)
		case "R" => AlgoritmosSilhueta.silhuetaComFoldRight(edifs)
		case x => println("Algoritmo " + x + " desconhecido."); exit(1)
	}
	
	arqSilhueta match {
		case "-"      => ManipuladorDeArquivos.escreveSilhuetaParaConsole(s)
		case fileName => ManipuladorDeArquivos.escreveSilhuetaParaArquivo(fileName, s)
	}

  arqImg match {
		case "-"      => Nil 
		case fileName => ManipuladorDeArquivos.geraImagem(s, fileName)
	}

  }  

}
