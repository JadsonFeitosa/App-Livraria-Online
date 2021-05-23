import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-messagem-compra',
  templateUrl: './messagem-compra.component.html',
  styleUrls: ['./messagem-compra.component.css']
})
export class MessagemCompraComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  voltar() {
    this.router.navigate(['/home/1']);
  }

}
