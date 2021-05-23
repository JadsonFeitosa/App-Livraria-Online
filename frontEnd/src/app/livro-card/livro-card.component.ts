import { LivroVendido } from './../livro-vendido';
import { PedidoService } from './../pedido.service';
import { LivroService } from './../livro.service';
import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig, MessageService, ConfirmationService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { Livro } from '../livro';
import { Pedido } from '../pedido';
import { BookPedido } from '../book-pedido';

@Component({
  selector: 'app-livro-card',
  templateUrl: './livro-card.component.html',
  styleUrls: ['./livro-card.component.css']
})




export class LivroCardComponent implements OnInit {
  // tipo de consulta
  tipo = 1;
  pedidoid=0;
  pedido!: Pedido;
  bookPedido!: BookPedido;
  livro: LivroVendido = {livroId: 0};

  // pedido = {
  //   user: { userID: 1 },
  //   total: 150.0,
  //   dataCriacao: Date,
  //   status: 'PENDENTE',
  //   pagamento: {},
  //   dataFechamento: Date,
  //   listaBook: []
  // };

  // { dataVenda: null, quantidadeVendida: 10, bookid: { livroId: 0 }, pedidoid: 0 }

  products: [];

  quant='';

  quantCarrinho=0;

  listCarrinho: [any];

  totalCarrinho: number;

  sortOptions = [{ label: 'Preço alto para baixo', value: '!price' },
  { label: 'Preço baixo para alto', value: 'price' }];

  sortOrder: number;

  sortField: string;


  constructor(private productService: LivroService,
              private primengConfig: PrimeNGConfig,
              private router: Router,
              private route: ActivatedRoute,
              private pedidoService: PedidoService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,) {
              this.products = [];
              this.listCarrinho = [{}];
              this.sortField = '';
              this.sortOrder = 5;
              this.totalCarrinho = 0;
              this.bookPedido = {dataVenda: new Date(), quantidadeVendida: 0, bookId: this.livro};
              this.pedido = {
                pedidoID: 0,
                user: { userID: 1 },
                total: 0,
                dataCriacao: new Date(),
                status: 'PENDENTE',
                pagamento: {pagamentoId: 1},
                dataFechamento: new Date(),
                listaBook: []
              };

  }

  ngOnInit() {
    this.route.params.subscribe(params => this.tipo = + params['id']);

    if (this.tipo === 2) {
      this.listaCinco();
    } else {
      this.productService.listAll()
        .then(data => this.products = data);
    }



    this.sortOptions = [
      { label: 'Preço alto para baixo', value: '!price' },
      { label: 'Preço baixo para alto', value: 'price' }
    ];

    this.primengConfig.ripple = true;
  }

  onSortChange(event: { value: any; }) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }

  listaCinco() {
    this.productService.listCinco()
      .then(data => this.products = data);
  }


  addCarrinho(id: number,  price: number) {
    this.messageService.add({ severity: 'success', summary: 'Carrinho', detail: 'Livro adicionado com sucesso', life: 3000 });
    this.livro.livroId = id;
    this.quantCarrinho++;
    this.quant = ""+this.quantCarrinho;
    this.bookPedido.bookId = this.livro;
    this.bookPedido.quantidadeVendida += 1;
    this.bookPedido.dataVenda = new Date();
    this.pedido.total += this.bookPedido.quantidadeVendida * price;
    // this.pedidoService.addCarrinho(this.livro);

    if (this.pedido.pedidoID === 0) {
      this.pedido.listaBook.push(this.bookPedido);
      
      // criando o pedido com carrinho
      this.pedidoService.addCarrinho(this.pedido);
      this.pedidoService.salvar(this.pedido).then(productSalvo =>{
      this.pedido = productSalvo;
      this.pedidoid=this.pedido.pedidoID;
      console.log(this.pedido);
      });

    } else {
      this.pedido.listaBook = this.pedidoService.getCarrinho();
      this.pedido.listaBook.push(this.bookPedido);
      this.pedidoService.addCarrinho(this.pedido);
      this.pedidoService.update(this.pedido).then(productSalvo =>this.pedido = productSalvo);
      this.pedidoid=this.pedido.pedidoID;
      console.log(this.pedidoid);
      this.messageService.add({ severity: 'success', summary: 'Carrinho', detail: 'Livro adicionado ao carrinho', life: 3000 });
      }
  }


  option(livro: Livro) {
    this.router.navigateByUrl('/detalhe-produto');
    this.productService.setLivro(livro);
  }


}
