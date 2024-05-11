import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UniversidadComponent } from './universidad/universidad.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule,
     UniversidadComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Universidades';
}
