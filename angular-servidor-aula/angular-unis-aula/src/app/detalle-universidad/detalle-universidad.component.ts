import { Component, Input } from '@angular/core';
import { Universidad } from '../universidad';

@Component({
  selector: 'app-detalle-universidad',
  templateUrl: './detalle-universidad.component.html',
  styleUrl: './detalle-universidad.component.css'
})
export class DetalleUniversidadComponent {
  @Input() universidad?: Universidad;
}
