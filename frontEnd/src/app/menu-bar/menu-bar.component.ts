import { PedidoService } from './../pedido.service';
import { PrimeNGConfig } from 'primeng/api';
import { Livro } from './../livro';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  quantCarrinho: number

  mostrar: boolean;

  livro!: Livro;

  carrinho: Livro[];

  livros: [];

  constructor(private router: Router, private primengConfig: PrimeNGConfig,
              private route: ActivatedRoute,
              private pedidoService: PedidoService) {
    this.quantCarrinho = 0;
    this.carrinho = [];
    this.livros = [];
    this.mostrar = false;
  }

  ngOnInit(): void {
    this.quantCarrinho = this.livros.length;
  }

  addCarinho(livro: any) {
    this.livros.push();
  }
  listar(id: number) {
    this.router.navigate(['/home', id]);
  }

  open(){
    this.mostrar = true;
  }

  statusCarrinho(){
     this.carrinho = this.pedidoService.getCarrinho();
     this.quantCarrinho = this.carrinho.length;
  }

}
