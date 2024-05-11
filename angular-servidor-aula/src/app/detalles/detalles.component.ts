import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UniService } from '../uni.service';
import { UniLocation } from '../uni-location';

@Component({
  selector: 'app-detalles',
  standalone: true,
  imports: [],
  template: `
  <article>
  <img class="listing-photo" [src]="uniLocation?.photo"
    alt="Exterior photo of {{uniLocation?.name}}"/>
  <section class="listing-description">
    <h2 class="listing-heading">{{uniLocation?.name}}</h2>
    <p class="listing-location">{{uniLocation?.city}}</p>
  </section>
  <section class="listing-features">
    <h2 class="section-heading">Sobre esta Universidad</h2>
    <ul>
      <li>¡Por hacer!</li>
    </ul>
  </section>
</article>
  `,
  styleUrl: './detalles.component.css'
})
export class DetallesComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  uniService = inject(UniService);
  uniLocation: UniLocation | undefined;

  constructor() {
    const housingLocationId = Number(this.route.snapshot.params['id']);
    this.uniLocation = this.uniService.getUniLocationById(housingLocationId);
  }
}
