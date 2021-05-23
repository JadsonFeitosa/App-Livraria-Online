import { LivroVendido } from './../livro-vendido';
import { MessageService, ConfirmationService, PrimeNGConfig } from 'primeng/api';
import { BookPedido } from './../book-pedido';
import { Pedido } from './../pedido';
import { LivroService } from './../livro.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Livro } from '../livro';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-detalhe-produto',
  templateUrl: './detalhe-produto.component.html',
  styleUrls: ['./detalhe-produto.component.css']
})
export class DetalheProdutoComponent implements OnInit {

  livro: Livro;
  quantidade = 1;
  total = 0;
  quant='';
  quantCarrinho=0;
  pedidoid=0;
  pedido!: Pedido;
  bookPedido!: BookPedido;
  livroVendido: LivroVendido = {livroId: 0};

  constructor(private router: Router, private livroService: LivroService,
              private primengConfig: PrimeNGConfig,
              private route: ActivatedRoute,
              private pedidoService: PedidoService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,) {

    const rota = this.router.getCurrentNavigation();
    this.livro = rota?.extras.state?.Livro;
    console.log(this.livro);
    this.bookPedido = {dataVenda: new Date(), quantidadeVendida: 0, bookId: this.livro};
              this.pedido = {
                pedidoID: 0,
                user: { userID: 1 },
                total: 0,
                dataCriacao: new Date(),
                status: 'PENDENTE',
                pagamento: {pagamentoId: 1},
                dataFechamento: new Date(),
                listaBook: []}
  }

  ngOnInit(): void {
    if (!this.livro) {
      this.livro = this.livroService.getLivro();
    }
  }

  finalizarPedido() {
    this.total = this.quantidade * this.livro.price;
    this.router.navigate(['/check', this.total]);
  }

  addCarrinho(id: number,  price: number) {
    this.messageService.add({ severity: 'success', summary: 'Carrinho', detail: 'Livro adicionado com sucesso', life: 3000 });
    this.livroVendido.livroId = id;
    this.quantCarrinho++;
    this.quant = ""+this.quantCarrinho;
    this.bookPedido.bookId = this.livroVendido;
    this.bookPedido.quantidadeVendida = this.quantidade;
    this.bookPedido.dataVenda = new Date();
    this.pedido.total += this.bookPedido.quantidadeVendida * price;
    // this.pedidoService.addCarrinho(this.livro);

    if (this.pedido.pedidoID === 0) {
      this.pedido.listaBook.push(this.bookPedido);

      // criando o pedido com carrinho
      this.pedidoService.addCarrinho(this.bookPedido);
      this.pedidoService.salvar(this.pedido).then(productSalvo =>{
      this.pedido = productSalvo;
      this.pedidoid=this.pedido.pedidoID;
      console.log(this.pedido);
      });

    } else {
      this.pedido.listaBook = this.pedidoService.getCarrinho();
      this.pedido.listaBook.push(this.bookPedido);
      this.pedidoService.addCarrinho(this.bookPedido);
      this.pedidoService.update(this.pedido).then(productSalvo =>{
        this.pedido = productSalvo
        this.pedidoid=this.pedido.pedidoID;
        this.messageService.add({ severity: 'success', summary: 'Carrinho', detail: 'Livro adicionado ao carrinho', life: 3000 });
      });
      }
  }



}
