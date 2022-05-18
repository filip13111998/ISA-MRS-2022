import { AdventureReservationHistoryDTO } from './../../models/response/http-adventure-response/adventure-history-reservation';
import { BoatReservationHistoryDTO } from './../../models/response/http-boat-response/boat-history-reservation';
import { CottageReservationHistoryDTO } from './../../models/response/http-cottage-response/cottage-history-reservation';
import { MyUserDTO } from './../../models/response/my-user/my-user';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyUserService {

  public apiUrl: string = "http://localhost:8080/myuser/profile";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }


  public getMyUser(username: any): Observable<MyUserDTO> {
    return this.http.get<MyUserDTO>(`${this.apiUrl}/${username}`, { headers: this.headers });
  }
  public editMyUser(myUser: any): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}`, myUser, { headers: this.headers });
  }


  public getCottageHistoryReservation(username: any, pageNum: any): Observable<CottageReservationHistoryDTO[]> {
    return this.http.get<CottageReservationHistoryDTO[]>(`${this.apiUrl}/historyCottage/${username}?pageNum=${pageNum}`, { headers: this.headers });
  }
  public getBoatHistoryReservation(username: any, pageNum: any): Observable<BoatReservationHistoryDTO[]> {
    return this.http.get<BoatReservationHistoryDTO[]>(`${this.apiUrl}/historyBoat/${username}?pageNum=${pageNum}`, { headers: this.headers });
  }
  public getAdventureHistoryReservation(username: any, pageNum: any): Observable<AdventureReservationHistoryDTO[]> {
    return this.http.get<AdventureReservationHistoryDTO[]>(`${this.apiUrl}/historyAdventure/${username}?pageNum=${pageNum}`, { headers: this.headers });
  }

  public getCottageNotHeldReservation(username: any, pageNum: any): Observable<CottageReservationHistoryDTO[]> {
    return this.http.get<CottageReservationHistoryDTO[]>(`${this.apiUrl}/notHeldCottage/${username}?pageNum=${pageNum}`, { headers: this.headers });
  }
  public getBoatNotHeldReservation(username: any, pageNum: any): Observable<BoatReservationHistoryDTO[]> {
    return this.http.get<BoatReservationHistoryDTO[]>(`${this.apiUrl}/notHeldBoat/${username}?pageNum=${pageNum}`, { headers: this.headers });
  }
  public getAdventureNotHeldReservation(username: any, pageNum: any): Observable<AdventureReservationHistoryDTO[]> {
    return this.http.get<AdventureReservationHistoryDTO[]>(`${this.apiUrl}/notHeldAdventure/${username}?pageNum=${pageNum}`, { headers: this.headers });
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
