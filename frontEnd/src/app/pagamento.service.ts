import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})

export class PagamentoService {
  readonly apiURL: string;

  constructor(private http: HttpClient, private messageService: MessageService) {
    this.apiURL = 'http://localhost:8080/pagamento';
  }

  listAll(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(categoria: any): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, categoria)
      .toPromise()
      .then(response => response.content)

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(categoria: any): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${categoria.id}`, categoria)
      .toPromise()
      .then(response => response.content)
      .catch(erro => {
        return Promise.reject(`Erro ao alterar categoria ${categoria.id}.`);
      });
  }

}
