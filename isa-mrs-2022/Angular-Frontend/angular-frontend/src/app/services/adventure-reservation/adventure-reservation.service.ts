import { AdventureReservationCalendarDTO } from './../../models/response/http-adventure-response/AdventureReservationCalendarDTO';
import { DeleteAdventureReservationDTO } from './../../models/response/entity-delete/adventure-delete';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { SaveAdventureReservationDTO } from 'src/app/models/response/http-adventure-response/SaveAdventureDTO';

@Injectable({
  providedIn: 'root'
})
export class AdventureReservationService {

  public apiUrl: string = "http://localhost:8080/myuser/reservationAdventure";


  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public deleteAdventure(dto: DeleteAdventureReservationDTO): Observable<Boolean> {

    return this.http.post<Boolean>(`${this.apiUrl}/delete`, dto, { headers: this.headers });
  }

  public getAllAdventureReservations(id: number): Observable<AdventureReservationCalendarDTO> {

    return this.http.get<AdventureReservationCalendarDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public saveAdventureReservation(dto: SaveAdventureReservationDTO): Observable<Boolean> {

    return this.http.post<Boolean>(`${this.apiUrl}`, dto, { headers: this.headers });
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
