import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversidadesComponent } from './universidades/universidades.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DetalleUniversidadComponent } from './detalle-universidad/detalle-universidad.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detalles/:id', component: DetalleUniversidadComponent },
  { path: 'universidades', component: UniversidadesComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
