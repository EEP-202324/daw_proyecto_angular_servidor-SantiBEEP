import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UniLocationComponent } from '../uni-location/uni-location.component';
import { UniLocation } from '../uni-location';

@Component({
  selector: 'app-universidad',
  standalone: true,
  imports: [
    CommonModule,
    UniLocationComponent
    ],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filtrar por Universidades">
      <button class="primary" type="button">Buscar</button>
    </form>
  </section>
  <section class="results">
  <app-uni-location *ngFor="let uniLocation of uniLocationList"
  [uniLocation]="uniLocation"></app-uni-location>
  </section>
`,
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  uniLocationList: UniLocation[] = [
    {
      id: 0,
      name: 'Acme Fresh Start Housing',
      city: 'Madrid',
      photo: `${this.baseUrl}/bernard-hermant-CLKGGwIBTaY-unsplash.jpg`,
    },
    {
      id: 1,
      name: 'A113 Transitional Housing',
      city: 'Madrid',
      photo: `${this.baseUrl}/brandon-griggs-wR11KBaB86U-unsplash.jpg`,
    },
    {
      id: 2,
      name: 'Warm Beds Housing Support',
      city: 'Madrid',
      photo: `${this.baseUrl}/i-do-nothing-but-love-lAyXdl1-Wmc-unsplash.jpg`,
    },
    {
      id: 3,
      name: 'Homesteady Housing',
      city: 'Madrid',
      photo: `${this.baseUrl}/ian-macdonald-W8z6aiwfi1E-unsplash.jpg`,
    },
    {
      id: 4,
      name: 'Happy Homes Group',
      city: 'Madrid',
      photo: `${this.baseUrl}/krzysztof-hepner-978RAXoXnH4-unsplash.jpg`,
    },
    {
      id: 5,
      name: 'Hopeful Apartment Group',
      city: 'Madrid',
      photo: `${this.baseUrl}/r-architecture-JvQ0Q5IkeMM-unsplash.jpg`,
    },
    {
      id: 6,
      name: 'Seriously Safe Towns',
      city: 'Madrid',
      photo: `${this.baseUrl}/phil-hearing-IYfp2Ixe9nM-unsplash.jpg`,
    },
    {
      id: 7,
      name: 'Hopeful Housing Solutions',
      city: 'Madrid',
      photo: `${this.baseUrl}/r-architecture-GGupkreKwxA-unsplash.jpg`,
    },
    {
      id: 8,
      name: 'Seriously Safe Towns',
      city: 'Madrid',
      photo: `${this.baseUrl}/saru-robert-9rP3mxf8qWI-unsplash.jpg`,
    },
    {
      id: 9,
      name: 'Capital Safe Towns',
      city: 'Madrid',
      photo: `${this.baseUrl}/webaliser-_TPTXZd9mOo-unsplash.jpg`,
    }
  ];
}
