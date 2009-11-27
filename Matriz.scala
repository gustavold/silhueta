
import java.util.ArrayList

class Matriz[T](val NLins: Int, val NCols: Int, valorInicial: T) {

  var matrizArray = new ArrayList[ArrayList[T]]
  for(lineNumber <- 0 to NLins - 1){
    matrizArray.add(new ArrayList[T])
    for(columnNumber <- 0 to NCols - 1){
      matrizArray.get(lineNumber).add(valorInicial)
    }
  }

  def apply(line: Int, column: Int): T = {
    return matrizArray.get(line).get(column)
  }

  def update(line: Int, column: Int, value: T): T = {
    matrizArray.get(line).set(column, value)
  }
}
