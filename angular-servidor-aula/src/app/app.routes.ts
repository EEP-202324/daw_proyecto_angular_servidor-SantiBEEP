import { Routes } from '@angular/router';
import { DetallesComponent } from './detalles/detalles.component';
import { UniversidadComponent } from './universidad/universidad.component';

const routeConfig: Routes = [
    {
      path: '',
      component: UniversidadComponent,
      title: 'Página de inicio'
    }, {
      path: 'detalles/:id',
      component: DetallesComponent,
      title: 'Detalles de la Universidad'
    }
  ];
export default routeConfig;
