import { MessageService, ConfirmationService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { EnderecoService } from '../endereco.service';

@Component({
  selector: 'app-endereco-form',
  templateUrl: './endereco-form.component.html',
  styleUrls: ['./endereco-form.component.css']
})
export class EnderecoFormComponent implements OnInit {

  telaDialog = false;

  enderecos = [];

  endereco = {endID: '', cep: '', rua: '', numero: '', bairro: '', cidade: '', UF: '',
  complemento: '', pontoReferencia: '', userID: ''};

  selectedEnderecos = [];

  submitted = false;


  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private enderecoService: EnderecoService

  ) {
  }

  ngOnInit() {
    this.listEnderecos();
  }

  openNew() {
    this.endereco = {
      endID: '', cep: '', rua: '', numero: '', bairro: '', cidade: '', UF: '',
    complemento: '', pontoReferencia: '', userID: ''
    };
    this.submitted = false;
    this.telaDialog = true;
  }

  listEnderecos() {
    this.enderecoService.listAll().then(enderecos => {
      this.enderecos = enderecos;
    });
  }


  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir os produtos selecionados?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //    this.endereco = this.endereco.filter(val => !this.selectedEnderecos.includes(val));
        //   this.selectedProducts = null;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
      }
    });
  }


  delete(id: number) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir endereco ?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.enderecoService.delete(id)
          .then(() => {
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Endereco Deletado com sucesso', life: 3000 });
            this.listEnderecos();
          });

      }
    });
  }


  update(endereco: any) {
    this.endereco = { ...endereco };
    this.telaDialog = true;

    /* .then(() => {
       //this.product = {...product};
       this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Autor Atualizado com sucesso', life: 3000 });

     });*/

  }


  save(endereco: any) {
    this.submitted = true;
    this.enderecoService.salvar(endereco)
      .then(enderecoSalvo => {
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'endereco Cadastrado com sucesso', life: 3000 });
        this.telaDialog = false;
        this.listEnderecos();
      });
  }

  hideDialog() {
    this.telaDialog = false;
    this.submitted = false;
  }
}

