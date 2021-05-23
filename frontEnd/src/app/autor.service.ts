import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AutorService {

  readonly apiURL: string;

  constructor(private http: HttpClient, private messageService: MessageService,) {
    this.apiURL = 'http://localhost:8080/autor';
  }

  listAutores(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(autor: any): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, autor)
      .toPromise()
      .then(response => response.content)

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(autor: any): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${autor.id}`, autor)
      .toPromise()
      .then(response => response.content)
      .catch(erro => {
        return Promise.reject(`Erro ao alterar autor ${autor.id}.`);
      });
  }

  listaNomesAutor(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}/listaNomes`)
    .toPromise()
    .then(response => response.content);
  }

}
