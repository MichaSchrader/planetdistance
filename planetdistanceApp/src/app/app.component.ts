// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule directly
import { RouterOutlet } from '@angular/router';
import { Planet } from './planet';
import { PlanetService } from './planet.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

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
  
  constructor(private planetService: PlanetService){}

  
  ngOnInit(): void {
    this.getPlanets();
  }
  
  public getPlanets(): void {
    this.planetService.getPlanets().subscribe(
      (response: Planet[]) => {
        this.planets = response;
        this.selectedPlanet1 = this.planets[0];
        this.selectedPlanet2 = this.planets[this.planets.length];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  calculateDistance() {
    // Placeholder for actual distance calculation
    if (this.selectedDate && this.selectedPlanet1 && this.selectedPlanet2) {
      const randomDistance = (Math.random() * 1000).toFixed(2);
      this.distance = `${randomDistance} million km`;
    } else {
      this.distance = 'Please select both planets and enter a date.';
    }
  }
}
