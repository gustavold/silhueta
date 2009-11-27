
case class Edificio(
  esq: Int,       // coordenada horizontal esquerda do edifício
  alt: Int,       // altura do edifício
  dir: Int        // coordenada horizontal esquerda do edifício
)



case class ElemSilhueta(
  x: Int, // coordenada horizontal
  h: Int  // altura
)

object AlgoritmosSilhueta {


/* iterativo
 */
def algoritmo1(edifs: List[Edificio]): List[ElemSilhueta] = {
	var s = silhuetaDeEdificio(edifs.head)
	for (k <- 1 until edifs.length){
		s = uniao(s, silhuetaDeEdificio(edifs(k)))
	}
	return s
}


/* recursivo
 */
def algoritmo2(edifs: List[Edificio]): List[ElemSilhueta] = algoritmo2(edifs, Nil)

private def algoritmo2(edifs: List[Edificio], s: List[ElemSilhueta]): List[ElemSilhueta] = edifs match {
	case Nil => s
	case h :: t => algoritmo2(t, uniao(silhuetaDeEdificio(h), s))
}

  /* divisao e conquista
   */
  def algoritmo3(edifs: List[Edificio]): List[ElemSilhueta] = edifs match {
	  case edif :: Nil => silhuetaDeEdificio(edif)
	  case edifs => { val (s1, s2) = edifs.splitAt(edifs.length/2)
	  		  uniao(algoritmo3(s1), algoritmo3(s2))
  			}
  }


/* foldLeft
 */
def silhuetaComFoldLeft(edifs: List[Edificio]): List[ElemSilhueta] = {
	edifs.foldLeft(List[ElemSilhueta]())((s1: List[ElemSilhueta], edif: Edificio) => uniao(s1, silhuetaDeEdificio(edif)))
	
} 

  /* foldRight
   */
  def silhuetaComFoldRight(edifs: List[Edificio]): List[ElemSilhueta] = {
	edifs.foldRight(List[ElemSilhueta]())((edif: Edificio, s1: List[ElemSilhueta]) => uniao(s1, silhuetaDeEdificio(edif)))
}

//def trace(s: String) = println(s)
def trace(s: String) = Nil

/* Recebe duas silhuetas s1 e s2, determina a união dessas silhuetas 
 */
def uniao(s1: List[ElemSilhueta], s2: List[ElemSilhueta]): List[ElemSilhueta] =	uniao(s1, s2, Nil, 0)

def uniao(s1: List[ElemSilhueta], s2: List[ElemSilhueta], uni: List[ElemSilhueta], hbaixo: Int): List[ElemSilhueta] = (s1, s2, uni, hbaixo) match {
	// fim da recursao
	case (Nil, Nil, u, _) => u.reverse
	case (s, Nil, u, _) => u reverse_::: s
	case (Nil, s, u, _) => u reverse_::: s
	// inicio da recursao
	case (h1 :: t1, h2 :: t2, Nil, hb) if h1.x < h2.x => uniao( t1, h2 :: t2, h1 :: Nil, 0)
	case (h1 :: t1, h2 :: t2, Nil, hb) if h1.x > h2.x => uniao( t2, h1 :: t1, h2 :: Nil, 0)
	case (h1 :: t1, h2 :: t2, Nil, hb) if h1.x == h2.x && h1.h >= h2.h => uniao(t1, t2, h1 :: Nil, h2.h)
	case (h1 :: t1, h2 :: t2, Nil, hb) if h1.x == h2.x && h1.h < h2.h => uniao(t2, t1, h2 :: Nil, h1.h)
	// durante a recursao
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h2.x < h1.x && h2.h > hu.h => uniao(t2,  h1 :: t1, h2 :: hu :: tu, hu.h)
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h2.x < h1.x && h2.h <= hu.h => uniao(h1 :: t1, t2, hu :: tu, h2.h)
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h1.x < h2.x && h1.h >= hb => uniao(t1, h2 :: t2, h1 :: hu :: tu, hb)
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h1.x < h2.x && h1.h < hb => uniao(h2 :: t2, t1, ElemSilhueta(h1.x, hb) :: hu :: tu, h1.h)
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h1.x == h2.x && h1.h >= h2.h => uniao(t1, t2, h1 :: hu :: tu, h2.h)
	case (h1 :: t1, h2 :: t2, hu :: tu, hb) if h1.x == h2.x && h1.h < h2.h => uniao(t2, t1, h2 :: hu :: tu, h1.h)
	case (_, _, _, _) => println("nao deveria entrar aqui"); Nil
}


/* Devolve a silhueta do edifício edif
 */
def silhuetaDeEdificio(edif: Edificio): List[ElemSilhueta] = List(new ElemSilhueta(edif.esq, edif.alt), new ElemSilhueta(edif.dir, 0))



}


