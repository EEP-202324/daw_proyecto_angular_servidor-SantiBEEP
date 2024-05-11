import { CommonModule } from '@angular/common';
import { Component, inject  } from '@angular/core';
import { UniLocationComponent } from '../uni-location/uni-location.component';
import { UniLocation } from '../uni-location';
import { UniService } from '../uni.service';

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
      <input type="text" placeholder="Filtrar por Universidades" #filter>
      <button class="primary" type="button" 
      (click)="filterResults(filter.value)">Buscar</button>
    </form>
  </section>
  <section class="results">
  <app-uni-location *ngFor="let uniLocation of filteredLocationList"
  [uniLocation]="uniLocation"></app-uni-location>
  </section>
`,
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  uniLocationList: UniLocation[] = [];
  uniService: UniService = inject(UniService);
  filteredLocationList: UniLocation[] = [];

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.uniLocationList;
      return;
    }
  
    this.filteredLocationList = this.uniLocationList.filter(
      uniLocation => uniLocation?.city.toLowerCase().includes(text.toLowerCase())
    );
  }

constructor() {
  this.uniLocationList = this.uniService.getAllUniLocations();
  this.filteredLocationList = this.uniLocationList;
}
}
