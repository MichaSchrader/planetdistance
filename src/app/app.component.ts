// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule directly
import { RouterOutlet } from '@angular/router';
import { Planet } from './planet';
import { PlanetService } from './planet.service';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'planetdistanceApp';
  public planets!: Planet[];
  selectedPlanet1!: Planet;
  selectedPlanet2!: Planet;
  // List of planets in our solar system
  /*planets: string[] = [
    'Mercury',
    'Venus',
    'Earth',
    'Mars',
    'Jupiter',
    'Saturn',
    'Uranus',
    'Neptune'
  ];*/
  //selectedPlanet1: string = 'Earth';
  //selectedPlanet2: string = 'Mars';
  selectedDate: string = '';
  distance: string = '';
  
  constructor(private planetService: PlanetService, private http: HttpClient){}

  
  ngOnInit(): void {
    this.getPlanets();
  }
  
  public getPlanets(): void {
    this.planetService.getPlanets().subscribe(
      (response: Planet[]) => {
        this.planets = response;
        //this.selectedPlanet1 = this.planets[0];
        //this.selectedPlanet2 = this.planets[1];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  calculateDistance() {
    if (this.selectedDate && this.selectedPlanet1 && this.selectedPlanet2) {
      const time = new Date(this.selectedDate).toISOString()
      const firstPlanetName = this.selectedPlanet1.name
      const secondPlanetName = this.selectedPlanet2.name
      const apiUrl = environment.apiBaseUrl + `/planet/distance/${firstPlanetName}/${secondPlanetName}/${time}`;
      
      // Call the backend API
      this.http.get<number>(apiUrl).subscribe({
        next: (response) => {
          this.distance = `${response} km`;
        },
        error: (err) => {
          console.error(err);
          this.distance = 'Error calculating distance. Please try again.';
        }
      });
    } else {
      this.distance = 'Please select both planets and enter a valid date.';
    }
  }
}
