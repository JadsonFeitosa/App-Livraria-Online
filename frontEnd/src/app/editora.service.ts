import { MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EditoraService {
  readonly apiURL: string;

  constructor(private http: HttpClient, private messageService: MessageService,) {
    this.apiURL = 'http://localhost:8080/editora';
  }

  listEditora(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}`)
      .toPromise()
      .then(response => response.content);
  }

  salvar(editora: any): Promise<any> {
    return this.http.post<any>(`${this.apiURL}`, editora)
      .toPromise()
      .then(response => response.content)

  }

  delete(id: number): Promise<any> {
    return this.http.delete(`${this.apiURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  update(editora: any): Promise<any> {
    return this.http.put<any>(`${this.apiURL}/${editora.editoraId}`, editora)
      .toPromise()
      .then(response => response.content);
  }

  listaNomes(): Promise<any> {
    return this.http.get<any>(`${this.apiURL}/lista-nomes`)
      .toPromise()
      .then(response => response.content);
  }


}
