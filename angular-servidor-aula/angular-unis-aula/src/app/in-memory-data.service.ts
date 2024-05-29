import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Universidad } from './universidad';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const universidades = [
      { id: 12, name: 'UniA' },
      { id: 13, name: 'UniB' },
      { id: 14, name: 'UniC' },
      { id: 15, name: 'UniD' },
      { id: 16, name: 'UniE' },
      { id: 17, name: 'UniF' }
    ]
    return {universidades};
  }

  genId(universidades: Universidad[]): number {
    return universidades.length > 0 ? Math.max(...universidades.map(uni => uni.id)) + 1 : 11;
  }
}
