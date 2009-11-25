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
    ManipuladorDeArquivos.lerArquivoDeEntrada("/Users/marcioviniciussantos/Documents/Projects/silhueta/exemplo_entrada.txt")
    ManipuladorDeArquivos.escreveArquivoDeSilhueta("/Users/marcioviniciussantos/Documents/Projects/silhueta/teste_saida.txt", 
      List(ElemSilhueta(1,  11),ElemSilhueta(3,  13),ElemSilhueta(9,  0),ElemSilhueta(12,  7),ElemSilhueta(16,  3),ElemSilhueta(19,  18),ElemSilhueta(22,  3),ElemSilhueta(23,  13),ElemSilhueta(29,  0)))
  }

}
