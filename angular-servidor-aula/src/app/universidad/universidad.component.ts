import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UniLocationComponent } from '../uni-location/uni-location.component';

@Component({
  selector: 'app-universidad',
  standalone: true,
  imports: [
    CommonModule,
    UniLocationComponent],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filtrar Universidades">
      <button class="primary" type="button">Buscar</button>
    </form>
  </section>
  <section class="results">
  <app-uni-location></app-uni-location>
  </section>
`,
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {

}
