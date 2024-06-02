import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent implements OnInit {
  
    universidades : Universidad[] = [];

    constructor(private universidadService: UniversidadService, private messageService: MessageService) {}

    ngOnInit(): void {
      this.getUnis();
    }

    getUnis(): void {
      this.universidadService.getUnis().subscribe(
        universidades => this.universidades = universidades);
    }

    add(name: string): void {
      name = name.trim();
      if (!name) { return; }
      this.universidadService.addUni({ name } as Universidad)
        .subscribe(uni => {
          this.universidades.push(uni);
        });
    }

    delete(uni: Universidad): void {
      this.universidades = this.universidades.filter(h => h !== uni);
      this.universidadService.deleteUni(uni.id).subscribe();
    }
}