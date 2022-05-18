import { DeleteCottageReservationDTO } from './../../models/response/entity-delete/cottage-delete';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { CottageReservationCalendarDTO } from 'src/app/models/response/http-cottage-response/CottageReservationCalendarDTO';
import { SaveCottageReservationDTO } from 'src/app/models/response/http-cottage-response/save-cottage-reservation-dto';

@Injectable({
  providedIn: 'root'
})
export class CottageReservationService {

  public apiUrl: string = "http://localhost:8080/myuser/reservationCottage";


  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public deleteCotage(dto: DeleteCottageReservationDTO): Observable<Boolean> {

    return this.http.post<Boolean>(`${this.apiUrl}/delete`, dto, { headers: this.headers });
  }

  public getAllCottageReservations(id: number): Observable<CottageReservationCalendarDTO> {

    return this.http.get<CottageReservationCalendarDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public saveCottageReservation(dto: SaveCottageReservationDTO): Observable<Boolean> {

    return this.http.post<Boolean>(`${this.apiUrl}`, dto, { headers: this.headers });
  }

  // public getAllCotages(pageNum: number): Observable<CottageDTO[]> {
  //   return this.http.get<CottageDTO[]>(`${this.apiUrl}?pageNum=${pageNum}&pageSize=9`, { headers: this.headers });
  // }

  // public filterCottage(pageNum: number, sortType: String, sortDirection: Boolean, cs: any): Observable<CottageDTO[]> {
  //   return this.http.post<CottageDTO[]>(`${this.apiUrl}/sortBest/${sortType}/${sortDirection}/?pageNum=${pageNum}&pageSize=9`, cs, { headers: this.headers });
  // }

  // public getPricelist(id: number): Observable<CottagePricelistDTO> {
  //   return this.http.get<CottagePricelistDTO>(`${this.apiUrl}/pricelist/${id}`, { headers: this.headers });
  // }


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
