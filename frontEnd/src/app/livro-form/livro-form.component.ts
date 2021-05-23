import { EditoraService } from './../editora.service';
import { CategoriaService } from './../categoria.service';
import { AutorService } from './../autor.service';
import { LivroService } from './../livro.service';
import { Component, OnInit, ɵisListLikeIterable } from '@angular/core';
import { MessageService, ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-livro-form',
  templateUrl: './livro-form.component.html',
  styleUrls: ['./livro-form.component.css']
})


export class LivroFormComponent implements OnInit {
  telaDialog = false;

  products = [];

  categorias = [];

  editoras = [];

  editora = { editoraId: 0, nome: '' };

  autor = { autorId: 0, nome: '' };

  categoria = { categoriaId: 0, descricao: '' };

  autores = [];

  selectedAutores = [this.autor];



  product = {
    livroId: 0,
    titulo: '',
    descricao: '',
    price: '',
    isbn: '',
    capa: '',
    edicao: '',
    ano: '',
    anoPublicacao: '',
    categoria: this.categoria,
    editora: this.editora,
    estoque: {livroid: {livroId: 0}, quantidade: 0, prateleira: '' },
    listAutor: []
  };

  selectedProducts = [];

  submitted = false;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private livroService: LivroService,
    private autorService: AutorService,
    private categoriaService: CategoriaService,
    private editoraService: EditoraService) {

  }
  ngOnInit() {
    //this.productService.getProducts().then();
    this.listAll();

  }

  openNew() {
    this.product = this.product;
    this.listaNomesAutor();
    this.listaCategoria();
    this.listaEditora();
    this.submitted = false;
    this.telaDialog = true;
  }

  listAll() {
    this.livroService.listAll().then(livros => {
      this.products = livros;
    });
  }

  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir os produtos selecionados?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.products = this.products.filter(val => !this.selectedProducts.includes(val));
        this.selectedProducts = [];
      }
    });
  }

  // tslint:disable-next-line: typedef
  update(product: any) {
    this.product = { ...product };
    this.telaDialog = true;
  }

  delete(id: number, nome: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza de que deseja excluir' + nome + '?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.livroService.delete(id)
          .then(() => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Livro' + nome + 'Deletado com sucesso',
              life: 3000
            });
            this.listAll();
          });

      }
    });
  }

  hideDialog() {
    this.telaDialog = false;
    this.submitted = false;
  }

  save(product: any) {
    this.submitted = true;
    this.livroService.salvar(product)
      .then(productSalvo => {
        if (product.pagamentoId === '') {
          this.messageService.add({ severity: 'success', summary: 'Livro', detail: 'Cadastrado com sucesso', life: 3000 });
        } else {
          this.messageService.add({ severity: 'success', summary: 'Livro', detail: 'Atualizado com sucesso', life: 3000 });
        }
        this.hideDialog();
        this.listAll();
      });
  }

  listaNomesAutor() {
    this.autorService.listAutores().then(autores => {
      this.autores = autores;
    });
  }

  listaCategoria() {
    this.categoriaService.listAll().then((result) => {
      this.categorias = result;
    }).catch((err) => {

    });

  }

  listaEditora() {
    this.editoraService.listaNomes().then((result) => {
      this.editoras = result;
    }).catch((err) => {

    });

  }
}


