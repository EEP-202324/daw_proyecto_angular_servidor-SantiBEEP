import { Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import { UniLocation } from '../uni-location';

@Component({
  selector: 'app-uni-location',
  standalone: true,
  imports: [],
  template: `
  <section class="listing">
    <img class="listing-photo" [src]="uniLocation.photo" alt="Exterior photo of {{uniLocation.name}}">
    <h2 class="listing-heading">{{ uniLocation.name }}</h2>
    <p class="listing-location">{{ uniLocation.city}}</p>
  </section>
  `,
  styleUrl: './uni-location.component.css'
})
export class UniLocationComponent {
  @Input() uniLocation!: UniLocation;
}
