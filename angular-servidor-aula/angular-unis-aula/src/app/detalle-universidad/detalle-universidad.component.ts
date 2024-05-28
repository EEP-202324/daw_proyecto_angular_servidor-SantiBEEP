import { Component, Input } from '@angular/core';
import { Universidad } from '../universidad';
import { ActivatedRoute } from '@angular/router';
import { UniversidadService } from '../universidad.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-detalle-universidad',
  templateUrl: './detalle-universidad.component.html',
  styleUrl: './detalle-universidad.component.css'
})
export class DetalleUniversidadComponent {
  @Input() universidad?: Universidad;

  constructor(
    private route: ActivatedRoute,
    private universidadService: UniversidadService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getUni();
  }
  
  getUni(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.universidadService.getUni(id)
    .subscribe(universidad => this.universidad = universidad);
  }

  goBack(): void {
    this.location.back();
  }
}
