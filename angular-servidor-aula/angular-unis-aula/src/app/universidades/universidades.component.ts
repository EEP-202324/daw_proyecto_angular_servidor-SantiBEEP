import { Component } from '@angular/core';
import { UNIVERSIDADES } from '../mock-universidades';
import { Universidad } from '../universidad';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {
    universidades : Universidad[] = UNIVERSIDADES;
    uniSeleccionada?: Universidad;

    onSelect(universidad: Universidad): void {
     this.uniSeleccionada = universidad;
  }
}