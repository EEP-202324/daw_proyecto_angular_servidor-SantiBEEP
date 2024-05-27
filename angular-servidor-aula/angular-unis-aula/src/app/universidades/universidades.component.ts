import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {
    universidades : Universidad[] = [];
    uniSeleccionada?: Universidad;

    getHeroes(): void {
      this.universidadService.getUnis().subscribe(
        unisRecibidas => this.universidades = unisRecibidas
      );
    }

    onSelect(universidad: Universidad): void {
     this.uniSeleccionada = universidad;
  }
  constructor(private universidadService: UniversidadService) {

  }
}