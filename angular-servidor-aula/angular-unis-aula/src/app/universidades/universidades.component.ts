import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent implements OnInit {

    uniSeleccionada?: Universidad;

    universidades : Universidad[] = [];

    constructor(private universidadService: UniversidadService, private messageService: MessageService) {}

    ngOnInit(): void {
      this.getUnis();
    }

    getUnis(): void {
      this.universidadService.getUnis().subscribe(
        unis => this.universidades = unis);
    }

    onSelect(universidad: Universidad): void {
     this.uniSeleccionada = universidad;
     this.messageService.add(`UniversidadesComponent: Universidad seleccionada id=${universidad.id}`);
  }
  
}