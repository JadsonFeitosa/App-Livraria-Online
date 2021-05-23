import { PagamentoService } from './../pagamento.service';
import { PagamentosService } from './../pagamentos.service';
import { MessageService, ConfirmationService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css']
})
export class CheckoutFormComponent implements OnInit {

  telaDialog = false;

  usuario = { cpf: '', nome: '', userID: '' };

  checkouts = [];

  checkout = { pagamentoId: '', descricao: '' };

  endereco = {
    endID: '', cep: '', rua: '', numero: '', bairro: '', cidade: '', UF: '',
    complemento: '', pontoReferencia: '', userID: ''
  };

  selectedpagamentos = null;

  total = 0;

  submitted = false;

  formaDePagamento: any[] = [{ name: 'Credito', key: 'C' },
  { name: 'Debto', key: 'D' },
  { name: 'Boleto', key: 'B' },
  { name: 'Paypal', key: 'P' }];

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private pagamentoService: PagamentoService,
    private route: ActivatedRoute,
    private router: Router,

  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(parametros => this.total = +parametros['total']);
    this.selectedpagamentos = this.formaDePagamento[1];
  }


  listAll() {
    this.pagamentoService.listAll().then(pagamentos => {
      this.checkouts = pagamentos;
    });
  }
  finalizarPedido(){
    this.router.navigate(['/compra']);

   }
  // save(checkout: any) {
  //   this.submitted = true;
  //   this.checkoutService.salvar(checkout)
  // .then(0 => {
  // if (checkout.pagamentoId === '') {
  //   this.messageService.add({ severity: 'success', summary: 'Pagamento', detail: ' Cadastrado com sucesso', life: 3000 });
  // } else {
  //   this.messageService.add({ severity: 'success', summary: 'Pagamento', detail: 'Atualizado com sucesso', life: 3000 });
  // }
  // this.hideDialog();
  // this.listAll();
  // });
  // }
}
