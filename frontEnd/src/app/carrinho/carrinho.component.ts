import { Carrinho } from './../carrinho';
import { element } from 'protractor';
import { Data, Router, RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Livro } from '../livro';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  total = 0;
  pedidoID = 0;

  carrinho: Carrinho[] = [];

  constructor( private pedidoService: PedidoService, private  router: Router,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.pedidoID = + params['id']);
    this.statusCarrinho();
  }

  statusCarrinho(){
    this.pedidoService.listaCarrinho(this.pedidoID).then(data => {
      this.carrinho = data;
      for(let i = 0; i<this.carrinho.length;i++){
        this.total += this.carrinho[i].price;
        console.log(this.carrinho[0]);
      }

    });


 }

 finalizarPedido(){
  this.router.navigate(['/check', this.total]);

 }

}
