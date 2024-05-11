import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UniService } from '../uni.service';
import { UniLocation } from '../uni-location';
import { FormControl, FormGroup, ReactiveFormsModule } 
from '@angular/forms';

@Component({
  selector: 'app-detalles',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
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
  <section class="listing-apply">
  <h2 class="section-heading">Entra en esta Universidad</h2>
  <form [formGroup]="applyForm" (submit)="submitApplication()">
    <label for="first-name">Nombre</label>
    <input id="first-name" type="text" formControlName="firstName">

    <label for="last-name">Apellidos</label>
    <input id="last-name" type="text" formControlName="lastName">

    <label for="email">Email</label>
    <input id="email" type="email" formControlName="email">
    <button type="submit" class="primary">Enviar Solicitud</button>
  </form>
  </section>
</article>
  `,
  styleUrl: './detalles.component.css'
})
export class DetallesComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  uniService = inject(UniService);
  uniLocation: UniLocation | undefined;
  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl('')
  });

  constructor() {
    const housingLocationId = Number(this.route.snapshot.params['id']);
    this.uniLocation = this.uniService.getUniLocationById(housingLocationId);
  }
  submitApplication() {
    this.uniService.submitApplication(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? ''
    );
  }
}
