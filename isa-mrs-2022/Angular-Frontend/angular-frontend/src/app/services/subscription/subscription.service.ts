import { SubscribeDTO } from './../../models/subscription/subscribe';
import { CottageDTO } from './../../models/response/http-cottage-response/cottage-dto';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { AdventureDTO } from 'src/app/models/response/http-adventure-response/adventure-dto';
import { BoatDTO } from 'src/app/models/response/http-boat-response/boat-dto';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  // public apiUrl: string = "http://localhost:8080/cottage?pageNum=0&pageSize=9";
  public apiUrl: string = "http://localhost:8080/myuser/sub";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public unsubCotages(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/unsubCottage`, sdto, { headers: this.headers });
  }
  public unsubBoat(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/unsubBoat`, sdto, { headers: this.headers });
  }
  public unsubAdventure(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/unsubAdventure`, sdto, { headers: this.headers });
  }

  public subCotages(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/subCottage`, sdto, { headers: this.headers });
  }
  public subBoat(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/subBoat`, sdto, { headers: this.headers });
  }
  public subAdventure(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/subAdventure`, sdto, { headers: this.headers });
  }


  public isSubCotages(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/isSubCottage`, sdto, { headers: this.headers });
  }
  public isSubBoat(sdto: SubscribeDTO): Observable<Boolean> {
    console.log("IDE POZIV")
    return this.http.post<Boolean>(`${this.apiUrl}/isSubBoat`, sdto, { headers: this.headers });
  }
  public isSubAdventure(sdto: SubscribeDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/isSubAdventure`, sdto, { headers: this.headers });
  }


  public getSubCotages(username: string): Observable<CottageDTO[]> {
    return this.http.get<CottageDTO[]>(`${this.apiUrl}/subCottage/${username}`, { headers: this.headers });
  }

  public getSubBoats(username: string): Observable<BoatDTO[]> {
    return this.http.get<BoatDTO[]>(`${this.apiUrl}/subBoat/${username}`, { headers: this.headers });
  }


  public getSubAdventures(username: string): Observable<AdventureDTO[]> {
    return this.http.get<AdventureDTO[]>(`${this.apiUrl}/subAdventure/${username}`, { headers: this.headers });
  }


  error(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
