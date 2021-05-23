import { Pedido } from './pedido';
import { Livro } from './livro';
import { MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  readonly apiURL: string;

  carrinho:any[];

  constructor(private http: HttpClient, private messageService: MessageService) {
    this.apiURL = 'http://localhost:8080/pedido';
    this.carrinho = this.getCarrinho();
  }

  addCarrinho(livro: any) {
    this.carrinho.push(livro);
    console.log(livro);

    console.log(this.carrinho);
  }

  removeCarrinho() {
    this.carrinho.pop();
  }

  getCarrinho(): any[] {
    if (!this.carrinho) {
      return this.carrinho = [];

    } else {
      return this.carrinho;
    }

  }

  listAll(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(pedido: Pedido): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, pedido)
      .toPromise()
      .then(response => response);

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(pedido: Pedido): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${pedido.pedidoID}`, pedido)
      .toPromise()
      .then(response => response);
      // .catch(erro => {
      //   return Promise.reject(`Erro ao alterar pedido ${pedido.pedidoID}.`);
      // });
  }

  listaCarrinho(id: number): Promise<any> {
    return this.http.get<any>(`${this.apiURL}/listcart/${id}`)
      .toPromise()
      .then(response => response.content);
  }


}

