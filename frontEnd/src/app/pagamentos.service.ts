import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';


@Injectable({
  providedIn: 'root'
})
export class PagamentosService {
  readonly apiURL: string;

  constructor(private http: HttpClient, private messageService: MessageService,) {
    this.apiURL = 'http://localhost:8080/pagamento';
  }

  listAll(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(pagamento: any): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, pagamento)
      .toPromise()
      .then(response => response.content)

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(pagamento: any): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${pagamento.id}`, pagamento)
      .toPromise()
      .then(response => response.content)
      .catch(erro => {
        return Promise.reject(`Erro ao alterar pagamento ${pagamento.id}.`);
      });
  }

}
