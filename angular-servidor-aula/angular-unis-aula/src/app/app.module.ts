import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UniversidadesComponent } from './universidades/universidades.component';
import { FormsModule } from '@angular/forms';
import { DetalleUniversidadComponent } from './detalle-universidad/detalle-universidad.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { BuscarUniversidadComponent } from './buscar-universidad/buscar-universidad.component';

@NgModule({
  declarations: [
    AppComponent,
    UniversidadesComponent,
    DetalleUniversidadComponent,
    MessagesComponent,
    DashboardComponent,
    BuscarUniversidadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
