import { Component } from '@angular/core';

@Component({
  selector: 'app-universidad',
  standalone: true,
  imports: [],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filtrar Universidades">
      <button class="primary" type="button">Buscar</button>
    </form>
  </section>
`,
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {

}
