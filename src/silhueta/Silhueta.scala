package silhueta

import silhueta.Matriz

    case class Edificio(
        esq: Int,       // coordenada horizontal esquerda do edifício
        alt: Int,       // altura do edifício
        dir: Int        // coordenada horizontal esquerda do edifício
    )



    case class ElemSilhueta(
        x: Int, // coordenada horizontal
        h: Int  // altura
    )

class Silhueta {

    val NLins = 600                     // número de linhas da imagem
    val NCols = 800                     // número de colunas da imagem
    val BordaInf = NLins - 1            // borda inferior (última linha da imagem) 
    val MargemInf = 20                  // linhas do eixo base à borda inferior da imagem
    val Base = BordaInf - MargemInf     // linha do eixo base 
    val Branco = 15                     // valor de maxval
    val Cinza = 10                      // cor da silhueta preenchida
    val Preto = 0                       // cor do eixo base


/* iterativo
 */
def algoritmo1(edifs: List[Edificio]): List[ElemSilhueta] = silhuetaComFoldLeft(edifs)



/* recursivo
 */
def algoritmo2(edifs: List[Edificio]): List[ElemSilhueta] = {
null	
}


/* divisao e conquista
 */
def algoritmo3(edifs: List[Edificio]): List[ElemSilhueta] = {null}


/* ???
 */
def silhuetaComFoldLeft(edifs: List[Edificio]): List[ElemSilhueta] = {
	edifs.foldLeft(List[ElemSilhueta]())((s1: List[ElemSilhueta], edif: Edificio) => uniao(s1, silhuetaDeEdificio(edif)))
	
} 


/* ???
 */
def silhuetaComFoldRight(edifs: List[Edificio]): List[ElemSilhueta] = {null}


/* Recebe duas silhuetas s1 e s2, determina a união dessas silhuetas 
 */
def uniao(s1: List[ElemSilhueta], s2: List[ElemSilhueta]): List[ElemSilhueta] = (s1, s2) match {
	case (Nil, Nil) => Nil
	case (s, Nil) => s
	case (Nil, s) => s
	//TODO: dois predios podem ter a mesma coordenada x? case (h1 :: t1, h2 :: t2) if h1.x == h2.x => h1 :: uniao(t1, h2 :: t2)
	case (h1 :: t1, h2 :: t2) if h1.x < h2.x => h1 :: uniao(t1, h2 :: t2)
	case (s, h2 :: t2) => h2 :: uniao(s, t2)
}


/* Devolve a silhueta do edifício edif
 */
def silhuetaDeEdificio(edif: Edificio): List[ElemSilhueta] = List(new ElemSilhueta(edif.esq, edif.alt), new ElemSilhueta(edif.dir, 0))


/* Recebe uma silhueta s e a converte para uma imagem no formato PGM
 */
def geraImagem(s: List[ElemSilhueta], nomeArq: String): Unit = {null}


/* Recebe uma matriz a, dois índices de linha lin1 e lin2 tais que 0 ≤ lin1 ≤ lin2, dois índices de 
 * coluna col1 e col2 tais que 0 ≤ col1 ≤ col2, e um valor k. A função preencheRetangulo preenche 
 * com o valor k a submatriz retangular de a  delimitada por lin1, lin2, col1 e col2.
 */ 
def preencheRetangulo(a: Matriz[Int], 
		      lin1: Int, lin2: Int,
		      col1: Int, col2: Int, k: Int): Unit = {null}

}


