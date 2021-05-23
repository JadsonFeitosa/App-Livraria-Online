import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { EditoraService } from '../editora.service';

@Component({
  selector: 'app-editora-form',
  templateUrl: './editora-form.component.html',
  styleUrls: ['./editora-form.component.css']
})
export class EditoraFormComponent implements OnInit {

  telaDialog = false;

  submitted = false;

  editora = { editoraId: '', nome: '', cep: '', rua: '', numero: '',
   bairro: '', cidade: '', cnpj: '', uf: ''};

  editoras = [];

  selectEditoras = [];

  constructor(

    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private editoraService: EditoraService) {
  }



  ngOnInit() {
    this.listAll();

  }

  openNew() {
    this.submitted = false;
    this.telaDialog = true;
    this.editora = { editoraId: '', nome: '', cep: '', rua: '', numero: '',
   bairro: '', cidade: '', cnpj: '', uf: ''};
  }

  listAll() {
    this.editoraService.listEditora().then(editora => {
      this.editoras = editora;
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
        this.editoraService.delete(id)
          .then(() => {
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Editora Deletado com sucesso', life: 3000 });

          });
          this.listAll();
      }
    });
  }


  update(editora: any) {
    this.editora = { ...editora };
    this.telaDialog = true;

  }

  save(editora: any) {
    this.submitted = true;

    if (this.editora.editoraId === '') {
      this.editoraService.salvar(editora)
      .then(editoraSalvo => {
        this.messageService.add({ severity: 'success', summary: 'Editora', detail: 'Cadastrado com sucesso', life: 3000 });
        this.telaDialog = false;
        this.listAll();
      });
    }
    else{
      this.editoraService.update(editora)
      .then(editoraSalvo => {
        this.messageService.add({ severity: 'success', summary: 'Editora', detail: 'Atualizado com sucesso', life: 3000 });
        this.telaDialog = false;
        this.listAll();
      });
    }

  }

  hideDialog() {
    this.telaDialog = false;
    this.submitted = false;
  }

}
