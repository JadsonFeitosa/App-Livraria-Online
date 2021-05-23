import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu-admin',
  templateUrl: './menu-admin.component.html',
  styleUrls: ['./menu-admin.component.css']
})
export class MenuAdminComponent implements OnInit {

  items: MenuItem[];
  activeItem: MenuItem;

  constructor() {
    this.items = [];
    this.activeItem = {};
  }



  ngOnInit() {
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', url: 'admin', id: 'home'},
      { label: 'Autor', icon: 'pi pi-user-plus', url: 'autor', id: 'autor' },
      { label: 'Pedido', icon: 'pi pi-folder-open', url: 'pedido-geral',id: 'pedido' },
      { label: 'Estoque', icon: 'pi pi-inbox', url: 'livro',id: 'estoque' },
      { label: 'Categoria', icon: 'pi pi-tags', url: 'categoria',id: 'categoria' },
      { label: 'Endereço', icon: 'pi pi-map', url: 'endereco',id: 'endereço' },
      { label: 'Editora', icon: 'pi pi-sitemap', url: 'editora',id: 'editora' },
      { label: 'Pagamento', icon: 'pi pi-money-bill', url: 'pagamento',id: 'pagaemnto' },
      { label: 'Sair', icon: 'pi pi-sign-out', url: 'home/1',id: 'sair' },
    ];

    this.activeItem = this.items[0];
  }

  setItem(item: MenuItem){
    this.activeItem = item;
  }
}
