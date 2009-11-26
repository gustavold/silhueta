/*
 * Main.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package silhueta

object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]) :Unit = {
    println("Hello, world!")
    val edifs = ManipuladorDeArquivos.lerArquivoDeEntrada("entrada.txt")
    val s = Silhueta.silhuetaComFoldLeft(edifs) 
    ManipuladorDeArquivos.escreveArquivoDeSilhueta("teste_saida.txt", s) 
  }

}
