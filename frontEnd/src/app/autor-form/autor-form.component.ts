import { AutorService } from './../autor.service';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-autor-form',
  templateUrl: './autor-form.component.html',
  styleUrls: ['./autor-form.component.css']
})


export class AutorFormComponent implements OnInit {

  options: any[];

  autorDialog = false;

  autores = [];

  autor = {nome: '', sexo: '', nacionalidade: '', dtNascimento: new Date() };

  dataNasc: Date;

  selectedAutors = [];

  submitted = false;


  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private autorService: AutorService

    ) {

    this.dataNasc = new Date();
    this.options = [{ label: 'Masculino', value: 'M', id: 'M', name: 'masc' }, { label: 'Feminino', value: 'F', id: 'F', name: 'fam' }];
  }

  ngOnInit() {
   this.listAutores();
  }

  openNew() {
    this.autor = {nome: '', sexo: '', nacionalidade: '', dtNascimento: new Date()};
    this.submitted = false;
    this.autorDialog = true;
  }

  listAutores() {
    this.autorService.listAutores().then(autores => {
      this.autores = autores;
    });
  }


  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir os produtos selecionados?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //  this.autor = this.autor.filter(val => !this.selectedAutor.includes(val));
        // this.selectedProducts = null;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
      }
    });
  }


  delete(id: number, nome: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir autor(a) ' + nome + '?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.autorService.delete(id)
          .then(() => {
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Autor Deletado com sucesso', life: 3000 });
            this.listAutores();
          });

      }
    });
  }


  update(autor: any) {
    this.autor = {...autor};
    this.autorDialog = true;
     /* .then(() => {
        //this.product = {...product};
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Autor Atualizado com sucesso', life: 3000 });

      });*/

  }


  save(autor: any) {
    this.submitted = true;
    this.autorService.salvar(autor)
      .then(autorSalvo => {
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Autor Cadastrado com sucesso', life: 3000 });
        this.autorDialog = false;
        this.listAutores();
      });
  }

  hideDialog() {
    this.autorDialog = false;
    this.submitted = false;
}
}

