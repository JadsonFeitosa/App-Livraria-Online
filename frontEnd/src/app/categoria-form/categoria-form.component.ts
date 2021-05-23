import { CategoriaService } from './../categoria.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';




@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.css']
})

export class CategoriaFormComponent implements OnInit {

  categoria = { categoriaId: '', descricao: '' };

  telaDialog = false;

  submitted = false;

  categorias = [];

  selectedCategorias = [];


  constructor(

    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private categoriaService: CategoriaService) {
  }



  ngOnInit() {
    this.listAll();

  }

  openNew() {
    this.categoria = { categoriaId: '', descricao: '' };
    this.submitted = false;
    this.telaDialog = true;
  }

  listAll() {
    this.categoriaService.listAll().then(categoria => {
      this.categorias = categoria;
    });
  }


  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir os produtos selecionados?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //    this.categoria = this.categoria.filter(val => !this.selectedEnderecos.includes(val));
        //   this.selectedProducts = null;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
      }
    });
  }


  delete(id: number, nome: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir categoria ' + nome + '?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.categoriaService.delete(id)
          .then(() => {
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Categoria Deletado com sucesso', life: 3000 });
            this.listAll();
          });

      }
    });
  }


  update(categoria: any) {
    this.categoria = { ...categoria };
    this.telaDialog = true;

  }

  save(categoria: any) {
    this.submitted = true;
    this.categoriaService.salvar(categoria)
      .then(enderecoSalvo => {
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Categoria Cadastrado com sucesso', life: 3000 });
        this.telaDialog = false;
        this.listAll();
      });
  }

  hideDialog() {
    this.telaDialog = false;
    this.submitted = false;
  }
}

