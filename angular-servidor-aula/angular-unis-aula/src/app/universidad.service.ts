import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { UNIVERSIDADES } from './mock-universidades';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})

export class UniversidadService {

  constructor(private messageService: MessageService) { }

  getUnis(): Observable<Universidad[]> {
    const unis = of(UNIVERSIDADES)
    this.messageService.add("UniversidadService: Universidades recuperadas");
    return unis;
  }

  getUni(id: number): Observable<Universidad> {
    const uni = UNIVERSIDADES.find(u => u.id === id)!;
    this.messageService.add(`UniversidadService: Uni recuperada id=${id}`);
    return of(uni);
  }
}
