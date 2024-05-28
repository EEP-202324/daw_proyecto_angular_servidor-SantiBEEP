import { Component } from '@angular/core';
import { UniversidadService } from '../universidad.service';
import { Universidad } from '../universidad';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  universidades: Universidad[] = [];

  constructor(private universidadService: UniversidadService) {}

  ngOnInit(): void {
    this.getUnis();
  }

  getUnis(): void {
    this.universidadService.getUnis().subscribe(
      unis => this.universidades = unis.slice(1, 5));
  }
}
