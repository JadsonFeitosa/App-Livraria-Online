import { LivroVendido } from './livro-vendido';



export interface BookPedido {
  dataVenda: Date;
  quantidadeVendida: number;
  bookId: LivroVendido;
}


