import { Injectable } from '@angular/core';
import { UniLocation } from './uni-location';

@Injectable({
  providedIn: 'root'
})
export class UniService {
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
  getAllUniLocations(): UniLocation[] {
    return this.uniLocationList;
  }
  
  getUniLocationById(id: number): UniLocation | undefined {
    return this.uniLocationList.find(uniLocation => uniLocation.id === id);
  }
}
