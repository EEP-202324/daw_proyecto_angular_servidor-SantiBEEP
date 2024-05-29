import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { Observable, Subject } from 'rxjs';
import { UniversidadService } from '../universidad.service';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

@Component({
  selector: 'app-buscar-universidad',
  templateUrl: './buscar-universidad.component.html',
  styleUrl: './buscar-universidad.component.css'
})
export class BuscarUniversidadComponent implements OnInit {

  universidades$!: Observable<Universidad[]>;
  
  private searchTerms = new Subject<string>();

  constructor(private universidadService: UniversidadService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.universidades$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.universidadService.searchUnis(term)),
    );
  }
}
