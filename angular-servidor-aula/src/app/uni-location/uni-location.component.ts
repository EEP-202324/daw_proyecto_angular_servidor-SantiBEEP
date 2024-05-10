import { Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import { UniLocation } from '../uni-location';

@Component({
  selector: 'app-uni-location',
  standalone: true,
  imports: [],
  template: `
    <p>
      uni-location works!
    </p>
  `,
  styleUrl: './uni-location.component.css'
})
export class UniLocationComponent {
  @Input() uniLocation!: UniLocation;
}
