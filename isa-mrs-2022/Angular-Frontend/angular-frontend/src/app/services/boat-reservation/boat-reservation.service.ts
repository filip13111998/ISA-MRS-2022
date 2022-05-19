import { DeleteBoatReservationDTO } from './../../models/response/entity-delete/boat-delete';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { BoatReservationCalendarDTO } from 'src/app/models/response/http-boat-response/BoatReservationCalendarDTO';
import { SaveBoatReservationDTO } from 'src/app/models/response/http-boat-response/SaveBoatReservationDTO';

@Injectable({
  providedIn: 'root'
})
export class BoatReservationService {

  public apiUrl: string = "http://localhost:8080/myuser/reservationBoat";


  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public deleteBoat(dto: DeleteBoatReservationDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/delete`, dto, { headers: this.headers });
  }

  public getAllBoatReservations(id: number): Observable<BoatReservationCalendarDTO> {

    return this.http.get<BoatReservationCalendarDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public saveBoatReservation(dto: SaveBoatReservationDTO): Observable<Boolean> {

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
