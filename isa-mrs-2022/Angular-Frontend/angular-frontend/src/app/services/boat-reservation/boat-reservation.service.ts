import { DeleteBoatReservationDTO } from './../../models/response/entity-delete/boat-delete';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatReservationService {

  public apiUrl: string = "http://localhost:8080/myuser/reservationBoat";


  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public deleteBoat(dto: DeleteBoatReservationDTO): Observable<Boolean> {
    console.log(dto.myUsername);
    console.log(dto.reservationId);
    console.log(dto.boatId + "IDDD");
    return this.http.post<Boolean>(`${this.apiUrl}/delete`, dto, { headers: this.headers });
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
