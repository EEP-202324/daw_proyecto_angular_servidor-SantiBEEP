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

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getUnis(): Observable<Universidad[]> {
    return this.http.get<Universidad[]>(this.urlUnis).
    pipe(
      tap(_ => this.log('Universidades recuperadas')),
      catchError(this.handleError<Universidad[]>('getUnis', []))
  );
  }

  getUni(id: number): Observable<Universidad> {
    const url = `${this.urlUnis}/${id}`;
    return this.http.get<Universidad>(url).pipe(
      tap(_ => this.log(`Univerisdad recuperada id=${id}`)),
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

  updateUni(universidad: Universidad): Observable<any> {
    return this.http.put(this.urlUnis, universidad, this.httpOptions).pipe(
      tap(_ => this.log(`Uni actualizada id=${universidad.id}`)),
      catchError(this.handleError<any>('updateUni'))
    );
  }

  addUni(uni: Universidad): Observable<Universidad> {
    return this.http.post<Universidad>(this.urlUnis, uni, this.httpOptions).pipe(
      tap((newUni: Universidad) => this.log(`Universidad Añadida w/ id=${newUni.id}`)),
      catchError(this.handleError<Universidad>('addUni'))
    );
  }
  
  deleteUni(id: number): Observable<Universidad> {
    const url = `${this.urlUnis}/${id}`;
    return this.http.delete<Universidad>(url, this.httpOptions).pipe(
      tap(_ => this.log(`Universidad Eliminada id=${id}`)),
      catchError(this.handleError<Universidad>('deleteUni'))
    );
  }
}
