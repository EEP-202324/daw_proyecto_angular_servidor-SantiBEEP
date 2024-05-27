import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { UNIVERSIDADES } from './mock-universidades';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  constructor() { }

  getUnis(): Observable<Universidad[]> {
    const unis: Observable<Universidad[]> = of(UNIVERSIDADES)
    return unis;
  }
}
