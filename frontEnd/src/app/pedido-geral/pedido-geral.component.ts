import { ConfirmationService, MessageService } from 'primeng/api';
import { PedidoService } from './../pedido.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pedido-geral',
  templateUrl: './pedido-geral.component.html',
  styleUrls: ['./pedido-geral.component.css']
})
export class PedidoGeralComponent implements OnInit {

  pedido = { pedidoID: '', cliente: '', pagamento: '', total: 0, dataCriacao: '', dataFechamento: '', status: '' };
  pedidos = [];
  boleto = 0;
  playpal = 0;
  ctdebito = 0;
  ctcredito = 0;
  total = 0;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private pedidoService: PedidoService) {

  }

  ngOnInit(): void {
    this.listAll();
  }

  listAll() {
    this.pedidoService.listAll().then(dados => {
      this.pedidos = dados;
    });
    for (const p of this.pedidos) {
      // this.pedido = { ...p };
      if (this.pedido.pagamento.toLowerCase() === 'boleto') {
        this.boleto += this.pedido.total;
      }else if (this.pedido.pagamento.toLowerCase() === 'playpal') {
        this.playpal += this.pedido.total;
      }else if (this.pedido.pagamento.toLowerCase() === 'cartão de crédito') {
        this.ctcredito += this.pedido.total;
      }else if (this.pedido.pagamento.toLowerCase() === 'cartão de débito') {
        this.ctdebito += this.pedido.total;
      }
      this.total += this.pedido.total;

      }
    }
  }
