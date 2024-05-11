import { Component, Input} from '@angular/core';
import { UniLocation } from '../uni-location';
import { RouterLink } from '@angular/router';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-uni-location',
  standalone: true,
  imports: [RouterLink,
    RouterOutlet
  ],
  template: `
  <section class="listing">
    <img class="listing-photo" [src]="uniLocation.photo" alt="Exterior photo of {{uniLocation.name}}">
    <h2 class="listing-heading">{{ uniLocation.name }}</h2>
    <p class="listing-location">{{ uniLocation.city}}</p>
    <a [routerLink]="['/detalles', uniLocation.id]">Saber más</a>
  </section>
  `,
  styleUrl: './uni-location.component.css'
})
export class UniLocationComponent {
  @Input() uniLocation!: UniLocation;
}
