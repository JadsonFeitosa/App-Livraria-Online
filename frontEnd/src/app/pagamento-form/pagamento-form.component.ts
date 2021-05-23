import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService } from 'primeng/api';
import { PagamentoService } from '../pagamento.service';

@Component({
  selector: 'app-pagamento-form',
  templateUrl: './pagamento-form.component.html',
  styleUrls: ['./pagamento-form.component.css']
})
export class PagamentoFormComponent implements OnInit {

  telaDialog = false;

  pagamentos = [];

  pagamento = {pagamentoId : '', descricao : ''};

  selectedpagamentos = [];

  submitted = false;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private pagamentoService: PagamentoService) {


  }


  ngOnInit(){
    this.listAll();
  }

  openNew() {
    this.pagamento = {pagamentoId : '', descricao : ''};
    this.submitted = false;
    this.telaDialog = true;
  }

  listAll() {
    this.pagamentoService.listAll().then(pagamentos => {
      this.pagamentos = pagamentos;
    });
  }


  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir os pagamentos selecionados?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
       // this.pagamentos = this.pagamentos.filter(val => !this.selectedpagamentos.includes(val));
       // this.pagamentos = [];
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Pagamento Deleted', life: 3000 });
      }
    });
  }


  delete(id: number, pagamento: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir pagamento ' + pagamento + '?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.pagamentoService.delete(id)
          .then(() => {
            this.messageService.add({ severity: 'warning', summary: 'Pagamento', detail: 'Deletado com sucesso', life: 3000 });
            this.listAll();
          });
      }
    });
  }


  update(pagamento: any) {
    this.pagamento = {...pagamento};
    this.telaDialog = true;
  }

  save(pagamento: any) {
    this.submitted = true;
    this.pagamentoService.salvar(pagamento)
      .then(pagamentoSalvo => {
        if (pagamento.pagamentoId === '') {
          this.messageService.add({ severity: 'success', summary: 'Pagamento', detail: ' Cadastrado com sucesso', life: 3000 });
        } else {
          this.messageService.add({ severity: 'success', summary: 'Pagamento', detail: 'Atualizado com sucesso', life: 3000 });
        }
        this.hideDialog();
        this.listAll();
      });
  }

  hideDialog() {
    this.telaDialog = false;
    this.submitted = false;
}
}
