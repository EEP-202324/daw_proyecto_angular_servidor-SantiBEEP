import { Injectable } from '@angular/core';
import { UniLocation } from './uni-location';

@Injectable({
  providedIn: 'root'
})
export class UniService {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';
  url = 'http://localhost:3000/locations';

  async getAllUniLocations(): Promise<UniLocation[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }
  
  async getUniLocationById(id: number): Promise<UniLocation | undefined> {
    const data = await fetch(`${this.url}/${id}`);
    return await data.json() ?? {};
  }

  submitApplication(firstName: string, lastName: string, email: string) {
    console.log(`Formulario: /n firstName: ${firstName}, lastName: ${lastName}, email: ${email}.`);
  }
}
