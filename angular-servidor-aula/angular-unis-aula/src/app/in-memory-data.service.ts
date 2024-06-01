import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Universidad } from './universidad';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const universidades = [
      {
        "id": 99,
        "name": "Universidad Primera",
        "ciudad": "Madrid",
        "image": "url"
      },
      {
        "id": 100,
        "name": "Universidad Segunda",
        "ciudad": "Madrid",
        "image": "url"
      },
      {
        "id": 101,
        "name": "Universidad Tercera",
        "ciudad": "Madrid",
        "image": "url"
      } 
    ];
    return {universidades};
  }

  genId(universidades: Universidad[]): number {
    return universidades.length > 0 ? Math.max(...universidades.map(uni => uni.id)) + 1 : 11;
  }
}
