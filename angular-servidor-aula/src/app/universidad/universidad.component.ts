import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UniLocationComponent } from '../uni-location/uni-location.component';
import { UniLocation } from '../uni-location';

@Component({
  selector: 'app-universidad',
  standalone: true,
  imports: [
    CommonModule,
    UniLocationComponent],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filtrar por Universidades">
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
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  housingLocation: UniLocation = {
    id: 9999,
    name: 'Test Home',
    city: 'Test city',
    photo: `${this.baseUrl}/example-house.jpg`,
  };
}
