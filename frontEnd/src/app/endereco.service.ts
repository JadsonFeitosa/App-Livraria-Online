import { MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {

  readonly apiURL: string;

  constructor(private http: HttpClient, private messageService: MessageService,) {
    this.apiURL = 'http://localhost:8080/endereco';
  }

  listAll(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(endereco: any): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, endereco)
      .toPromise()
      .then(response => response.content)

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(endereco: any): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${endereco.id}`, endereco)
      .toPromise()
      .then(response => response.content)
      .catch(erro => {
        return Promise.reject(`Erro ao alterar endereco ${endereco.id}.`);
      });
  }

}
