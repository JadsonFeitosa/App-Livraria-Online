import { CarrinhoComponent } from './carrinho/carrinho.component';
import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorFormComponent } from './autor-form/autor-form.component';
import { CategoriaFormComponent } from './categoria-form/categoria-form.component';
import { CheckoutFormComponent } from './checkout-form/checkout-form.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';
import { EditoraFormComponent } from './editora-form/editora-form.component';
import { EnderecoFormComponent } from './endereco-form/endereco-form.component';
import { LivroCardComponent } from './livro-card/livro-card.component';
import { LivroFormComponent } from './livro-form/livro-form.component';
import { MessagemCompraComponent } from './messagem-compra/messagem-compra.component';
import { PagamentoFormComponent } from './pagamento-form/pagamento-form.component';
import { PedidoGeralComponent } from './pedido-geral/pedido-geral.component';
import { PortalAdminComponent } from './portal-admin/portal-admin.component';


const routes: Routes = [
  {path: '', redirectTo: '/home/1', pathMatch: 'full'},
  { path: 'home/:id', component: LivroCardComponent },
  { path: 'autor', component: AutorFormComponent },
  { path: 'livro', component: LivroFormComponent },
  { path: 'pagamento', component: PagamentoFormComponent },
  { path: 'categoria', component: CategoriaFormComponent },
  { path: 'endereco', component: EnderecoFormComponent },
  { path: 'admin', component: PortalAdminComponent },
  { path: 'editora', component: EditoraFormComponent },
  { path: 'detalhe-produto', component: DetalheProdutoComponent },
  { path: 'check/:total', component: CheckoutFormComponent },
  { path: 'pedido-geral', component: PedidoGeralComponent},
  { path: 'compra', component: MessagemCompraComponent},
  { path: 'carrinho/:id', component: CarrinhoComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
