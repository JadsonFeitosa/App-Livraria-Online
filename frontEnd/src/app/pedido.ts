import { Data } from "@angular/router";
import { BookPedido } from "./book-pedido";
import { Livro } from "./livro";

export interface Pedido {

pedidoID: number;
user: {userID: 1};
total: number;
dataCriacao: Date;
status: string;
pagamento: {pagamentoId: 1};
dataFechamento: Data;
listaBook: BookPedido[];
}
