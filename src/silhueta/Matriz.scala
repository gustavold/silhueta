/*
 * Matriz.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package silhueta

import java.util.ArrayList

class Matriz[T](val NLins: Int, val NCols: Int) {

  var matrizArray = new ArrayList[ArrayList[Int]]
  for(lineNumber <- 0 to NLins - 1){
    matrizArray.add(new ArrayList[Int])
    for(columnNumber <- 0 to NCols - 1){
      matrizArray.get(lineNumber).add(0)
    }
  }

  def apply(line: Int, column: Int): Int = {
    return matrizArray.get(line).get(column)
  }

  def update(line: Int, column: Int, value: Int): Int = {
    matrizArray.get(line).set(column, value)
  }
}
