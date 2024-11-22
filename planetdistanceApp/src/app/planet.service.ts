import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map, Observable } from "rxjs";
import { Planet } from "./planet";
import { environment } from "../environments/environment.development";

@Injectable({providedIn: 'root'})
export class PlanetService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getPlanets(): Observable<Planet[]> {
        const planetNames: Observable<string[]> = this.http.get<string[]>(`${this.apiServerUrl}/planet/all`);
        const planets: Observable<Planet[]> = planetNames.pipe(
            map((stringArray: string[]) => 
              stringArray.map((planetName: string) => ({ id: 0, name: planetName, imageUrl: "" }))
            )
          );
        return planets
    }
}