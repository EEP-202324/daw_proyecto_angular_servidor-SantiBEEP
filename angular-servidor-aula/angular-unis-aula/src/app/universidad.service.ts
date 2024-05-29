import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { UNIVERSIDADES } from './mock-universidades';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UniversidadService {

  private urlUnis = 'api/universidades';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getUnis(): Observable<Universidad[]> {
    this.messageService.add("UniversidadService: Universidades recuperadas");
    return this.http.get<Universidad[]>(this.urlUnis);
  }

  getUni(id: number): Observable<Universidad> {
    const uni = UNIVERSIDADES.find(u => u.id === id)!;
    this.messageService.add(`UniversidadService: Uni recuperada id=${id}`);
    return of(uni);
  }

  private log(message: string) {
    this.messageService.add(`UniversidadService:${message}`)
  }
}
