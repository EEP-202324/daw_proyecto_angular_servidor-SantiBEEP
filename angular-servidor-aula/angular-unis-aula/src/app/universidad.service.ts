import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { UNIVERSIDADES } from './mock-universidades';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class UniversidadService {

  private urlUnis = 'api/universidades';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getUnis(): Observable<Universidad[]> {
    return this.http.get<Universidad[]>(this.urlUnis).
    pipe(
      tap(_ => this.log('universidades recuperadas')),
      catchError(this.handleError<Universidad[]>('getUnis', []))
  );
  }

  getUni(id: number): Observable<Universidad> {
    const url = `${this.urlUnis}/${id}`;
    this.messageService.add(`UniversidadService: Uni recuperada id=${id}`);
    return this.http.get<Universidad>(url).pipe(
      tap(_ => this.log(`uni recuperada id=${id}`)),
      catchError(this.handleError<Universidad>(`getUni id=${id}`))
    );
  }

  private log(message: string) {
    this.messageService.add(`UniversidadService:${message}`)
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  
}
