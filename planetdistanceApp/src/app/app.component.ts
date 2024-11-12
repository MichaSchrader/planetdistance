import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Planet } from './planet';
import { PlanetService } from './planet.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'planetdistanceApp';
  public planets!: Planet[];
  
  constructor(private planetService: PlanetService){}

  ngOnInit(): void {
    this.getPlanets();
  }

  public getPlanets(): void {
    this.planetService.getPlanets().subscribe(
      (response: Planet[]) => {
        this.planets = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
