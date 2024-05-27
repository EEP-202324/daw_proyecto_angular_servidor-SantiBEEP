import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UniversidadesComponent } from './universidades/universidades.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, UniversidadesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Universidades Aula';
}
