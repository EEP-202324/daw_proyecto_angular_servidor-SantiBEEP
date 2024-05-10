import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UniversidadComponent } from './universidad/universidad.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, UniversidadComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Universidades';
}
