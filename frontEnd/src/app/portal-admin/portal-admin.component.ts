import { PedidoService } from './../pedido.service';
import { Component, OnInit } from '@angular/core';
import { Pedido } from '../pedido';

@Component({
  selector: 'app-portal-admin',
  templateUrl: './portal-admin.component.html',
  styleUrls: ['./portal-admin.component.css']
})
export class PortalAdminComponent implements OnInit {

  pedidos: Pedido[];
  pedido!: Pedido;
  boleto = 0;
  playpal = 0;
  ctdebito = 0;
  ctcredito = 0;
  total = 0;

  constructor(private pedidoService: PedidoService) {
    this.pedidos = [];

   }

  ngOnInit(): void {
    this.listAll();
  }

  listAll() {
    this.pedidoService.listAll().then(data => {
      this.pedidos = data
      for(let i=0;i<this.pedidos.length;i++){
        this.total +=  this.pedidos[i].total;

      }

    });


  }

}
