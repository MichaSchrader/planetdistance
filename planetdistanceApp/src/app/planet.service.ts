import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Planet } from "./planet";
import { environment } from "../environments/environment.development";

@Injectable({providedIn: 'root'})
export class PlanetService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getPlanets(): Observable<Planet[]> {
        return this.http.get<Planet[]>(`${this.apiServerUrl}/planet/all`);
    }

    public addPlanet(planet: Planet): Observable<Planet> {
        return this.http.post<Planet>(`${this.apiServerUrl}/planet/add`, planet);
    }

    public updatePlanet(planet: Planet): Observable<Planet> {
        return this.http.put<Planet>(`${this.apiServerUrl}/planet/update`, planet);
    }

    
    public deletePlanet(planetId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/planet/delete${planetId}`);
    }
}